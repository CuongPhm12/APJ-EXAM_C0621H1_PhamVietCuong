package controller;

import model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactManagement {
    private final List<Contact> contactList= new ArrayList<>();

    public List<Contact>getContactList(){
        return contactList;
    }

    @Override
    public String toString() {
        return "ContactManagement{" +
                "contactList=" + contactList +
                '}';
    }

    public void showAll(){
        if(contactList.isEmpty()){
            System.out.println("Danh bạ không có số điện thoại nào");
        }
        int count = 0;
        for (Contact contact:contactList
             ) {
            System.out.println(contact);
        }
    }

    public void addNew(Contact contact){
        contactList.add(contact);
    }

    public void removeByNumberPhone(String numberPhone){
        int index = findByNumberPhone(numberPhone);
        contactList.remove(index);

    }
    public int findbyName(String name){
        int index = -1;
        for (int i = 0; i < contactList.size(); i++) {
            if(contactList.get(i).getName().equals(name)){
                index = i;
                break;
            }
        }
        return index;
    }

    public int findByNumberPhone(String numberPhone) {
        int index = -1;
        for (int i = 0; i < contactList.size(); i++) {
            if(contactList.get(i).getNumberPhone().equals(numberPhone)){
                index =i;
                break;
            }
        }
        return index;
    }

}
