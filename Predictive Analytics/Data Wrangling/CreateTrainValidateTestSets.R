rm(list = ls())
library(tidyverse)

# Read the data as a tibble
mydata <- read_csv("yrbss_22.csv")
mydata <- na.omit(mydata)

# Create an additional column that will be used as a row identifier
mydata <- mutate(mydata, id=1:nrow(mydata)) 

# Define the train and validate proportions and set the seed
t_prop <- 0.7
v_prop <- 0.2
set.seed(467)

# Create train set by selecting 70% of mydata randomly 
train <- mydata %>% slice_sample(n = t_prop*nrow(mydata), replace=FALSE)

# First, remove the 70% selected as the train set from mydata. 
# Then, from the remaining dataset, randomly select 20% as the validate set
validate <- mydata %>%
  anti_join(train, by = 'id') %>%
  slice_sample(n = v_prop*nrow(mydata), replace = FALSE) 

# Remove the train and validate sets from mydata, which will give you the test set
test <- mydata %>%
  anti_join(train, by = 'id') %>%
  anti_join(validate, by = 'id')

# Remove the id column as it is not a part of the original data
train <- select(train, -id)
validate <- select(validate, -id)
test <- select(test, -id)

# Display the first 5 rows of each of the three new data frames. 
head(train, 5)
head(validate, 5)
head(test, 5)
