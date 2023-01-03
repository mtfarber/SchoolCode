#Debug this code as per the instructions, do not add lines for debugging
#Handle 2 exceptions (2 different possible errors) for full credit
#Handle additional errors for extra credit
#Adding code for exception handling is permitted

def std_dev(filename, n = True): #don't change this
    try:
        with open(filename, 'r', encoding = 'UTF-8') as file: #don't change this
            data = file.read() #don't change this
        try:
            data = [float(x) for x in data.split('\n')] #process data from file
        except ValueError as ve:
            print(ve)
            print('The file has a letter inside or is empty')
        else:
            mean = sum(data)/len(data) #find the mean
            sum_sq = 0
            for item in data: #find squared difference for each item, i.e. difference between item and mean squared        
                sum_sq += ((item - mean)**2)
            #Mean of sq diffs for both cases
            try:
                if n: 
                    mean_sq = sum_sq/len(data)
                else:
                    mean_sq = sum_sq/(len(data)-1)
                return mean_sq**0.5 #return sqrt of mean of sq diffs
            except ZeroDivisionError as zde:
                print(zde)
                print('Can not calculate standard deviation with one number.')
    except IOError as ie:
        print(ie)
        print('The file you are looking for does not exist.')