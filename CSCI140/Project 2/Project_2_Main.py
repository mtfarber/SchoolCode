from Project_2 import *
from random import randint, choice, choices, sample, shuffle

your_numbers = generate_numbers(65)
winning_numbers = generate_numbers(65)
print(lotto_prize(your_numbers,winning_numbers,[100000,500,50]))
print(simulate_lotto(generate_numbers(20,6),20,1000))