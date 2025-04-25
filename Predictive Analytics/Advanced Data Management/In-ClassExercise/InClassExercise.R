rm(list = ls())
library(tidyverse)

assessment1 <- read.table(file = "Assessment1.txt", header = T, sep = '\t')
assessment2 <- read.table(file = "Assessment2.txt", header = T, sep = '\t')
assessment3 <- read.table(file = "Assessment3.txt", header = T, sep = '\t')
studentinfo <- read.table(file = "studentinfo.txt", header = T, sep = '\t')

mydata <- full_join(studentinfo,assessment1,by = "Number")
mydata <- full_join(mydata,assessment2,by = "Number")
mydata <- full_join(mydata,assessment3,by = "Number")
colnames(mydata) <- c("Number", "Name", "BirthDate", "Year", "Major", "Grade1", "Grade2", "Grade3")

