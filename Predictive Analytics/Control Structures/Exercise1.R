i = 4
for(j in seq(2, 8, by = 2)){
  i = i + j
  print(i)
}

x = seq(12, 22, by = 3)
for (i in 1:length(x)){
  y = i
  if((x[i] %% 5) == 1)
    y = c(y, 3*x[i])
}


x = sample(20:50,1)
print(x)
if (x < 35){
  x = x + 100
  y = sqrt(x)
} else{
  x = x + 1000000
  y = x^2
}
print(x)
print(y)


