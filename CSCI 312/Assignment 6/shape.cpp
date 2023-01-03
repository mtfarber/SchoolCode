#include <string>
#include <iostream>
#include "shape.h"
#include "point.h"

using namespace std;

Shape::Shape (string type, string color, Point loc){
  this -> type = type;
  this -> color = color;
  this -> loc = loc;
}

void Shape::print_color(void){
  cout << "Color: " << color;
}

//double Shape::compute_volume(void);

void Shape::print_type(void){
  cout << "Name: " << type;
}

void Shape::print_loc(void){
  loc.print();
}
