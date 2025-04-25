rm(list = ls())
library(tidyverse)

# Read the data as a data frame
mydata <- mtcars

# Create an additional column that will be used as a row identifier
mydata <- mutate(mydata, id=1:nrow(mydata)) 

# Define the train and validate proportions and set the seed
t_prop <- 0.6
v_prop <- 0.2
set.seed(467)

# Create train set by selecting 70% of mydata randomly 
train <- slice_sample(mydata, n = t_prop*nrow(mydata), replace=FALSE)

# First, create a temporary data frame, which removes the train observations from mydata data frame
# Then, from the remaining dataset, randomly select 20% as the validate set
temp <- anti_join(mydata, train, by = 'id')  # 
validate <- slice_sample(temp, n = v_prop*nrow(mydata), replace = FALSE)


# Remove the validate observations from temp1 (which already excluded train obs from mydata)
# This will give you the test set
test <- anti_join(temp, validate, by = 'id')

# Remove the id column as it is not a part of the original data
train <- select(train, -id)
validate <- select(validate, -id)
test <- select(test, -id)

# Display the first 5 rows of each of the three new data frames. 
head(train, 5)
head(validate, 5)
head(test, 5)

