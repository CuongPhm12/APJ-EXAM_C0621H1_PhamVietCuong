package view;

import controller.ContactManagement;
import model.Contact;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactView {
public static Scanner scanner = new Scanner(System.in);
public static ContactManagement contactManagement = new ContactManagement();
public static Contact contact = new Contact();

    static {
        contactManagement.addNew(new Contact("0123456789", "Nhóm 5", "Tiến Cường", "Nam", "HN", "05/06/2000", "abcxyz@gmail.com"));
        contactManagement.addNew(new Contact("0123456781", "Nhóm 5", "Việt Cường", "Nam", "HD", "04/6/1990", "abcxyz@gmail.com"));
        contactManagement.addNew(new Contact("0123456782", "Nhóm 5", "Định", "Nam", "HB", "04/6/1990", "abcxyz@gmail.com"));
        contactManagement.addNew(new Contact("0123456783", "Nhóm 1", "Duy", "Nam", "BG", "05/7/96", "abcxyz@gmail.com"));
        contactManagement.addNew(new Contact("0123456784", "Nhóm 1", "Trường", "Nam", "NA", "08/04/99", "abcxyz@gmail.com"));
        contactManagement.addNew(new Contact("0123456785", "Nhóm 1", "Tuấn", "Nam", "NĐ", "30/03/99", "abcxyz@gmail.com"));

    }

public void run(){
    String choice;
do{
    menuContact();
    choice = scanner.nextLine();
    switch (choice) {
        case "1": {
            displayContactList();
            break;
        }
        case "2": {
            addContact();
            break;
        }
        case "3": {
            updateContact();
            break;
        }
        case "4": {
            deleteContact();
            break;
        }
        case "5": {
            searchContactByPhoneNumberOrName();
            break;
        }
        case "6": {
            readDataToFile();
            break;
        }
        case "7": {
            writeDataToFile();
            break;
        }
        default: {
            System.out.println("Không hợp lệ !!! mời bạn chọn lại dùm");
        }
    }

} while (!choice.equals("8"));
}

    private void writeDataToFile() {
        System.out.println("Bạn có muốn ghi lại toàn bộ file không?");
        System.out.println("Nhấn Y để tiếp tục; nhấn phím bất kỳ để quay lại");
        String check = scanner.nextLine();
        if(!check.equals("Y")){
            return;
        }
        List<Contact> contactList = contactManagement.getContactList();
        try {
            FileWriter fileWriter = new FileWriter("data/contacts.csv");
            BufferedWriter bw = new BufferedWriter(fileWriter);
            for (Contact contact:contactList
            ) {
                bw.write(String.valueOf(contact));
                bw.newLine();
            }
            bw.close();
            System.out.println("Ghi file thành công");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readDataToFile() {
    try{
        FileReader fileReader =new FileReader("data/contacts.csv");
        BufferedReader br =new BufferedReader(fileReader);
        String line;
        while ((line = br.readLine()) != null){
            System.out.println(Arrays.toString(line.split(",")));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    private void searchContactByPhoneNumberOrName() {
        System.out.println("Nhập tên bạn cần tìm");
        String check = scanner.nextLine();
        int indexName = contactManagement.findbyName(check);

        if(indexName !=-1){
            System.out.println("Thông tin danh bạ bạn cần tìm");
            System.out.println(contactManagement.getContactList().get(indexName));
        }else{
            System.out.println("Không tìm thấy ");

        }

    }

    private void deleteContact() {
        System.out.println("Xóa liên hệ");
        System.out.println("Vui lòng nhập số điện thoại bạn cần xóa");
        String numberPhone = scanner.nextLine();
        int index =contactManagement.findByNumberPhone(numberPhone);
        if(index != -1){
            System.out.println("Bạn có muốn xóa số điện thoại này khỏi danh bạ không?");
            System.out.println("Bấm y để xóa, bấm phím bất kỳ để thoát");
            String check = scanner.nextLine();
            if(check.equals("y")){
                contactManagement.removeByNumberPhone(numberPhone);
                System.out.println("Xóa thành công");

            }
        }else{
            System.out.println("Không tìm thấy. Vui lòng nhập lại");
            deleteContact();
        }
    }


    private void displayContactList() {
    contactManagement.showAll();
    }

    private void addContact() {
    Contact contact = new Contact();
    contactManagement.addNew(contact);
        System.out.println("Thêm mới thành công");

    }

    private void updateContact() {
        System.out.println("Cập nhật danh bạ");
        System.out.println("Mời bạn nhập vào số điện thoại cần cập nhật");
        String phoneNumber =scanner.nextLine();
        int index = contactManagement.findByNumberPhone(phoneNumber);
        if(index != -1){
            Contact contact = updatingContact();
            contactManagement.addNew(contact);
            System.out.println("Cập nhật thành công");

        }

    }

    private Contact updatingContact() {
        System.out.println("Cập nhật liên hệ");
        String numberPhone,contactGroup, name,gender,address,birthday,email;
        boolean isValidNumber,isValidEmail;
        int count = 0;
        do {
            if (count > 0) {
                System.out.println("Bạn nhập sai rồi mời nhập lại");
            }
            System.out.println("Nhập số điện thoại: ");
            numberPhone = scanner.nextLine();
            isValidNumber = contact.validateNumber(numberPhone);
            if (!isValidNumber) {
                System.out.println("Hãy nhập số từ 0 đến 9");
            }
            System.out.print("Nhập nhóm của danh bạ: ");
            contactGroup = scanner.nextLine();
            if(contactGroup.equals("")) {
                System.out.println("Không được để rỗng");
            }
            System.out.print("Nhập họ tên: ");
            name = scanner.nextLine();
            if(name.equals("")) {
                System.out.println("Không được để rỗng");
            }
            System.out.print("Nhập giới tính: ");
            gender = scanner.nextLine();
            if(gender.equals("")) {
                System.out.println("Không được để rỗng");
            }
            System.out.print("Nhập địa chỉ: ");
            address = scanner.nextLine();
            if(address.equals("")) {
                System.out.println("Không được để rỗng");
            }
            System.out.print("Nhập ngày tháng năm sinh: ");
            birthday = scanner.nextLine();
            if(birthday.equals("")) {
                System.out.println("Không được để rỗng");
            }
            System.out.print("Nhập email: ");
            email = scanner.nextLine();
            isValidEmail = contact.validateEmail(email);
            if (!isValidEmail) {
                System.out.println("Định dạng email chưa đúng");
            }
            count++;
        } while (contactGroup.equals("") || name.equals("") || gender.equals("") || address.equals("") || birthday.equals("") || !isValidEmail || !isValidNumber);
        return new Contact(numberPhone, contactGroup, name, gender, address, birthday, email);
    }
    


    private void menuContact() {
        System.out.println("=====CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ=====");
        System.out.println("1.Xem danh sách");
        System.out.println("2.Thêm mới");
        System.out.println("3.Cập nhật");
        System.out.println("4.Xóa");
        System.out.println("5.Tìm kiếm");
        System.out.println("6.Đọc từ File");
        System.out.println("7.Ghi từ File");
        System.out.println("8.Thoát");

    }


}


