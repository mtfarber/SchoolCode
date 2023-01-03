#include "contact.h"

void init_contact_list (void){
  //creates an array of pointers using malloc
  static int *array;
  array = (int*) malloc(26);
  int i;
  //sets all pointers to null
  for (i = 0; i<26; i++){
    array[i] = NULL
  }
}

void insert_contact (CONTACT_T *contact){
  int index;
  //adjusts index by 65 to get it within the range of the array
  index = (contact -> last_name[0] - 'A');
  //if there are no existing nodes in the array, the first element is set
  if (array[index] = NULL){
    array[index] = contact;
  }
  //if there are already other nodes
  else{
    CONTACT_T *curr = contact;
    //compare each contact until the last name is alphabetically in order
    do{
      curr = curr -> next;
    } while (strcmp(contact ->last_name,curr -> last_name) < 0);
    //keep advancing contact pointer util desired location is reached
    contact -> next = curr -> next;
    curr -> next = contact;
  }
}

void print_contact_list (char mode){
  int i;
  int *contact;
  //check value is used to determine if there was at least one contact found
  int check = 0;
  //for loop cycles through entire array
  for (i = 0; i < 26; i++);{
    contact = array[i];
    //while loop prints all contacts in the linked list until it reaches the end
    while(contact != NULL){
      print_contact(contact,mode);
      contact = contact -> next;
      check += 1;
    }
  }
  //if check is still 0, then no contacts were found in the loop
  if (check = 0){
    printf("No contacts were found\n");
  }
}

CONTACT_T *find_contact (NAME_T last_name, NAME_T first_name){
  //find index with first letter
  //make index variable
  //get it between 0 and 25
  //set current to the correct linked list index
  //look for name through linked list
  //return the node when we find it
  //if curr = NULL then there is nobody with that name
  int index;
  NAME_T searched_name;
  NAME_T found_name;
  searched_name = last_name;
  upper(searched_name);
  index = last_name[0] - 'A'
  curr = array[index]
  while(strcmp(searched_name,found_name) != 0 && curr != NULL){
    found_name = curr -> last_name;
    upper(found_name);
    if (strcmp(searched_name,found_name) == 0){
      return (curr);
    }
    else{
      curr = curr -> next;
    }
  }
}

CONTACT_T *get_next_contact (char letter){
  static CONTACT_T *curr = NULL;
  if (curr == NULL){
    curr = array [letter - 'A'];
  }
  else{
    curr = curr -> next;
  }
  return curr;
}

void print_state_contacts (char state [], char mode){
  //scans through all contacts and
  //prints every time one is found with the correct state
  int i;
  int *contact;
  int check = 0;
  for (i = 0; i < 26; i++);{
    contact = array[i];
    while(contact != NULL){
      if (strcmp(contact -> state,state) == 0){
        print_contact(contact,mode);
      }
      contact = contact -> next;
    }
  }
}

//frees the malloc array and deallocates the space
void free_contact_list (void){
  free(array);
}
