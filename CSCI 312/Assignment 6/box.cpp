#include <string>
#include <iostream>
#include "box.h"
#include "shape.h"
#include "point.h"

using namespace std;

Box::Box (string type, string color, string tbcolor, double length, double width, double height, Point loc)
: Shape (type, color, loc)

{
  this -> tbcolor = tbcolor;
  this -> length = length;
  this -> width = width;
  this -> height = height;
}

void Box::print_color(void){
  cout << "Side Color: " << color << " Top/Base Color: " << tbcolor;
}

double Box::compute_volume(void){
  return(length*width*height);
}
