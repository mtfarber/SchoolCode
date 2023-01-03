from Project_3 import *
import nltk

most_retweets = popular_tweets('sen_tweets_edited_2.csv', cutoff = 1000)
print(most_retweets.keys())
joined_tweets = join_tweets(most_retweets)
graph_usage(joined_tweets)
most_replies = popular_tweets('sen_tweets_edited_2.csv', how = 'reply', cutoff = 500, counts = True)
print(most_replies.keys())