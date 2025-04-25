# Create a linear regression model to predict HwyMpg 
# using the variables Class, Disp and FuelType variables 

# Using the model you created predict the HwyMpg for 
# a midsize car that uses regular gas and has an engine displacement of 3.1

FuelEff <- read.csv("Fuel.csv")

str(FuelEff)
summary(FuelEff)

FuelEff$Class <- as.factor(FuelEff$Class)
FuelEff$FuelType <- as.factor(FuelEff$FuelType)

model1 = lm(HwyMPG~Class+Disp+FuelType, data = FuelEff)
summary(model1)
nd1 <- data.frame(Class="Midsize", Disp = 3.1, FuelType = "R")
predict(model1, nd1)

model2 = lm(HwyMPG~.-Car, data = FuelEff) #won't work if we take out the row identifier
summary(model2)
predict(model2, nd1) # Does not work, why???
#Doesn't work because the . was used. Avoided by including all original columns
#in the mini data frame, or we can just build the model using +

nd2 <- data.frame(Car = 1, Class="Midsize", Disp = 3.1, FuelType = "R")
predict(model2, nd2)

# How can I make a prediction for multiple observations???
nd2 <- data.frame(Car = c(1,2,3,4,5), Class="Midsize", Disp = 3.1, FuelType = "R")
predict(model2, nd2)

#Another way to calculate predictions
#yhat <- 28.6414+1.6450*ClassLarge+3.9634*ClassMidSize-1.6347*Disp+1.1210*FuelTypeR
contrasts(FuelEff$Class)
contrasts(FuelEff$FuelType)
yhat <- 28.6414+1.6450*0+3.9634*1-1.6347*3.1+1.1210*1
yhat


