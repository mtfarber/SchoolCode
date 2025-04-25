#ifndef __FRACTION_HPP__
#define __FRACTION_HPP__

#include <ostream>

namespace csci320 {
  int gcd(int m, int n);
  
  class Fraction {
  public:
    Fraction() : a(0), b(0) {}
    Fraction(int _a_, int _b_) : a(_a_), b(_b_)
    {
      /*
       * After we initialize the numerator and denominator we clean up the
       * representation of the fraction
       */
      
      // Transfer any negative sign to the numerator.
      auto sign = (a * b < 0) ? -1 : +1;
      a = sign * std::abs(a);
      b = std::abs(b);

      // Cancel the signs if a and b are both negative.
      if ((a < 0) && (b < 0)) {
        a = std::abs(a);
        b = std::abs(b);
      }

      // Remove the greatest common divisor from the numerator and denominator.
      auto d = gcd(a, b);
      a /= d;
      b /= d;
    }

    // Unary minus (changes the sign).
    Fraction operator-() {return Fraction(-a, b);}

    // Friends of a class can access the private methods and variables of
    // a class object.  Some people feel strongly that you should not use
    // friend functions since they have access to the implementation of an
    // object.  Instead, you should have get and set helper functions in the
    // class to control access.
    
    friend Fraction operator+(const Fraction &f, const Fraction &g);
    friend Fraction operator+(const Fraction &f, const int &n);
    friend Fraction operator+(const int &n, const Fraction &g);

    friend Fraction operator-(const Fraction &f, const Fraction &g);
    friend Fraction operator-(const Fraction &f, const int &n);
    friend Fraction operator-(const int &n, const Fraction &g);
    
    friend Fraction operator*(const Fraction &f, const Fraction &g);
    friend Fraction operator*(const Fraction &f, const int &n);
    friend Fraction operator*(const int &n, const Fraction &g);
    
    friend Fraction operator/(const Fraction &f, const Fraction &g);
    friend Fraction operator/(const Fraction &f, const int &n);
    friend Fraction operator/(const int &n, const Fraction &g);
    
    friend std::ostream &operator<<(std::ostream &os, const Fraction &f);

  private:
    int a; // The numerator.
    int b; // The denominator.
  };
};
#endif
