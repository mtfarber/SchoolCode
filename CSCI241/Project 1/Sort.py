import time
import random

def insertion_sort(arr):
    for k in range(1, len(arr)):
        cur = arr[k]
        j = k
        while j > 0 and arr[j-1] > cur:
            arr[j] = arr[j-1]
            j = j - 1
        arr[j] = cur

def selection_sort(arr):
    for k in range(0, len(arr)):
        j = k
        minval = arr[k]
        jindex = j
        while j >= 0 and j<len(arr):
            if arr[j] < minval:
                minval = arr[j]
                jindex = j
            j += 1
        c = arr[k]
        arr[k] = arr[jindex]
        arr[jindex] = c

if __name__ == '__main__':
    inc1000 = [None] * 1000
    for i in range (1000):
        inc1000[i] = i
    start = time.process_time()
    insertion_sort(inc1000)
    end = time.process_time()
    print('One Thousand Increasing Insertion: ' + '{:.6f}'.format(end-start))

    inc1000 = [None] * 1000
    for i in range (1000):
        inc1000[i] = i
    start = time.process_time()
    selection_sort(inc1000)
    end = time.process_time()
    print('One Thousand Increasing Selection: ' + '{:.6f}'.format(end-start))

    inc2500 = [None] * 2500
    for i in range (2500):
        inc2500[i] = i
    start = time.process_time()
    insertion_sort(inc2500)
    end = time.process_time()
    print('Two Thousand Five Hundred Increasing Insertion: ' + '{:.6f}'.format(end-start))

    inc2500 = [None] * 2500
    for i in range (2500):
        inc2500[i] = i
    start = time.process_time()
    selection_sort(inc2500)
    end = time.process_time()
    print('Two Thousand Five Hundred Increasing Selection: ' + '{:.6f}'.format(end-start))

    inc5000 = [None] * 5000
    for i in range (5000):
        inc5000[i] = i
    start = time.process_time()
    insertion_sort(inc5000)
    end = time.process_time()
    print('Five Thousand Increasing Insertion: ' + '{:.6f}'.format(end-start))

    inc5000 = [None] * 5000
    for i in range (5000):
        inc5000[i] = i
    start = time.process_time()
    selection_sort(inc5000)
    end = time.process_time()
    print('Five Thousand Increasing Selection: ' + '{:.6f}'.format(end-start))

    inc7500 = [None] * 7500
    for i in range (7500):
        inc7500[i] = i
    start = time.process_time()
    insertion_sort(inc7500)
    end = time.process_time()
    print('Seven Thousand Five Hundred Increasing Insertion: ' + '{:.6f}'.format(end-start))

    inc7500 = [None] * 7500
    for i in range (7500):
        inc7500[i] = i
    start = time.process_time()
    selection_sort(inc7500)
    end = time.process_time()
    print('Seven Thousand Five Hundred Increasing Selection: ' + '{:.6f}'.format(end-start))

    inc10000 = [None] * 10000
    for i in range (10000):
        inc10000[i] = i
    start = time.process_time()
    insertion_sort(inc10000)
    end = time.process_time()
    print('Ten Thousand Increasing Insertion: ' + '{:.6f}'.format(end-start))

    inc10000 = [None] * 30000
    for i in range (30000):
        inc10000[i] = i
    start = time.process_time()
    selection_sort(inc10000)
    end = time.process_time()
    print('Thirty Thousand Increasing Selection: ' + '{:.6f}'.format(end-start))


    dec1000 = [None] * 1000
    for i in range(1000):
        dec1000[i] = -i
    start = time.process_time()
    insertion_sort(dec1000)
    end = time.process_time()
    print('One Thousand Decreasing Insertion: ' + '{:.6f}'.format(end-start))

    dec1000 = [None] * 1000
    for i in range(1000):
        dec1000[i] = -i
    start = time.process_time()
    selection_sort(dec1000)
    end = time.process_time()
    print('One Thousand Decreasing Selection: ' + '{:.6f}'.format(end-start))

    dec2500 = [None] * 2500
    for i in range(2500):
        dec2500[i] = -i
    start = time.process_time()
    insertion_sort(dec2500)
    end = time.process_time()
    print('Two Thousand Five Hundred Decreasing Insertion: ' + '{:.6f}'.format(end-start))

    dec2500 = [None] * 2500
    for i in range(2500):
        dec2500[i] = -i
    start = time.process_time()
    selection_sort(dec2500)
    end = time.process_time()
    print('Two Thousand Five Hundred Decreasing Selection: ' + '{:.6f}'.format(end-start))

    dec5000 = [None] * 5000
    for i in range(5000):
        dec5000[i] = -i
    start = time.process_time()
    insertion_sort(dec5000)
    end = time.process_time()
    print('Five Thousand Decreasing Insertion: ' + '{:.6f}'.format(end-start))

    dec5000 = [None] * 5000
    for i in range(5000):
        dec5000[i] = -i
    start = time.process_time()
    selection_sort(dec5000)
    end = time.process_time()
    print('Five Thousand Decreasing Selection: ' + '{:.6f}'.format(end-start))

    dec7500 = [None] * 7500
    for i in range(7500):
        dec7500[i] = -i
    start = time.process_time()
    insertion_sort(dec7500)
    end = time.process_time()
    print('Seven Thousand Five Hundred Decreasing Insertion: ' + '{:.6f}'.format(end-start))

    dec7500 = [None] * 7500
    for i in range(7500):
        dec7500[i] = -i
    start = time.process_time()
    selection_sort(dec7500)
    end = time.process_time()
    print('Seven Thousand Five Hundred Decreasing Selection: ' + '{:.6f}'.format(end-start))

    dec10000 = [None] * 10000
    for i in range(10000):
        dec10000[i] = -i
    start = time.process_time()
    insertion_sort(dec10000)
    end = time.process_time()
    print('Ten Thousand Decreasing Insertion: ' + '{:.6f}'.format(end-start))

    dec10000 = [None] * 10000
    for i in range(10000):
        dec10000[i] = -i
    start = time.process_time()
    selection_sort(dec10000)
    end = time.process_time()
    print('Ten Thousand Decreasing Selection: ' + '{:.6f}'.format(end-start))


    arand1000 = [None] * 1000
    brand1000 = [None] * 1000
    for i in range(1000):
        randnum = random.randint(0,1000)
        arand1000[i] = randnum
        brand1000[i] = randnum
    start = time.process_time()
    insertion_sort(arand1000)
    end = time.process_time()
    print('One Thousand Random Insertion: ' + '{:.6f}'.format(end-start))

    start = time.process_time()
    selection_sort(brand1000)
    end = time.process_time()
    print('One Thousand Random Selection: ' + '{:.6f}'.format(end-start))

    arand2500 = [None] * 2500
    brand2500 = [None] * 2500
    for i in range(2500):
        randnum = random.randint(0,2500)
        arand2500[i] = randnum
        brand2500[i] = randnum
    start = time.process_time()
    insertion_sort(arand2500)
    end = time.process_time()
    print('Two Thousand Five Hundred Random Insertion: ' + '{:.6f}'.format(end-start))

    start = time.process_time()
    selection_sort(brand2500)
    end = time.process_time()
    print('Two Thousand Five Hundred Random Selection: ' + '{:.6f}'.format(end-start))

    arand5000 = [None] * 5000
    brand5000 = [None] * 5000
    for i in range(5000):
        randnum = random.randint(0,5000)
        arand5000[i] = randnum
        brand5000[i] = randnum

    start = time.process_time()
    insertion_sort(arand5000)
    end = time.process_time()
    print('Five Thousand Random Insertion: ' + '{:.6f}'.format(end-start))

    start = time.process_time()
    selection_sort(brand5000)
    end = time.process_time()
    print('Five Thousand Random Selection: ' + '{:.6f}'.format(end-start))

    arand7500 = [None] * 7500
    brand7500 = [None] * 7500
    for i in range(7500):
        randnum = random.randint(0,7500)
        arand7500[i] = randnum
        brand7500[i] = randnum

    start = time.process_time()
    insertion_sort(arand7500)
    end = time.process_time()
    print('Seven Thousand Five Hundred Random Insertion: ' + '{:.6f}'.format(end-start))

    start = time.process_time()
    selection_sort(brand7500)
    end = time.process_time()
    print('Seven Thousand Five Hundred Random Selection: ' + '{:.6f}'.format(end-start))

    arand10000 = [None] * 10000
    brand10000 = [None] * 10000
    for i in range(10000):
        randnum = random.randint(0,10000)
        arand10000[i] = randnum
        brand10000[i] = randnum

    start = time.process_time()
    insertion_sort(arand10000)
    end = time.process_time()
    print('Ten Thousand Random Insertion: ' + '{:.6f}'.format(end-start))

    start = time.process_time()
    selection_sort(brand10000)
    end = time.process_time()
    print('Ten Thousand Random Selection: ' + '{:.6f}'.format(end-start))
