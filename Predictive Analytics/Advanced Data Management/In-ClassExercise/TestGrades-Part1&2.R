rm(list=ls())
library(tidyverse)

### Part 1 ###
a1grades <- read.table("Assessment1.txt", sep="\t", header=TRUE)
a2grades <- read.table("Assessment2.txt", sep="\t", header=TRUE)
a3grades <- read.table("Assessment3.txt", sep="\t", header=TRUE)
studentinfo <- read.table("StudentInfo.txt", sep="\t", header=TRUE)

testgrades <- studentinfo %>% full_join(a1grades, by = "Number") %>% 
  full_join(a2grades, by = "Number") %>% 
  full_join(a3grades, by = "Number")
head(testgrades)
colnames(testgrades)
testgrades <- testgrades %>% 
  rename(Assessment1 = Grade.x, Assessment2 = Grade.y, Assessment3 = Grade)
str(testgrades)

testgrades <- na.omit(testgrades)

testgrades <- testgrades %>% mutate(BirthDate = as.Date(BirthDate))
testgrades <- testgrades %>% mutate(Major = factor(Major))
testgrades <- testgrades %>% mutate(Year = factor(Year, ordered = T))
str(testgrades)

### Part 2 ###

grades <- data.frame(scale(testgrades[6:8]))

combinedscore <- grades %>% 
  transmute(combined = (Assessment1+Assessment2+Assessment3)/3)

testgrades <- testgrades %>% mutate(combinedscore)

quantiles <- quantile(combinedscore$combined, c(.8, 0.6, 0.4, 0.2))
quantiles

##### Part 3 #####

testgrades <- testgrades %>% mutate(letter_grade = case_when(
  combined < .3825 ~ "F",combined > -.3825 & combined < -.06193 ~ "D",
  combined > -.06193 & combined < .2127 ~ "C",
  combined > .2127 & combined < .3777 ~ "B", combined > .3777 ~ "A"))

testgrades <- testgrades %>% separate(Name, c('FirstName', 'LastName'), sep = ' ') %>%
  arrange(LastName)
#testgrades <-unite(testgrades, first_name,last_name, sep = " ")
