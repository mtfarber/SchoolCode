from random import randint, choice, choices, sample

def generate_numbers(limit = 34, num = 5): #do not change this line
    lotto_picks = sample(range(1, limit+1), k=num)
    return lotto_picks

#Function below needs correcting
#Do not add or delete lines
#You may need to change indentation
#Actual code changes are small (indexing errors, errors in boolean expressions)
#BE CAREFUL - there may be an infinite loop in here!!!

def lotto_prize(attempt, winning, prizes): #do not change this line
    match = 0
    i = 0 
    #Check each item in attempt to see if it is in winning
    while(i < len(attempt)):
        #print(i) #You might find this debugging print line helpful
            #Record a match if the items match
        if attempt[i] in winning:
            match += 1
            #print('matched',i) #You might find this debugging print line helpful
        i += 1
    #print(match) #You might find this debugging print line helpful
    if match > (len(attempt)-len(prizes)):#Did they get enough numbers to win a prize?
        return prizes[len(attempt)-match]
    return 0

def simulate_lotto(your_numbers, max_pick, max_plays = 10000):
    i = 0
    while i < max_plays:
        i += 1
        winning_numbers = generate_numbers(max_pick, len(your_numbers))
        jackpot_win = lotto_prize(your_numbers, winning_numbers, [1])
        if jackpot_win == 1:
            return i
    return i

#Absolutely no additional code in this file
#No function calls
#No input lines
#There should be no unindented code other than the import line and the def lines
