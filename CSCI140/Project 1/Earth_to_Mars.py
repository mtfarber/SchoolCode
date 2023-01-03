MARS_YEAR = 687
EARTH_YEAR = 365.25
Earth_to_Mars_conversion = (EARTH_YEAR/MARS_YEAR)
Earth_to_Mars = (float(input('Enter number of Earth years: ')))*Earth_to_Mars_conversion
print ('This is',Earth_to_Mars,'years on Mars.')
