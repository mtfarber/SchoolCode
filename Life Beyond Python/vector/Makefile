CCFLAGS = -g -std=c++17  # Include debugging information and use the C++17 standard.
FILES = main.cpp Vector.hpp

all: a.out.gnu a.out.clang

a.out.gnu: $(FILES)
	g++ -o a.out.gnu $(CCFLAGS) main.cpp

a.out.clang: $(FILES)
	clang++ -o a.out.clang $(CCFLAGS) main.cpp

vector.zip: main.cpp Vector.hpp Makefile foo.cpp
	zip -r vector main.cpp Vector.hpp Makefile foo.cpp
	unzip -vl vector.zip
