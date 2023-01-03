import nltk

def process_hashes(tweet):
    hashes = []
    tokens = tweet.upper().split()
    for token in tokens:
        if token.startswith('#'):
            token = token.translate(token.maketrans('','','?.,!_:;#@'))
            if token.endswith("'S"):
                token = token[:-2]
            hashes.append(token)
    return hashes

#Correct the next function
#The comments tell you what the code SHOULD be doing
#In many cases it is not doing that
#Do not add or delete lines
def popular_tweets(filename, how = 'retweet', cutoff = 100, counts = False):
    info = {'reply':2,'retweet': 3,'favorite': 4,'username': 5} #map columns in data to what they represent
    result = {} #store results in a dictionary
    with open(filename, 'r') as file: #open file
        for line in file: #read through file line by line
            #print(line) #you may find this debugging print line helpful - use with SMALL file 
            data = line.rstrip().split('\t')#separate data by column for this line 
            #print(data) #you may find this debugging print line helpful - use with SMALL file
            if int(data[info[how]]) >= cutoff: #check if we've met the cutoff
                #print(data[how]) #you may find this debugging print line helpful - use with SMALL file
                if data[info['username']] in result: #check if username already in dictionary
                    result[data[info['username']]].append(data[1]) #add to the existing tweets for that user
                else:
                    result[data[info['username']]] = [data[1]] #create new list of tweets for that user
        if counts == True: #If we want counts for each user in dictionary
            for item in result: #for each user, figure out how many tweets they have and store in dictionary
                result[item] = len(result[item])
        return result
    
def graph_usage(tweets, cutoff= 10):
    hashtags = []
    for tweet in tweets:
        hashtags += process_hashes(tweet)
    tweet_dist = nltk.FreqDist(hashtags)
    tweet_dist.plot(cutoff)
    return tweet_dist

#The function below is correct
#Do not change it in any way
#Input is a dictionary with lists of strings as values
#Output is a list of strings that is ALL of the strings from ALL of the values in the dictionary, see .pdf for example
def join_tweets(info):
    result = []
    for v in info.values():
        result.extend(v)
    return result


#Extra credit only - do not delete the def lines if you don't do these, just leave them as they are

def process_hashes_regex(tweet):
    pass
    
def join_tweets_lc(info):
    pass