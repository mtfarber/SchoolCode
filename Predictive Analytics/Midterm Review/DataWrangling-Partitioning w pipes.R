rm(list = ls())
library(tidyverse)

# Read the data as a data frame
mydata <- mtcars

# Create an additional column that will be used as a row identifier
mydata <- mydata %>% mutate(id=1:nrow(mydata))

# Define the train and validate proportions and set the seed
t_prop <- 0.6
v_prop <- 0.2
set.seed(467)

# Create train set by selecting 70% of mydata randomly 
train <- mydata %>% slice_sample(n = t_prop*nrow(mydata), replace=FALSE)

# First, remove the train observations from mydata. 
# Then, from the remaining dataset, randomly select 20% as the validate set
validate <- mydata %>%
  anti_join(train, by = 'id') %>%
  slice_sample(n = v_prop*nrow(mydata), replace = FALSE) 

# Remove the train and validate sets from mydata, which will give you the test set
test <- mydata %>%
  anti_join(train, by = 'id') %>%
  anti_join(validate, by = 'id')

# Remove the id column as it is not a part of the original data
train <- train %>% select(-id)
validate <- validate %>% select(-id)
test <- test %>% select(-id)

# Display the first 5 rows of each of the three new data frames. 
train %>% head(5)
validate %>% head(5)
test %>% head(5)

