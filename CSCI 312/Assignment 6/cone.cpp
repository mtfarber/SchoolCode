#include <string>
#include <iostream>
#include "cone.h"
#include "shape.h"
#include "point.h"

using namespace std;


Cone::Cone (string type, string color, string bcolor, Point center, Point apex, double radius)
           : Shape (type, color, center)

{
   this -> bcolor = bcolor;
   this -> center = center;
   this -> apex = apex;
   this -> radius = radius;
}


void Cone::print_color (void)

{
   cout << "Color: "  << color << "  Base Color: " << bcolor;
}


double Cone::compute_volume (void)

{
   Point diff;
   double h;

   diff = apex - center;
   h = diff.length ();

   return (3.14159 * radius * radius * radius * h / 3);
}
