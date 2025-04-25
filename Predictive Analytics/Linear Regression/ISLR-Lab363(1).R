library(MASS)
library(car)

lm.fit = lm(medv~lstat+age ,data=Boston )
summary(lm.fit)

lm.fit=lm(medv~.,data=Boston)
summary(lm.fit)

summary(lm.fit)$r.sq
summary(lm.fit)$adj.r.sq
summary(lm.fit)$sigma #Display the RSE
?summary.lm

vif(lm.fit)

lm.fit1=lm(medv~.-age ,data=Boston )
summary(lm.fit1)

lm.fit2=lm(medv~.-age -indus ,data=Boston )
summary(lm.fit2)

