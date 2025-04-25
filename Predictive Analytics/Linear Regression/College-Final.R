rm(list=ls())
library(FNN)
library(tidyverse)
library(car)

# Read the data and set College 
college <- read.csv('College.csv')
head(college)
str(college)
## Name variable has no predictor power, it is a unique row identifier. 
## We need to remove it, but can do it after partitioning. 

## Scale the numeric data now rather than after it hasbeen partitioned 
## - better scaling with the entire set of observations.
ncol(college)
college[2:17] <- scale(college[2:17])  
head(college)
## Create the training and test sets
set.seed(467)
t_prop <- 0.8
train <- slice_sample(college, n = t_prop*nrow(college), replace=FALSE)
test <- anti_join(college, train, by = 'Name') 
set.seed(seed=NULL)

# Remove name variable as it has no predictive power
train <- select(train, -Name)
test <- select(test, -Name)

# Create subsets needed for KNN Regression
train.x <- select(train, -Grad.Rate)
test.x <- select(test, -Grad.Rate)
train.y <- train$Grad.Rate
test.y <- test$Grad.Rate

###################################
####### LINEAR REGRESSION #########
m1.train <- lm(Grad.Rate ~ ., data = train)
summary(m1.train)
vif(m1.train)

m2.train <- lm(Grad.Rate ~ .-Apps-Accept-Enroll, data = train)
summary(m2.train)
vif(m2.train)

m3.train <- lm(Grad.Rate ~ .-Apps-Accept-Enroll-S.F.Ratio, data = train)
summary(m3.train)

m4.train <- lm(Grad.Rate ~ .-Apps-Accept-Enroll-S.F.Ratio-Books, data = train)
summary(m4.train)

test.pred <- predict(m4.train, newdata = test)
error <- test.pred-test$Grad.Rate
testMSE <- mean(error^2)
testMSE 

###################################
######### KNN REGRESSION ##########

# predicting graduation rate with k = 3
knn.reg.model <- knn.reg(train.x, test.x, train.y, k=3)
MSE <- mean((test.y-knn.reg.model$pred)^2)
MSE

# Predict graduation rates for k = 1, k = 2, ..., k = 50
biggestk=50
# Create testMSE, a vector of 0s, to store the MSEs
testMSE <- rep(0, biggestk)

# Execute the knn algorithm for k = 1,...,50.
# Calculate the MSEs and store them in testMSE 
kset <- seq(1, biggestk)  
for(i in kset) {
  knn.reg.model <- knn.reg(train.x, test.x, train.y, k=i)
  testMSE[i] <- mean((test.y-knn.reg.model$pred)^2)
}
minerror <- min(testMSE)
bestk <- kset[which.min(testMSE)]
print(paste("Best test k is", bestk, "with a test error rate of", minerror))

########### Additional Insights ##################
trainMSE <- rep(0, biggestk)
for(i in kset) {
  knn.reg.model <- knn.reg(train.x, train.x, train.y, k=i)
  trainMSE[i] <- mean((train.y-knn.reg.model$pred)^2)
}

minerror.tr <- min(trainMSE)
bestk.tr <- kset[which.min(trainMSE)]
print(paste("Best train k is", bestk.tr, "with a train error rate of", minerror.tr))

# Plot the testMSEs and trainingMSEs
# # Plot the axis, title and the labels
plot(NULL, NULL, 
     xlim=c(biggestk, 1), 
     ylim=c(0,max(c(testMSEs, trainMSEs))),      
     type='n',  
     xlab='Increasing Flexibility (Decreasing k)', 
     ylab='Mean Squared Errors', 
     main='Training and Test MSEs as a Function of \n Flexibility for KNN Prediction')

# Plot the testMSE line from k = 50,...,1
lines(seq(biggestk, 1,by = -1), 
      testMSEs[length(testMSEs):1], 
      type='b', 
      col=2, 
      pch=16)

# Plot the trainingMSE line from k = 50,...,1
lines(seq(biggestk, 1, by=-1), 
      trainMSEs[seq(biggestk, 1, by=-1)], 
      type='b', 
      col=1, 
      pch=16)

# Add the legend
legend("topleft", legend = c("Test MSEs", "Training MSEs"), 
       col=c(2, 1), 
       cex=.75, 
       pch=16)
set.seed(seed=NULL)
