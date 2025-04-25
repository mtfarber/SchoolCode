rm(list=ls())

mydata <- read.csv("churndata.csv")
str(mydata)
na.omit(mydata)
mydata$churn <- as.factor(mydata$churn)
mydata$vmail <- as.factor(mydata$vmail)
mydata$area <- as.factor(mydata$area)

mydata[4:17] <- scale(mydata[4:17])
head(mydata)
## Create the training and test sets
set.seed(123)
mydata <- mutate(mydata, id=1:nrow(mydata)) 
t_prop <- 0.7
train <- slice_sample(mydata, n = t_prop*nrow(mydata), replace=FALSE)
test <- anti_join(mydata, train, by = 'id')
#set.seed(seed=NULL)
train <- select(train, -id)
test <- select(test, -id)

train.x <- select(train, -churn)
test.x <- select(test, -churn)
train.y <- train$churn
test.y <- test$churn

###################################
####### LINEAR REGRESSION #########
m1.train <- lm(churn ~ ., data = train)
summary(m1.train)
vif(m1.train)

test.pred <- predict(m4.train, newdata = test)
error <- test.pred-test$churn
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