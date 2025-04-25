library(MASS)
head(Boston)
fix(Boston)
#Modify the data set using an editor
names(Boston)
data(Boston)
lm.fit=lm(medv~lstat) #Creates a linear model
#Doesn't work because R doesn't know what medv is
#first argument is the y value (dependent), second argument after ~ is the x variable'
lm.fit=lm(medv~lstat , data=Boston)
attach(Boston)
lm.fit=lm(medv~lstat) #Works because Boston is now attached
lm.fit #gives the coefficients for the regression equation
summary(lm.fit) #can look at p values and r squared
names(lm.fit)

lm.fit$coefficients
coef(lm.fit)
confint(lm.fit) #creates 95% confidence interval
confint(lm.fit, 'lstat', level=0.90)
confint(lm.fit, 'lstat', level=0.99) #Creates a 99% confidence interval

#confidence and prediction intervals
predict(lm.fit, data.frame(lstat = c(5, 10, 15)), interval = "confidence")
#c() is the values you want to plug into the equation
predict(lm.fit, data.frame(lstat = c(5, 10, 15)), interval = "prediction")

#scatter plot and the regression line
plot(lstat ,medv)
abline(lm.fit)

#plotting with different colors or patterns
abline(lm.fit ,lwd=3,col ="red")
plot(lstat ,medv ,col="blue")
plot(lstat ,medv ,pch =20)

#residual and other diagnostic plots
par(mfrow=c(2,2))
plot(lm.fit)

#if you want to plot some of these diagnostic plots 
par(mfrow=c(1,1))
plot(predict (lm.fit), residuals (lm.fit))
plot(predict (lm.fit), rstudent (lm.fit))

plot(hatvalues(lm.fit))
plot(hatvalues(lm.fit), type = "h")
which.max(hatvalues(lm.fit))
max(hatvalues(lm.fit))
detach()
