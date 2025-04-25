mydata <- read.csv("NASCAR.csv")
library(tidyverse)

NASCAR <- mydata %>% select(-Driver)

pairs(NASCAR)
cor(NASCAR)

model = lm(Winnings~.,data = NASCAR)

coef(model)

#####look into mulitcoliniarity#####
#For the vif function
#Anything above 10 is a red flaf
#5-10 is concerning
library(car)
vif(model)

NASCAR <- NASCAR %>% mutate(Top2.5 = Top5 - Wins, Top6.10 = Top10 - Top5) %>%
  select(-Top5,-Top10)

model = lm(Winnings~.-Poles-Points, data = NASCAR)
summary(model)

plot(hatvalues(model))
which.max(hatvalues(model))

par(mfrow=c(2,2))
plot(model)

#####Check for normality#####
hist(model$residuals)
shapiro.test(model$residuals)
#if the p value is greater than .05, the data is normal
#If it is below .05, the data significantly deviates from a normal distribution
