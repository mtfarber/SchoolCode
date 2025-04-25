rm(list=ls())
grades <- c("A", "B", "B", "A-", "F")

if (is.character(grades)){
  grades <- as.factor(grades)
}

grades <- as.character(grades)

if (!is.factor(grades)){
  grades <- as.factor(grades)
  print("Grades changed to a factor")
} else{
  print("Grades already is a factor")
}

for (i in 1:10){
  print(paste("Hello World",i, sep = "")) #use paste to combine strings with variables
}

for (i in 1:10){
  print(paste("Hello World",i, sep = " - ")) #use sep to change what combines the variables
}

x <- 2
for (i in 1:10){
  print(paste("Step", i, ":",x, sep = ""))
  x <- x^2
}

i <- 5
while (i > 0){
  print(paste("Hello", i))
  i <- i -1
}

for (i in 1:5){
  for (j in 1:3){
    print(paste("i = ",i, " and j =",j,sep = ""))
  }
}
