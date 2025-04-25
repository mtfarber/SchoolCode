library(tidyverse)
library(FNN)

mydata <- read.csv("spamData.csv")
mydata$Spam <- factor(mydata$Spam)
contrasts(mydata$Spam)
# Scale the predictor variables 
mydata[3:5] <- scale(mydata[3:5])

### Create train and test set ###
# no need to add  ID column as record is a unique row identifier
set.seed(123) 
t_prop <- 0.8
train <- slice_sample(mydata, n = t_prop*nrow(mydata), replace=FALSE)
test <- anti_join(mydata, train, by = 'Record') 
set.seed(seed=NULL) # release the seed and set it to be random 

### Implement KNN Classification Algorithm ###
### The knn() function requires 4 arguments: 
# 1. A matrix or dataframe containing the predictors (the X's) 
#    associated with the training data, here train.x
# 2. A matrix or dataframe containing the predictors (the X's) 
#    associated with the data for which we wish to make predictions, 
#    here test.x for the test set, train.x for the training set
# 3. A factor containing the class labels (the Y's) for the
#    training observations, here train.y
# 4. A value for k, the number of nearest neighbors to be 
#    used by the classifier.

train.x <- select(train, Recipients, Hyperlinks, Characters)
test.x <- select(test, Recipients, Hyperlinks, Characters)
train.y <- train$Spam
test.y <- test$Spam

# Set the seed in case of a possible randomness in KNN
set.seed(123) 

# knn with k = 1
knn.pred  <- knn(train.x, test.x, train.y, k = 1)
mytable <- table(test.y, knn.pred)
mytable
test.error <- (mytable["No", "Yes"]+mytable["Yes", "No"])/ sum(mytable)
print(paste('The test error rate is ', round(test.error, 4)*100, '% when k = 1', sep = ''))

# knn with k = 3
knn.pred  <- knn(train.x, test.x, train.y, k = 3)
mytable <- table(test.y, knn.pred)
mytable
test.error <- (mytable["No", "Yes"]+mytable["Yes", "No"])/ sum(mytable)
print(paste('The test error rate is ', round(test.error, 4)*100, '% when k = 3', sep = ''))

# knn with k = 5
knn.pred  <- knn(train.x, test.x, train.y, k = 5)
mytable <- table(test.y, knn.pred)
mytable
test.error <- (mytable["No", "Yes"]+mytable["Yes", "No"])/ sum(mytable)
print(paste('The test error rate is ', round(test.error, 4)*100, '% when k = 5', sep = ''))

# knn with k = 7
knn.pred  <- knn(train.x, test.x, train.y, k = 7)
mytable <- table(test.y, knn.pred)
mytable
test.error <- (mytable["No", "Yes"]+mytable["Yes", "No"])/ sum(mytable)
print(paste('The test error rate is ', round(test.error, 4)*100, '% when k = 7', sep = ''))

set.seed(seed=NULL) # release the seed and set it to be random 

