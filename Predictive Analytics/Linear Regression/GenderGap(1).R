library(readxl)
library(tidyverse)
myData <- read_excel("DataSets_Week11.xlsx", sheet = "GenderGap")
str(myData)
summary(myData)
myData$Female <- factor(myData$Female)
myData$Grad <- factor(myData$Grad)

### Part 1
#Part 1a
m1 <- lm(Salary ~ Size + Experience + Female + Grad, data = myData)
summary(m1)
par(mfrow=c(2,2))
plot(m1)
#Interpretation of the model
# ......

#Part 1b
m2 <- lm(Salary ~ Size + Experience + Female + Grad 
         + Size*Experience
         + Size*Female
         + Size*Grad 
         + Experience*Female
         + Experience*Grad
         + Female*Grad  
         , data = myData)
summary(m2)

m3 <- lm(Salary ~ Size + Experience + Female + Grad 
         + Size*Experience
         + Size*Grad 
         + Experience*Female
         + Experience*Grad
         + Female*Grad  
         , data = myData)
summary(m3)

m4 <- lm(Salary ~ Size + Experience + Female + Grad 
         + Size*Experience
         + Experience*Female
         + Experience*Grad
         + Female*Grad  
         , data = myData)
summary(m4)

m5 <- lm(Salary ~ Size + Experience + Female + Grad 
         + Size*Experience
         + Experience*Female
         + Experience*Grad
         , data = myData)
summary(m5)

par(mfrow=c(2,2))
plot(m5)

#Interpretation of the model
#....


#Part 1c

# plotting exp vs salary for grad and no grad - for females
dataFG <- myData %>% filter(Female == "1", Grad == "1") %>% arrange(Experience)
dataFNG <- myData %>% filter(Female == "1", Grad == "0") %>% arrange(Experience)

lm1 <- lm(dataFG$Salary~dataFG$Experience)
lm2 <- lm(dataFNG$Salary~dataFNG$Experience)
par(mfrow=c(1,1))
plot(dataFG$Experience, dataFG$Salary, type="p", col="blue", xlab="Experience", ylab="Salary")
abline(lm1, col = "blue")
lines(dataFNG$Experience, dataFNG$Salary,type="p", col="green")
abline(lm2, col = "green")
legend("topleft",c("Grad","No Grad"), lwd=c(1,1), col=c("blue","green"))

# plotting exp vs salary for grad and no grad - for males
dataMG <- myData %>% filter(Female == "0", Grad == "1") %>% arrange(Experience)
dataMNG <- myData %>% filter(Female == "0", Grad == "0") %>% arrange(Experience)

lm3 <- lm(dataMG$Salary~dataMG$Experience)
lm4 <- lm(dataMNG$Salary~dataMNG$Experience)
plot(dataMG$Experience, dataMG$Salary, type="p", col="blue", xlab="Experience", ylab="Salary")
abline(lm3, col = "blue")
lines(dataMNG$Experience, dataMNG$Salary,type="p", col="green")
abline(lm4, col = "green")
legend("topleft",c("Grad","No Grad"), lwd=c(1,1), col=c("blue","green"))

#Part 1d 
# The model with significant interaction terms is the best fitting model 
# with an R2 of ~75%


#Part 2a
set.seed(123) #sets the random seed

# Create an additional column that will be used as a row identifier
myData <- mutate(myData, id=1:nrow(myData)) 

# Define the train and validate proportions and set the seed
t_prop <- 0.8

# Create train set by selecting 80% of myData randomly 
train <- myData %>% slice_sample(n = t_prop*nrow(myData), replace=FALSE)
# or
#train <- slice_sample(myData, n = t_prop*nrow(myData), replace=FALSE)

# Remove the train and validate sets from myData, which will give you the test set
test <- myData %>%
  anti_join(train, by = 'id') 
# or
#test <- anti_join(myData, train, by = 'id') 

# Remove the id column as it is not a part of the original data
train <- select(train, -id)
train <- train[-6]
test <- select(test, -id)
test <- test[-6]
set.seed(seed=NULL) # remove the random seed  


#Part 2b
m1.train <- lm(Salary ~ Size + Experience + Female + Grad, data = train)
summary(m1.train)

#Part 2c
m2.train <- lm(Salary ~ Size + Experience + Female + Grad 
         + Size*Experience
         + Size*Female
         + Size*Grad 
         + Experience*Female
         + Experience*Grad
         + Female*Grad  
         , data = train)
summary(m2.train)

m3.train <- lm(Salary ~ Size + Experience + Female + Grad 
               + Size*Experience
               + Size*Female
               + Size*Grad 
               + Experience*Grad
               + Female*Grad  
               , data = train)
summary(m3.train)

m4.train <- lm(Salary ~ Size + Experience + Female + Grad 
               + Size*Experience
               + Size*Grad 
               + Experience*Grad
               + Female*Grad  
               , data = train)
summary(m4.train)

m5.train <- lm(Salary ~ Size + Experience + Female + Grad 
               + Size*Experience
               + Size*Grad 
               + Experience*Grad
               , data = train)
summary(m5.train)

m6.train <- lm(Salary ~ Size + Experience + Female + Grad 
               + Size*Experience
               + Experience*Grad
               , data = train)
summary(m6.train)

#Part 2d
trainMSE1 <- mean(residuals(m1.train)^2)
trainMSE1
trainMSE6 <- mean(m6.train$residuals^2)
trainMSE6

#Part 2e
pred1 <- predict(m1.train, newdata = test)
error1 <- pred1-test$Salary
testMSE1 <- mean(error1^2)
testMSE1 

pred6 <- predict(m6.train, newdata = test)
error6 <- pred6-test$Salary
testMSE6 <- mean(error6^2)
testMSE6 


