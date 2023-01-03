#include <string>
#include <iostream>
#include "sphere.h"
#include "shape.h"
#include "point.h"

using namespace std;

Sphere::Sphere (string type, string color, Point center, double radius)
: Shape (type, color, center)
{
  this -> center = center;
  this -> radius = radius;
}

double Sphere::compute_volume(void){
  return ((4/3) * 3.14159 * (radius * radius * radius));
}
