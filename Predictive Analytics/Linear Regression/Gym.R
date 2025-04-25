library(tidyverse)
library(FNN)
mydata <- read.csv("gymData.csv")
str(mydata)
mydata$Enroll <- as.factor(mydata$Enroll)
contrasts(mydata$Enroll)
mydata[2:4] <- scale(mydata[2:4])

set.seed(123)
t_prop <- 0.8
mydata <- mutate(mydata, id=1:nrow(mydata))

train <- slice_sample(mydata, n = t_prop*nrow(mydata), replace=FALSE)
test <- anti_join(mydata, train, by = 'id') 
train <- select(train, -id)
test <- select(test, -id)

train.x <- select(train, Age, Income, Hours)
test.x <- select(test, Age, Income, Hours)
train.y <- train$Enroll
test.y <- test$Enroll

#####Logistic Regression#####
set.seed(123)
glm.fit = glm(Enroll~.,family = binomial, data = train)


knn.pred  <- knn(train.x, test.x, train.y, k = 1)
mytable <- table(test.y, knn.pred)
mytable
test.error <- (mytable["0", "1"]+mytable["1", "0"])/ sum(mytable)