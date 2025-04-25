rm(list=ls())
library(tidyverse)
library(readxl)
?read_excel

mydata <- read_excel("DataSets_Week11.xlsx")

pairs(mydata)
model = lm(Wage~., data = mydata)
summary(model)


model2 = lm(Wage~.+I(Age^2), data = mydata)
summary(model2)
plot(predict(model2), residuals(model2))
plot(predict(model2), rstudent(model2))

nd1 <- data.frame(Educ = c(16,16,16), Age = c(30,50,70))
predict(model2, nd1)

##Part E##
SAge = seq(min(mydata$Age),max(mydata$Age))
PWage <- predict(model2, data.frame(Educ = 16, Age = SAge))
plot(SAge, PWage, type = "l", lwd = 1, col = "green", xlab = "Age", ylab = "Wage")
which.max(PWage)
SAge[34]
coef(model2)