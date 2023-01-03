#include <iostream>
#include <vector>

// Class definition
class Student {

  std::string name;
  int id;
  int score;

public:
  Student(std::string new_name, int new_id, int new_score): name(new_name), id(new_id), score(new_score) {}
  ~Student() {}

  int get_score() {
    return score;
  }
};

// TODO: complete the function below to calculate the average score of all the given students in "vec"
double calculate_average (std::vector<Student> vec) {
  double avg = 0.0;
  double total = 0.0;
  for (Student x : vec){
      total += x.get_score();
  }
  avg = total/vec.size();
  //std::cout << "Please complete the average function!" << std::endl;
  return avg;
}

// Setup vec
std::vector<Student> vec = {
  Student("Rich", 931000000, 90),
  Student("Joe", 931000001, 95),
  Student("Frank", 930000002, 89),
  Student("Luis", 931000003, 75),
  Student("Liz", 931000004, 80),
  Student("Jason", 936000005, 65),
  Student("Richael", 931000006, 98),
  Student("Ross", 931000007, 76),
  Student("Sam", 937000008, 83),
  Student("Rose", 931000009, 92),
  Student("Sarah", 931000010, 72)
};

int main(int argc, char **argv)
{
  // Print out the vector
  std::cout << "Scores = { ";
  for (Student x : vec) {
      std::cout << x.get_score() << ", ";
  }
  std::cout << "}; \n";

  double avg = 0.0;

  // Calling the function
  avg = calculate_average(vec);

  std::cout << "Result: " << avg << std::endl;

  return 0;
}
