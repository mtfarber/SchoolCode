rm (list = ls())
library(tidyverse)
library(FNN)

mydata <- read.csv("College.csv")
str(mydata)
mydata[2:17] <- scale(mydata[2:17])

set.seed(467) 
#mydata <- mutate(mydata, id=1:nrow(mydata)) 
t_prop <- 0.8
train <- slice_sample(mydata, n = t_prop*nrow(mydata), replace=FALSE)
test <- anti_join(mydata, train, by = 'Name') 
train <- select(train, -Name)
test <- select(test, -Name)
set.seed(seed=NULL)

train.x <- train[1:16]
train.y <- train$Grad.Rate

test.x <- test[1:16]
test.y <- test$Grad.Rate

lm.fit <- lm(Grad.Rate~. -S.F.Ratio-Accept-Top10perc-Books-Enroll-F.Undergrad-PhD-Terminal,data = train)
summary(lm.fit)

#new model
lm.fit <- lm(Grad.Rate~. -Apps-S.F.Ratio-Accept-Books,data = train)
summary(lm.fit)

test.pred <- predict(lm.fit,newdata = test)

testMSE <- mean(lm.fit$residuals^2)
testMSE


pred1 <- predict(m1.train, newdata = test)
error1 <- pred1-test$Salary
testMSE1 <- mean(error1^2)
testMSE1 

pred6 <- predict(m6.train, newdata = test)
error6 <- pred6-test$Salary
testMSE6 <- mean(error6^2)
testMSE6

