library(tidyverse)
beverage <- read.csv("BeverageSales.csv")
str(beverage)

lm1 = lm(Sales ~ Temperature, data = beverage)
summary(lm1)

plot(Sales~Temperature, data=beverage)
abline(lm1, col = 2) 

#Residual line should be straight
plot(lm1)

lm2 = lm(Sales ~ Temperature + I(Temperature^2), data = beverage)
summary(lm2)

# In order to plot the polynomial graph, we need to sort x values, 
# and then predict y-hats using the sorted x's.
# Then, we plot the corresponding curve

sort.bev <- arrange(beverage, Temperature)

# x <- select(sort.bev, Temperature)  # This does not work, why???
x <- sort.bev$Temperature
yhat <- predict(lm2, sort.bev)
lines(x, yhat, col = 2, lwd = 2)
