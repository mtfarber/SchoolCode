rm(list=ls())
nRows = 20
nCols = 10

my.matrix = matrix(data = 0,nrow = nRows,ncol = nCols)
for(i in 1:nRows){
  print(paste("Populating row ",i, sep= ""))
  for (j in 1:nCols){
    my.matrix[i,j] = (i^2 + j^3)
  }
}
my.matrix

