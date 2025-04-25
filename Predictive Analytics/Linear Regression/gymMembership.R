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

###################################
######### KNN #####################
## I pick k = 5
knn.pred <- knn(train.x, test.x, train.y, 5)
sum(test.y != knn.pred) / length(knn.pred)
# Implement KNN for k = 1,2,3,..., 50
set.seed(123)
biggestk <- 50
kset <- seq(1:biggestk)
test.errors <- rep(0, length(kset))           
for(k in kset) {
  knn.pred <- knn(train.x, test.x, train.y, k = k)
  test.errors[k] <- sum(test.y != knn.pred) / length(knn.pred)
}
## Find the lowest error and the associated k value
minerror <- min(test.errors)
bestk <- kset[which.min(test.errors)]
print(paste("Best test k is", bestk, "with a test error rate of", minerror))

############################################################################
####################### End of Parts 1, 2 and 3 ############################
############################################################################

################################################################
### Create confusion matrix for both knn and logistic regression
# Re-run the knn model with best k to obtain the predictions with the best k
knn.pred <- knn(train.x, test.x, train.y, k = bestk)
knn.table<-table(test.y,knn.pred)
knn.table
(knn.table[1,2]+knn.table[2,1])/ sum(knn.table)

# Create confusion matrix for the logistic regression
glm.table<-table(test.y, glm.pred)
glm.table
(glm.table[1,2]+glm.table[2,1])/ sum(glm.table)

######################################################
### Analyze error rates for the test set and train set
## Use KNN to make a classification for the train data
set.seed(123)
train.errors <- rep(0, length(kset))
for(k in kset) {
  knn.pred <- knn(train.x, train.x, train.y, k = k)
  train.errors[k] <- sum(train.y != knn.pred) / length(knn.pred)
}
### Plot the graph k vs error rate
# This is just for demonstration purposes.
# You are not expected to create this in the assignment or exam
plot(NULL, NULL, type='n', xlim=c(biggestk, 1), ylim=c(0,max(c(test.errors, train.errors))), xlab='Increasing Flexibility (Decreasing k)',ylab='Errors', main='Errors as a Function of \n Flexibility for KNN')
lines(seq(biggestk, 1,by = -1), test.errors[length(test.errors):1], type='b', col=2, pch=16)
lines(seq(biggestk, 1 , by = -1), train.errors[length(train.errors):1], type='b', col=1, pch=16)
legend("bottomleft", legend = c("Test Error", "Train Errors"), col=c(2, 1), cex=.75, pch=16)

#########################################
#### Making predictions with odd k's ####
## Loop through odd k's, recording test and train errors
set.seed(123)
biggestk <- 50            
kset <- seq(1, biggestk, by = 2)
train.errors <- rep(0, length(kset))
test.errors <- rep(0, length(kset))           
for(k in kset) {
  knn.pred <- knn(train.x, test.x, train.y,k = k)
  test.errors[k%/%2 + 1] <- sum(test.y != knn.pred) / length(knn.pred)
}
for(k in kset) {
  knn.pred <- knn(train.x, train.x, train.y, k = k)
  train.errors[k%/%2 + 1] <- sum(train.y != knn.pred) / length(knn.pred)
}

### Plot the graph k vs error rate
# This is just for demonstration purposes.
# You are not expected to create this in the assignment or exam
plot(NULL, NULL, type='n', xlim=c(biggestk, 1), ylim=c(0,max(c(test.errors, train.errors))), xlab='Increasing Flexibility (Decreasing k)',ylab='Errors', main='Errors as a Function of \n Flexibility for KNN')
lines(seq(biggestk, 1,by = -2), test.errors[length(test.errors):1], type='b', col=2, pch=16)
lines(seq(biggestk, 1 , by = -2), train.errors[length(train.errors):1], type='b', col=1, pch=16)
legend("bottomleft", legend = c("Test Errors", "Train Errors"), col=c(2, 1), cex=.75, pch=16, lty=1)


