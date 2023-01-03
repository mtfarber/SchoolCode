target = int(input ('Enter total amount to save: '))
paychecks = int(input('Enter number of paychecks per year: '))
deduction = target//paychecks
extra_money = target%paychecks
final_deduction = deduction + extra_money
print ('You must make ' + str(paychecks-1) + ' deductions of $' + str(deduction) + ' and one final deduction of $' + str(final_deduction) + ' to save $' + str(target))
