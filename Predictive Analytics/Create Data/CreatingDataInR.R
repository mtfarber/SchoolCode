rm(list=ls())

a <- c(1,2,5,3,6,-2,4) #creates a numerical vector
b <- c("one", "two", "three") #creates a character vector
d <- c(TRUE, TRUE, T, FALSE, TRUE, F) #Creates a logical vector
#TRUE and FALSE can also be represented as T and F

e <- 1:20
f <- seq (7,23)
rep(2, 10)

a[3] #extracts third element from the vector
f[1:12]
a[c(1,3,5)]

n <- c(2, 3, 5)
s <- c("aa", "bb", "cc")
c(n, s)

e <- c(1:3, 5, -3:3)
e
e[2]
e[4:6]

a <- c(1,3,5,7)
b <- c(1,2,4,8)

a+b
a-b
a*b
5*a

u <- c(10, 20, 30)
v <- 1:9
u+v

x <- c(10, 20)
x + v

my.vec <- scan("vectordata.csv", what = character(), sep = ",")

##### Matrices #######

#Create a 5x4 matrix with values 1 through 20, populated by columns
myMatrix <- matrix(1:20, 5, 4)
myMatrix

# Create a 2x2 matrux with values 1 and 26 in the first row and 24 and 68 in the second row

matrix2 <- matrix(c(1, 26, 24, 68), nrow=2, byrow = T)
matrix2

myMatrix[2,4]
myMatrix[,4]
myMatrix[2,]
myMatrix[2:3, 3:4]

####### Data Frames #######

patientID <- c("John","Mary","Steve", "Susan")
age <- c(25, 34, 28, 52)
diabetes <- c("Type1", "Type2", "Type1", "Type1")
status <- c("Poor", "Improved", "Excellent", "Poor")

patientData <- data.frame(patientID, age, diabetes, status)
patientData[1,2]
patientData[1,]
patientData[1] #Will give the first column as a data frame
patientData[,1]
patientData$patientID #reduces the named column into a vector
#Used for grabbing different elements of a data frame

#patientData$patientID

patientData[1:2]
patientData[c(1,3)]
patientData[,c(1,3)]

#names the rows with the names that are stored in the patientID vector
patientDataNew <- data.frame(patientID,age,diabetes,status,
                             row.names = "patientID")

patientData2 <- patientData

##### Factors #####
diabetes <- factor(diabetes)
status2 <- factor(status)
#to change the order of the levels
status <- factor(status, levels = c("Poor", "Improved", "Excellent"))
#Variables will be NA if the level isn't included
status3 <- factor(status, levels = c("Improved", "Excellent"))
#when converting a variable into an ordered factor you need to include order = T

patientData$status <- factor(patientData$status, levels = c("Poor", "Improved", "Excellent"), order = T)
patientData$diabetes <- factor(patientData$diabetes)

summary(patientData)
summary(patientData2)

##### Reading data from a txt file #####

apps <- read.table("StudentApplications.txt", header = T, sep = "\t",
                   row.names = "StudentID", stringsAsFactors = F)

apps2 <- read.table("StudentApplications.txt", header = T, sep = "\t",
                   row.names = "StudentID", stringsAsFactors = T)

mtcars
summary(mtcars$mpg)
attach(mtcars)
summary(mpg)
detach(mtcars)

#use scan when you want to convert to vectors instead of a dataframe

##### Miscellaneous #####
y <- c(1,2,3,NA)
y[1] == 2
y[4] == NA
is.na(y)
is.na(y[4])

x <- y[1] + y[2] + y[3] + y[4]
x

z <- sum(y, na.rm = TRUE)
z

a <- c(1,2,3)
is.numeric(a)
is.vector(a)
is.character(a)
is.factor(a)
as.character(a)
