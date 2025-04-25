mydata <- read.csv("BirthRates.csv")

lm.fit = lm(BirthRate~.-ID -Nation,data=mydata)
summary(lm.fit)

vif(lm.fit)

lm.fit = lm(BirthRate~.-ID -Nation -Density,data=mydata)
summary(lm.fit)
