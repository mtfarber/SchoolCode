def median(data):
    midpt = len(data)/2
    data = data.sort()       
    if len(data) % 2 == 0:
        return data[midpt] + data[midpt+1]/2
    else:
        return data[midpt]