import random

#This function will delete item from the list
#Why didn't we just use remove?
def delete_from_list(the_list, item):
    return [obj for obj in the_list if obj != item]

#Convert dollar amounts like $4.50 to floats: 4.5
def convert(money):
    return float(money.lstrip('$'))

#Pick 5 random lotto numbers
def lotto():
    return [random.randint(1,34) for i in range(5)]