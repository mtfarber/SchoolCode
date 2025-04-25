rm(list=ls())

c(1,3,5,2,11,9,3,9,12,3) -> age
weight <- c(4.4, 5.3, 7.2, 5.2, 8.5, 7.3, 6, 10.4, 10.2, 6.1)
plot (age, weight)
my.model <- lm (weight ~ age)
summary(my.model)