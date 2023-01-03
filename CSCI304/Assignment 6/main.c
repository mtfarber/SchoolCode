#include <stdio.h>
#include "contact.h"
#include "contact_list.h"
#include "utils.h"

void main (int argc, char *argv []){
  //sets default mode to compact output
  char mode = 'c';
  char option;
//checks the argument to make sure a file name is specified
  if (argc = 2){
//initializes the contact list
//creates the nodes to populate the array with the file name
//prints out all of the contacts from the array
    init_contact_list();
    process_file(argv[1]);
    print_contact_list(mode);
//prints the menu option until an acceptable input is entered
    do{
      option = print_menu();

      switch (option){
        case 'C':
          mode = print_format_menu();
        case 'I':
          find_individual(mode);
        case 'T':
          print_tab(mode);
        case 'S':
          process_state_contacts(state,mode);
        case 'Q':
          break;
        }

      } while (option != 'Q');
      //frees the array at the end of the program
      free_contact_list();
  }
  else{
    //if the user doesn't enter a file name then an error is returned
    printf("Contact file not specified\n");
    exit (EXIT_FAILURE);
  }
}

//takes in file and calls create contacts for each line
void process_file (char *filename){
  char line[200];
  CONTACT_T *contact_ptr;
  //open file
  //read and get rid of first lines
  FILE *fp;
  fp = fopen(filename, "r");
  fscanf(fp,"%*s ");
  while (fscanf()==12){
    //create CONTACT
    //pass pointer to insert contact
    contact_ptr = create_contact(line);
    insert_contact(contact_ptr);
  }
  fclose(fp);
}

//prints out normal menu options and asks for user input
char print_menu (void){
  char new_mode;
  int tracker = 0;
  char valid_input[] = "CITSQ";

  do{
    if (tracker > 0){
      printf("\nInvalid Option: %s\n\n",new_mode);
    }
    printf("Please select one of the following:\n\n");
    printf("c  Change output format\n");
    printf("i  Search for Individual\n");
    printf("t  Print tab\n");
    printf("s  Look up by state\n");
    printf("q  Quit\n\n");
    printf("Option: ");
    scanf("%s",new_mode);
    upper(new_mode);
    tracker +=1;
  } while((strlen(mode) !=1) || (!strchr(valid_input,new_mode)));

  return new_mode;
}

//prints out secondary menu
//user can select a new format
char print_format_menu (void){
  char mode;
  printf("Please select one of the following:\n\n");
  printf("c  Condensed output format\n");
  printf("f  Full output format\n\n");
  printf("Option: \n");
  scanf("%s",mode);
  return(mode);
}

//calls the find_contact function to find the person with the desired
//first and last name
void find_individual (char mode){
  //declare variables to hold first and last name
  //declare contact pointer for the node with the correct info
  //ask user for first and last name
  //call upper on both
  NAME_T first;
  NAME_T last;
  printf("Please enter last name: ");
  scanf("%s",last);
  upper(last);
  printf("\nPlease enter first name: ");
  scanf("%s",first);
  upper(first);
  CONTACT_T *curr;
  //call find contact in contact list
  //if pointer is null it couldnt find it and print not found
  //if its found, call print contact with the pointer and mode
  if (curr = find_contact(last,first)){
    print_contact(curr,mode);
  }
  else{
    //contact not found
    printf("Contact not found\n\n");
  }
}

void print_tab (char mode){
  //looks for all users with first names starting with the desired letter
  char letter;
  CONTACT_T *contact;
  printf("Please enter last name tab letter: ");
  scanf("%s",letter);
  upper(letter);
  //calls get_next_contact until there are no more contacts with that letter
  if (contact = get_next_contact(letter)){
    while(contact != NULL){
      print_contact(contact,mode);
      get_next_contact();
    }
  }
  else{
    printf("Contact not found\n\n");
  }
}

//asks the user for input and calls print_state_contacts with the chosen state
void process_state_contacts (char mode){
  char state[2];
  printf("Please enter two letter state abbreviation: ");
  scanf("%s",state);
  upper(state);
  //each contact with that state is printed out with this function immeadiately
  //nothing is returned
  print_state_contacts(state,mode);
}
