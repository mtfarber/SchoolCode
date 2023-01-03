from random import randint, choice, choices, sample, shuffle


def generate_numbers_super(limit = 34, num = 5, super_limit = 20): 
    lotto_picks = sample(range(1,limit+1),k=num)
    super_pick = sample(range(1,super_limit+1),k=1)
    lotto_picks += super_pick
    return lotto_picks

def lotto_prize_super(attempt, winning, prizes, super = False, multiplier = 1):
    match = 0
    i = 0
    if super == True:
        super_pick = attempt[-1]
        attempt = attempt[:-1]
        super_win = winning [-1]
        winning = winning[:-1]
    while(i<len(attempt)):
        if attempt[i] in winning:
            match += 1
        i += 1
    if match > (len(attempt)-len(prizes)):
        prize = prizes[len(attempt)-match]
    else:
        prize = 0
    if super == True and super_pick == super_win:
        prize *= multiplier
    return prize

def lotto_prize_search(attempt, winning, prizes):
    match = 0
    for i in attempt:
        if i in winning:
            match +=1
    if match > (len(attempt)-len(prizes)):
        return prizes[len(attempt)-match]
    return 0


#Absolutely no additional code in this file
#No function calls
#No input lines
#There should be no unindented code other than the import line and the def lines