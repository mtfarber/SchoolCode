### This exercise is intended to demonstrate the use of knn() function 
### It may not necessarily apply to future analysis 
### as we will typically use KNN with train-test or train-validate-test sets.
library(FNN)

data1 <- read.csv("data1.csv")
data1$loanDefault <- factor(data1$loanDefault)

# We will use these stats to standardize the new observations manually
m.age = mean(data1$age)
sd.age = sd(data1$age)
m.bal = mean(data1$avgBal)
sd.bal = sd(data1$avgBal)

# Standardize numerical predictors using the scale() function
data1[2:3] <- scale(data1[2:3])

?knn
### The knn() function requires 4 arguments: 
#
# 1. A matrix or dataframe containing the predictors (the X's) 
#    associated with the training data, here train.x
# 2. A matrix or dataframe containing the predictors (the X's) 
#    associated with the data for which we wish to make predictions, 
#    here test.x for the test set, train.x for the training set
# 3. A factor containing the class labels (the Y's) for the
#    training observations, here train.y
# 4. A value for k, the number of nearest neighbors to be 
#    used by the classifier.

train.x <- data1[2:3]
train.y <- data1$loanDefault

# Classify the following 2 observations, but standardize them first using the train data stats
# Observation1 - Balance: 900, Age: 28
# Observation2 - Balance: 671, Age: 42
test.x <- data.frame(avgBal = (c(900, 671)-m.bal)/sd.bal, age = (c(28, 42)-m.age)/sd.age)
test.x <- test.x
knn.pred <- knn(train.x, test.x, train.y, k = 3)
knn.pred[1:2]

