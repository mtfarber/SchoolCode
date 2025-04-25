#include <iostream>
#include <ostream>

#include "fraction.hpp"

namespace csci320 {
  /*
   * These operators are not invoked as methods of the class Fraction.
   * Instead, they are called when operations with specified types of
   * operands are encountered.
   */

  std::ostream &operator<<(std::ostream &os, const Fraction &f)
  {
    /*
     * Output operator.
     *
     * Fractions that are integers are written as integers.
     */

    auto sign = (f.a * f.b < 0) ? '-' : ' ';
    if (std::abs(f.b) != 1) {
      os << sign << std::abs(f.a) << '/' << std::abs(f.b);
    }
    else {
      os << sign << std::abs(f.a);
    }

    return os;
  }

  /*********************************************************
   * Adddition.
   */

  Fraction operator+(const Fraction &f, const Fraction &g)
  {
    /*
     * Compute f + g for Fraction f and Fraction g.
     */

    return Fraction(f.a * g.b + f.b * g.a, f.b * g.b);
  }

  Fraction operator+(const Fraction &f, const int &n)
  {
    /*
     * Compute f + n for Fraction f and int n.
     */

    return f + Fraction(n, 1);
  }

  Fraction operator+(const int &n, const Fraction &g)
  {
    /*
     * Compute n + f  for int n and Fraction f.
     */

    return Fraction(n, 1) + g;
  }

  /*********************************************************
   * Subtraction.
   */

  Fraction operator-(const Fraction &f, const Fraction &g)
  {
    /*
     * Compute f - g for Fraction f and Fraction g.
     */
     return Fraction(f.a * g.b - f.b * g.a, f.b * g.b);
  }

  Fraction operator-(const Fraction &f, const int &n)
  {
    /*
     * Compute f - n for Fraction f and int n.
     */
     return f - Fraction(n, 1);
  }

  Fraction operator-(const int &n, const Fraction &f)
  {
    /*
     * Compute n - f for Fraction f and int n.
     */
     return Fraction(n, 1) - f;
  }

  /*********************************************************
   * Multiplication.
   */

Fraction operator*(const Fraction &f, const Fraction &g)
{
  /*
   * Compute f * g for Fraction f and Fraction g.
   */
   return Fraction(f.a * g.a, f.b * g.b);
}

Fraction operator*(const Fraction &f, const int &n)
{
  /*
   * Compute f * n for Fraction f and int n.
   */
   return f * Fraction(n, 1);
}

Fraction operator*(const int &n, const Fraction &f)
{
  /*
   * Compute n * f for Fraction f and int n.
   */
   return Fraction(n, 1) * f;
}


  /*********************************************************
   * Division.
   */
   Fraction operator/(const Fraction &f, const Fraction &g)
   {
     /*
      * Compute f / g for Fraction f and Fraction g.
      */
      return Fraction(f.a * g.b, f.b * g.a);
   }

   Fraction operator/(const Fraction &f, const int &n)
   {
     /*
      * Compute f / n for Fraction f and int n.
      */
      return f / Fraction(n, 1);
   }

   Fraction operator/(const int &n, const Fraction &f)
   {
     /*
      * Compute n / f for Fraction f and int n.
      */
      return Fraction(n, 1) / f;
   }

  /*********************************************************
   * Greatest common divisor.
   *
   * This is just a regular function.
   */

  int gcd(int m, int n)
  {
    /*
     * Compute the greatest common divisor of m and n using recursion.
     * This function always returns a nonnegative result.
     */

    // We compute the gcd of |m| and |n| to avoid applying the remainder
    // operation % to negative numbers.
    m = std::abs(m);
    n = std::abs(n);

    if (m == n) {
      return m;
    }
    else if (n == 0) {
      return m;
    }
    else {
      return gcd(n, m % n);
    }
  }
};
