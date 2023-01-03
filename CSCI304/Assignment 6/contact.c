
#include <stdio.h>
#include <string.h>
#include "contact.h"

//performs a string copy from the original contact node to a new new_mode
//sets that new nodes next pointer to NULL
CONTACT_T *create_contact (CONTACT_T *contact){
  CONTACT_T *contact_ptr;
  contact_ptr = (int*) malloc(sizeof(contact));
  strcpy(contact_ptr -> first_name, contact -> first_name);
  strcpy(contact_ptr -> last_name, contact -> last_name);
  strcpy(contact_ptr -> co_name, contact -> co_name);
  strcpy(contact_ptr -> addr, contact -> addr);
  strcpy(contact_ptr -> city, contact -> city);
  strcpy(contact_ptr -> county, contact -> county);
  strcpy(contact_ptr -> state, contact -> state);
  strcpy(contact_ptr -> zip, contact -> zip);
  strcpy(contact_ptr -> phone1, contact -> phone1);
  strcpy(contact_ptr -> phone2, contact -> phone2);
  strcpy(contact_ptr -> email, contact -> email);
  strcpy(contact_ptr -> web, contact -> web);
  node -> next = NULL;
  return (contact_ptr);
}

//choses which type of print to perform based on mode type
void print_contact (CONTACT_T *contact, char mode){
  //if selected mode is condensed
  if (mode = 'C'){
    printf("%18s%18s%15s%s\n",contact ->last_name, contact -> first_name, contact ->phone1, contact ->email);
  }
  //if selected mode is full
  else{
    printf("Name:    %s %s\n",contact -> first_name, contact -> last_name);
    printf("Address: %s, %s, %s %s  (%s)\n", contact -> addr,contact -> city, contact -> state, contact -> zip, contact ->county);
    printf("Phone:   %s (primary)  %s\n",contact.phone1, contact ->phone2);
    printf("email:   %s\n", contact -> email);
    printf("web:     %s\n", contact ->web);
  }
}
