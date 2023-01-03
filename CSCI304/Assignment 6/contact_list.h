#ifndef CONTACT_LIST
#define CONTACT_LIST

void init_contact_list (void);

void insert_contact (CONTACT_T *contact);

void print_contact_list (char mode);

CONTACT_T *find_contact (NAME_T last_name, NAME_T first_name);

CONTACT_T *get_next_contact (char letter);

void print_state_contacts (char state [], char mode);

#endif
