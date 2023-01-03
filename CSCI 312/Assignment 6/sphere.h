#ifndef SPHERE_H
#define SPHERE_H

#include <string>
#include "shape.h"
#include "point.h"

using namespace std;

class Sphere : public Shape {

  private:
    Point center;
    double radius;

  public:
    Sphere (string type, string color, Point center, double radius);

    double compute_volume(void);
};

#endif
