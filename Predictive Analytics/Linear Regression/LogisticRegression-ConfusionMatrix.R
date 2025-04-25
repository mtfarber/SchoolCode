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


################ Above this line is what we did in class on April 11 ##############
#### Below this line is a complementary example pertaining to Confusion Matrix ####

# Create the confusion matrix using the table() function
# First argument is the actual value and the second is the prediction
glm.table <-table(mydata$purchase, glm.pred)
glm.table

# Calculate the overall error rate, which is the rate of
# predicting YES when the actual is NO as well as predicting NO when the actual is YES
(glm.table["No", "Yes"] + glm.table["Yes", "No"])/sum(glm.table)

# Calculate the Type I error, which is False Positive
# The number of NO (Negatives) that we predict as YES (Positive), 
# divided by the total number of NO we have.
glm.table["No", "Yes"]/sum(glm.table["No", ])

# Calculate the Type II error, which is False Negative
# The number of YES (Positives) that we predict as NO (Negative), 
# divided by the total number of YES we have.
glm.table["Yes", "No"]/sum(glm.table["Yes", ])

# Calculate the Power of the model, which is 1-Type II error
1-glm.table["Yes", "No"]/sum(glm.table["Yes", ])

