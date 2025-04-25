rm(list-ls())

x <- 1:100
y <- numeric(length(x))

for (i in x) y[i] <- x[i]^2
head(y)

# or #
x <- seq(2, 100, 2)
y <- numeric(length(x))
for(i in seq(2, 100, 2)) y[i%/%2] <- x[i%/%2]^2
head(y)

##### Part3 #####

x <- seq(1, 99, 2)
y <- numeric(length(x))
for (i in x) y[(i+1)/2] <- x[(i + 1)/2]^2

#or#
x <- seq(1, 99, 2)
y <- numeric(length(x))
for (i in x){
  y[i%/%2 + 1] <- x[i%/%2 + 1]^2
}
head(y)

