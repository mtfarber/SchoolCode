EARTH_YEAR = 365.25 #Do not change
D_PER_Y_PLANETS = [87.97, 687, 4331.87, 10760.27, 60189.55] #Do not change
PLANETS = ['Mercury', 'Mars', 'Jupiter', 'Saturn', 'Neptune'] #Do not change
#Fill in the rest of your code here
home_planet = int(input('Select your home planet: 1 for Mercury, 2 for Mars, 3 for Jupiter, 4 for Saturn, 5 for Neptune: '))
age = float(input('Enter your age on your home planet: '))
planet_selection = int(input('Select a destination planet: 1 for Mercury, 2 for Mars, 3 for Jupiter, 4 for Saturn, 5 for Neptune: '))
conversion = (float(D_PER_Y_PLANETS[home_planet-1])/(D_PER_Y_PLANETS[planet_selection-1]))*age
print ('Your age on ' + str(PLANETS[planet_selection-1]) + ' is ' + str(conversion) + ' years')