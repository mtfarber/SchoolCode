#include <string>
#include <iostream>
#include <cmath>
#include "shape.h"
#include "point.h"

using namespace std;

//Point::Point (double x, double y, double z){
//  this -> x = x;
//  this -> y = y;
//  this -> z = z;
//}

double Point::length(){
  return (sqrt(x * x + y * y + z * z));
}

Point Point::operator- (Point& p){
Point pointer;
  pointer.x = this -> x - p.x;
  pointer.y = this -> y - p.y;
  pointer.z = this -> z - p.z;
  return (pointer);
}

void Point::print (void){
  cout << "Location: x: " << x << " y: " << y << " z: " << z;
}
