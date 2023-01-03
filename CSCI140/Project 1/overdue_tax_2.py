PENALTY_RATE = 0.06 #Do not change
EARTH_YEAR = 365 #Do not change
tax = input('Enter amount of tax owed: ') #Do not change, will be in format $100
rate = input('Enter interest rate: ') #Do not change, will be in format 4%
late = int(input('Enter number of days overdue tax is: ')) #Do not change, will be an integer
penalty = PENALTY_RATE * float(tax[1:])
interest = (float(rate[:-1])/100)*float(tax[1:])*(late/EARTH_YEAR)
total = float(tax[1:]) + interest + penalty
total = round(total, 2)
total = format(total, '.2f')
print('Your total payment is ' + str(total))