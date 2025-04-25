double dot(const double *const x, const double *const y, const long int n)
{
  double sum = 0;
  for (int i = 0; i<n; i++){
    sum += (x[i] * y[i]);
  }

  return sum;
}
