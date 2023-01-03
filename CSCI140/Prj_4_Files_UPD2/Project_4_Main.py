from Project_4 import *
import pandas as pd
import seaborn as sns
import numpy as np
import matplotlib.pyplot as plt
import folium


sen = pd.read_csv('senators_parsed.csv') #Read in data from file
sen['year'] = sen['created_at'].apply(lambda x: '20' + x.split()[0][-2:]) #Create year column
#Next line is correct
months = {1:'January', 2:'February', 3:'March', 4:'April', 5:'May', 6:'June', 7:'July', 8:'August', 9:'September', \
          10:'October', 11:'November', 12:'December'} #this line is correct
sen['month'] = sen['created_at'].apply(lambda x: months.get(int(x.split('/')[0]))) #Create month column
sen.drop(['url', 'bioguide_id', 'created_at'], axis = 1, inplace = True) #Drop 3 columns
sen['user'] = sen['user'].str.upper() #Put usernames in to uppercase
sen_2016 = sen.loc[(sen['year'] == '2016') & (sen['month'] =='November')] #New data frame just November 2016

###Put your code for Part 3 here underneath this line, it should not be indented

user_tweets = aggregate_data(sen, 'user')
high_retweets = subset_data(user_tweets, cutoff = 100000)
print(high_retweets)
party_tweets = aggregate_data(sen, 'party', how = 'mean')
favorites_plot = plot_data(sen, 'favorites', 'party')
replies_map = map_data(aggregate_data(sen, 'state'), 'replies', 'us-states.json', 'feature.id')