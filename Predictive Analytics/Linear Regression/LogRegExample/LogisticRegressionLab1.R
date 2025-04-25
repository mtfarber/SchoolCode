library(tidyverse)
rm(list = ls())
# Read the data from the txt file and take a peek into the dataset and its structure
mydata <- read.table("LogRegExample1.txt", sep = "\t", header = T)
head(mydata)
str(mydata)
# Convert purchase variable to be factor
mydata$purchase <- as.factor(mydata$purchase)
# Contrasts tells us, which level (Yes or No) is considered as which class (Class 1 or 0)
contrasts(mydata$purchase)
# Use the table() function to display the breakdown of the purchase variable
table(mydata$purchase)
# Calculate the error rate when/if you predict everything to be No (the most common occurrence)
nrow(filter(mydata, purchase == "Yes"))/nrow(mydata)
# Create a logistic regression model
glm.fit <- glm(purchase ~ income+age+zip, data = mydata, family = binomial)
# Display the summary output of the logistic regression model
summary(glm.fit)
# Create a vector of all No's (The class that we are not interested in)
glm.pred <- rep("No", nrow(mydata))
# Predict the probability of customers making a purchase
p.prob <- predict(glm.fit, data = mydata, type = 'response')
# Update the glm.pred vector based on the likelihood of customers' purchases
glm.pred[p.prob > 0.5] <- "Yes"
# Calculate the percentage of correct predictions
mean(glm.pred == mydata$purchase)
# Calculate the percentage of incorrect predictions, which is the error rate
mean(glm.pred != mydata$purchase)
