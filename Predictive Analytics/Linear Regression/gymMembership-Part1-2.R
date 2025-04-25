rm (list = ls())
library(tidyverse)
library(FNN)

mydata <- read.csv("gymData.csv")
str(mydata)
mydata$Enroll <- factor(mydata$Enroll)
contrasts(mydata$Enroll)
head(mydata)

# Scale the predictor variables
mydata[2:4] <- scale(mydata[2:4])
head(mydata)

# Partition the data frame
set.seed(123) 
mydata <- mutate(mydata, id=1:nrow(mydata)) 
t_prop <- 0.8
train <- slice_sample(mydata, n = t_prop*nrow(mydata), replace=FALSE)
test <- anti_join(mydata, train, by = 'id') 
train <- select(train, -id)
test <- select(test, -id)
set.seed(seed=NULL) # release the seed and set it to be random 

train.x <- train[2:4]
train.y <- train$Enroll

test.x <- test[2:4]
test.y <- test$Enroll

###################################
#####LOGISTIC REGRESSION###########
set.seed(123)
glm.fit = glm(Enroll ~.,family=binomial, data=train)
summary(glm.fit)

glm.probs<-predict(glm.fit,newdata = test,type="response")   

glm.pred<-rep(0, nrow(test))
glm.pred[glm.probs>.5]<- 1

mean(glm.pred != test.y)
