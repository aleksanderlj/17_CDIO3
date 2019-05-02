package test;

import dal.IUserDAO;
import dal.TransientDAO;
import dal.dto.IUserDTO;
import dal.dto.UserDTO;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TransientUI {
    TransientDAO tDAO = new TransientDAO();
    Scanner sc = new Scanner(System.in);
    TUI tui = new TUI();
    public void ChooseTorP() throws IUserDAO.DALException {
        System.out.println("Menu:\n"
                + "1.Transient data\n"
                + "2. Persistent data\n");

        int choice = sc.nextInt();
        if(choice==1){
            menu();
        }
        else if(choice==2){
            tui.run();
        }
    }
    public void menu() throws IUserDAO.DALException {
        IUserDTO user = new UserDTO();
        String choice;
        do {
            System.out.println("Transient Menu:\n"
                    + "1. Show user\n"
                    + "2. Show all users\n"
                    + "3. Create user\n"
                    + "4. Edit user\n"
                    + "5. Delete user\n"
                    + "6. Close program\n");

            choice = sc.next();
            if(choice.equals("1")){
                System.out.println("Enter userId: ");
                System.out.println(tDAO.getUser(sc.nextInt()).toString());
            }
            else if(choice.equals("2")){
                for(int i = 0; i<tDAO.getUserList().size();i++){
                    System.out.println(tDAO.getUserList().get(i).toString());
                }
            }
            else if(choice.equals("3")){
                tDAO.createUser(user);
            }
            else if(choice.equals("4")){
                System.out.println("Enter userId");
                int userId = sc.nextInt();
                tDAO.getUser(userId);
                System.out.println("Change Menu:\n"
                        + "1. Change username\n"
                        + "2. Change ini\n"
                        + "3. Change roles\n");
                int choiceof = sc.nextInt();
            }
            else if(choice.equals("5")){
                System.out.println("Enter userId");
                tDAO.deleteUser(sc.nextInt());
            }
            else if(choice.equals("6")){
                break;
            }

        } while (!choice.equalsIgnoreCase("6"));
        System.out.println("...Program closed");
        System.exit(0);
    }
    int UserIdCounter = 6;

    public int setUserID(){
        return ++UserIdCounter;
    }
    public String setUserName(){
        System.out.println("Enter username: ");
        String userName = sc.next();
        return userName;
    }
    public String setInitials(){
        System.out.println("Enter initials: ");
        String initials = sc.next();
        return initials;
    }
    public void setRoles(IUserDTO user){
        System.out.println("Enter number of roles: ");
        int n = sc.nextInt();
        for(int i = 0; i<n;i++) {
            System.out.println("Enter roles: ");
            user.addRole(sc.next());
        }
    }
}
