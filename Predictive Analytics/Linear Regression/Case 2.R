library(tidyverse)
library(readxl)
library(car)

mydata <- read_excel("DataSets_Week11.xlsx", sheet = "GenderGap")

pairs(mydata)
model = lm(Salary~., data = mydata)
summary(model)

plot(model)
vif(model)
cor(mydata)

#####Part 1b#####
model2 <- lm(Salary~ Size+Experience+Female+Grad
             +Size*Experience
             +Size*Grad
             +Experience*Female
             +Experience*Grad
             +Female*Grad
             ,data = mydata)
summary(model2)

model3 <- lm(Salary~ Size+Experience+Female+Grad
             +Size*Experience
             +Experience*Female
             +Experience*Grad
             ,data = mydata)
summary(model3)
par(mfrow = c(2,2))
plot(model3)
par(mfrow = c(1,1))

#####Part 2#####
mydata <- mutate(mydata, id=1:nrow(mydata))
t_prop <- 0.8
set.seed(123)

train <- mydata %>% slice_sample(n = t_prop*nrow(mydata), replace=FALSE)
test <- mydata %>%
  anti_join(train, by = 'id')

train <- select(train, -id)
test <- select(test, -id)
model4 <- lm(Salary~Size+Experience+Female+Grad, data = train)
summary(model4)
model5 <- lm(Salary~Size+Experience+Female+Grad
             +Size*Experience
             +Experience*Female
             +Experience*Grad
             , data = train)
summary(model5)

model6 <- lm(Salary~Size+Experience+Female+Grad, data = test)
summary(model6)
model7 <- lm(Salary~Size+Experience+Female+Grad
             +Size*Experience
             +Experience*Female
             +Experience*Grad
             , data = test)
summary(model7)
#Calculate the MSE
#Train data
mean(model4$residuals^2)
mean(model5$residuals^2)

#Test Data #From GenderGap file
pred1 <- predict(m1.train, newdata = test)
error1 <- pred1-test$Salary
testMSE1 <- mean(error1^2)
testMSE1 

pred6 <- predict(m6.train, newdata = test)
error6 <- pred6-test$Salary
testMSE6 <- mean(error6^2)
testMSE6
