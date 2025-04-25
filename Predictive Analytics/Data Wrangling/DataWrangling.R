rm(list = ls())
install.packages("tidyverse")
no
library(tidyverse)
mydata_df <- read.csv("data1.csv")
mydata_df
mydata_tib <- read_csv("data1.csv")

##### Piping #####

(x <- seq(1, 10, by = 2))
sqrt(x)
log(sqrt(x))
round(log(sqrt(x)), digits = 2)

x %>% sqrt %>% log %>% round(digits = 2)

mydata <- read_csv("yrbss_22.csv")
glimpse(mydata)
head(mydata, 7)
mydata %>% filter(bmi > 20)
mydata %>% filter(grade == "9th") #with piping
filter(mydata, grade == "9th") #without piping

mydata[mydata$grade == "9th", ]

##### Practice #####

mydata %>% filter(bmi < 20)
mydata %>% filter(bmi/stweight < .5)
mydata %>% filter(bmi < 15 | bmi > 50)
mydata %>% filter(record == 506901)
mydata %>% filter(bmi < 20 & stweight < 50 & sex == "Male")
mydata %>% filter(sex == "Female")
mydata %>% filter(grade != "9th")
mydata %>% filter(grade == "10th" | grade == "11th")
mydata %>% filter(is.na(bmi))
mydata %>% filter(!is.na(bmi))

mydata1 <- na.omit(mydata)
distinct(mydata)
mydata %>% distinct(age)

mydata %>% arrange (desc(age), bmi)

newdata <- read_csv("yrbss_22.csv")
newdata <- newdata %>% filter((race7 == "Asian" | race7 == "Native Hawaiian/other PI")& grade == "9th") %>% filter ((age > 13)) %>% select(-contains("race4"))
newdata <- newdata %>% filter(race7 %in% c("Asian", "Native Hawaiian/other PI"))%>%
  filter(grade == "9th") %>% filter ((age != "12 years old or younger")) %>%
  select(-race4)
#Select choses which columns to keep
#select(-columnName) means everything but that column is kept
ncol(newdata)                                                                                       
head(newdata, 7)

##### Mutate #####

library(tidyverse)
mydata <- read_csv("yrbss_22.csv")
mydata1 <- mydata %>% mutate(height_m = sqrt(stweight/bmi))

mydata2 <- mydata %>% mutate(bmi_group = case_when(bmi < 18.5 ~ "underweight",
                                                   bmi >= 18.5 & bmi <= 24.9 ~ "normal",
                                                   bmi > 24.9 & bmi <= 29.9 ~ "overweight",
                                                   bmi > 29.9 ~ "obese"))

mydata %>% mutate(bmi_high = (bmi > 30))
mydata %>% mutate(male = (sex == "Male"))
mydata %>% mutate(male = 1*(sex == "Male"))
mydata %>% mutate(grade_num = as.numeric(str_remove(grade,"th"))) %>%
  select(-grade)

##### Mutate Practice #####

newdata <- read_csv("yrbss_22.csv") %>%
  mutate(grade_num = as.numeric(str_remove(grade,"th"))) %>%
  filter(grade_num >= 11, !is.na(bmi)) %>% 
  mutate(bmi_normal = case_when(
    bmi >= 18.5 & bmi <= 24.9 ~ 1, TRUE ~ 0)) %>% #True means every other value
  arrange(desc(grade_num))
