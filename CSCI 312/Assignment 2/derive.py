#Matthew Farber
#Oct 4 2021
#This program prints an output of possible values of any specified length that can be created by a certain grammar
#It also has the option of printing the corresponding derivation lists for each possible value
#This option can be set by a command line line argument

import sys
import string

#initial default variable setup
dict = {}
length = 3
derivations = False

#read in arguments from the command line
args = sys.argv
for i in args:
    if (i[0:2] == "-l"):
        length = int(i[2:])
    elif (i == "-d"):
        derivations = True
    else:
        filename = i
        

set_worklist = False
original_key = True
nonterms = []
worklist = []
derivation = []

#open file and read in line by line
#create dictionary based off of the grammar
for line in open(filename, "r"):
    list = line.split()
    key = list.pop(0)
    
    if (original_key == True):
        starter = key
        original_key = False
    nonterms.append(key)
    
    #save start symbol and delimiter values
    #initializes the worklist
    if (set_worklist == False):
        worklist.append(key)
        derivation.append(key)
        worklist.append(derivation)
        set_worklist = True
    delimiter = list.pop(0)
    
    #add individual values to the dictionary
    if key in dict:
        firstRun = True
        string = ""
        for i in list:
            if (firstRun == False):
                string += " "
            string += i
            firstRun = False
        dict[key].append(string)
    else:
        #create a new key and add values
        firstRun = True
        string = ""
        for i in list:
            if (firstRun == False):
                string += " "
            string += i
            firstRun = False
        dict[key] = [string]
nonterms = set(nonterms)


s = ""
generated_strings = 0
printed_strings = []
print("")


#go through the dictionary and create derivation lists and possible values that can be printed as output
while (len(worklist) != 0):
    
    #gets next two values off of the worklist
    s = worklist.pop(0)
    derivation = worklist.pop(0)
    
    #check is the current string is the correct length
    if (len(s.split()) > length):
        continue
    
    #check to see if the current string contains nonterminals
    #saves the value for the leftmost nonterminal in tmp
    contains_nonterms = False
    position = 0
    for i in s.split():
        if (i in nonterms):
            contains_nonterms = True
            temp = i
            break
        position += 1
    
    #checks to see if the current string is a viable print output
    #prints the string and possibly derivation list depending on the selection from command line
    if (contains_nonterms == False and len(s.split()) == length):
        #print derivation if specified
        if (s not in printed_strings):
            printed_strings.append(s)
            if (derivations):
                #print starting value
                print(derivation.pop(0) + " " + delimiter + " " + derivation.pop(0))
                for i in (derivation):
                    print(" " * (len(starter)+1) + delimiter + " " + i)
                print("")
            else:
                print (s)
            generated_strings += 1
    
    #if there are no more nonterminals then the while loop continues and those values dissappear
    if (contains_nonterms == False):
        continue
        
    #get all possible replacements for temp
    replacements = []
    replacements = dict[temp]
    
    #replace temp with each possible value
    #add the new value to the derivation list
    #add that new value with derivation list to the worklist
    for replacement in replacements:
        split_s = s.split()
        split_s[position] = replacement
        joined_s = ' '.join(split_s)
        new_derivation = derivation.copy()
        new_derivation.append(joined_s)
        worklist.append(joined_s)
        worklist.append(new_derivation)

#prints out how many strings were created for the specified length and adds spacing for the output
if (derivations == False):
    print("")
print("# of strings generated: ",generated_strings)
print("")
      
      
        