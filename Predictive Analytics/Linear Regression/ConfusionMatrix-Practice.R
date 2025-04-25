rm(list=ls())
library(tidyverse)
library(FNN)
library(caret)
# Read the data from the csv file
mydata <- read.csv("spamDataCM.csv")
head(mydata)
str(mydata)

# Convert spam variable to be factor
mydata$Spam <- factor(mydata$Spam)

# Contrasts tells us, which level (Yes or No) is considered as which class (Class 1 or 0)
contrasts(mydata$Spam)

# Please note we use all the observations in the data set without partitioning. 

# Create a logistic regression model
glm1 <- glm(Spam ~ Recipients+Hyperlinks+Characters, data = mydata, family = binomial)
# Display the summary output of the logistic regression model
summary(glm1)
# Create a vector of all No's (The class that we are not interested in)
glm.pred <- rep("No", nrow(mydata))
# Predict the probability of an email being spam
p.prob <- predict(glm1, data = mydata, type = 'response')
# Update the glm.pred vector based on the likelihood of emails being spam
glm.pred[p.prob > 0.5] <- "Yes"

#Part a
glm.pred <- as.factor(glm.pred)
confusionMatrix(mydata$Spam, glm.pred)

#Overall error rate is 20.6%
#Type 1 error rate is 20.9%
#Type 2 error rate is 20.3%
#Power of the model is 79.08%
#Precision of the model is 78.1%


#Part B

#Since spam emails aren't harmful if they are sent to the inbox, it is more important
#that all of the regular emails make it into the inbox
#It would be worse if these emails were to be sent to spam by accident,
#so the type 1 error should try and be reduced
#One way to do this would be to increase the threshold for the probability of what counts
#as a spam email

#Part C

glm.pred <- rep("No", nrow(mydata))
glm.pred[p.prob > 0.2] <- "Yes"
glm.pred <- as.factor(glm.pred)
confusionMatrix(mydata$Spam, glm.pred)

#Overall error rate is 31.8%
#Type 1 error is 15.12%
#Type 2 error is 37%
#Power is 84.87%

