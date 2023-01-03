#include <string>
#include <iostream>
#include "cone.cpp"
#include "box.cpp"
#include "sphere.cpp"
#include "point.cpp"
#include "shape.cpp"

using namespace std;

void read_objs (Shape **list)

{
   Shape *node;         // variable used to create a new node each time through the loop
   double x, y, z, xx, yy, zz, rad;  // temporary variables used to read in values; fill in the rest
   string type, color, color2;     // temporary variables used to read in values; fill in the rest

   // initialize list
   *list = NULL;

   while (cin >> type) {

      if (type.compare ("sphere") == 0) {

         // fill in code here to read in the sphere values and create a new node
         cin >> x >> y >> z >> rad >> color;

         node = new Sphere (type, color, Point(x, y, z), rad);
      }

      else if (type.compare ("box") == 0) {

         // fill in code here to read in the box values and create a new node
         cin >> x >> y >> z >> xx >> yy >> zz >> color >> color2;

         node = new Box (type, color, color2, x, y, z, Point (xx, yy, zz));
      }

      else if (type.compare ("cone") == 0) {

         cin >> x >> y >> z >> xx >> yy >> zz >> rad >> color >> color2;

         node = new Cone (type, color, color2, Point (x, y, z), Point (xx, yy, zz), rad);
      }

      // link new node at front of list:
      //   set the next field of node to the beginning of the list (*list)
      node -> next = *list;
      //   set the beginning of the list to node
      *list = node;
   }
}

void print_objs (Shape *list){
  Shape* curr = list;
  while (curr != NULL){
    curr -> print_type();
    curr -> print_color();
    curr -> print_loc();
    cout << "Volume: " << curr -> compute_volume();
  }
}

int main (){
  Shape *list;
  read_objs (&list);
  print_objs (list);
  // add loop here to return any allocated space to the system
  Shape *temp;
  while (list -> next != NULL){
    *temp = *list;
    *list = *list -> next;
    delete temp;
  }

  return (0);
}
