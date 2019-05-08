package test;
import dal.IUserDAO;
import dal.TransientDAO;
import dal.dto.IUserDTO;
import java.util.Scanner;

public class TransientUI {
    private TransientDAO tDAO = new TransientDAO();
    private Scanner sc = new Scanner(System.in);
    private TUI tui = new TUI();
    public void ChooseTorP() throws IUserDAO.DALException {
        System.out.println("Menu:\n"
                + "1. Transient data\n"
                + "2. Persistent data\n");

        int choice = sc.nextInt();
        if(choice==1){
            menu();
        }
        else if(choice==2){
            tui.run();
        }
    }
    private void menu(){
        String choice;
        label:
        do {
            System.out.println("Transient Menu:\n"
                    + "1. Show user\n"
                    + "2. Show all users\n"
                    + "3. Create user\n"
                    + "4. Edit user\n"
                    + "5. Delete user\n"
                    + "6. Close program\n");

            choice = sc.next();
            switch (choice) {
                case "1":
                    showUser();
                    break;
                case "2":
                    showAllUsers();
                    break;
                case "3":
                    createUser();
                    break;
                case "4":
                    editUser();
                    break;
                case "5":
                    deleteUser();
                    break;
                case "6":
                    break label;
            }

        } while (!choice.equalsIgnoreCase("6"));
        System.out.println("...Program closed");
        System.exit(0);
    }
    public void showUser(){
        System.out.println("Enter userId: ");
        System.out.println(tDAO.getUser(sc.nextInt() - 1).toString());
    }
    public void showAllUsers(){
        for (int i = 0; i < tDAO.getUserList().size(); i++) {
            System.out.println(tDAO.getUserList().get(i).toString());
        }
    }
    public void createUser(){
        tDAO.createUser(tDAO.getUser(1));
    }
    public void newUserName(int userId){
        System.out.println("Enter new username: ");
        String userName = sc.next();
        tDAO.getUser(userId).setUserName(userName);
    }
    public void newInitials(int userId){
        System.out.println("Enter new initials: ");
        String initials = sc.next();
        tDAO.getUser(userId).setIni(initials);
    }
    public void addRoles(int userId){
        int restart = 0;
        do {
            System.out.println("Enter the number of roles, you want to add: ");
            String n = sc.next();
            if (n.matches("^[0-4]$") && n.length() > 0) {
                int m = Integer.valueOf(n);
                for (int i = 0; i < m; i++) {
                    System.out.println("Enter roles: ");
                    tDAO.getUser(userId).addRole(sc.next());
                }
                break;
            } else {
                restart++;
            }
        }
        while (restart > 0);
    }
    public void removeRoles(int userId){
        System.out.println("Enter the role you want to remove: ");
        String roleString = sc.next();
        tDAO.getUser(userId).removeRole(roleString);
    }

    public void newRoles(int userId){
        while (true) {
            System.out.println("Current roles " + tDAO.getUser(userId).getRoles());
            System.out.println("Role Menu:\n"
                    + "1. Delete roles\n"
                    + "2. Add role\n"
                    + "3. Done\n");
            int roleChoice = sc.nextInt();
            if (roleChoice == 1) {
                removeRoles(userId);
            } else if (roleChoice == 2) {
                addRoles(userId);
            } else if (roleChoice == 3) {
                break;
            }
        }
    }
    public void editUser(){
        System.out.println("Enter userId");
        int userId = sc.nextInt() - 1;
        while (true) {
            tDAO.getUser(userId);
            System.out.println("Change Menu:\n"
                    + "1. Change username\n"
                    + "2. Change ini\n"
                    + "3. Change roles\n"
                    + "4. Done\n");
            int choiceof = sc.nextInt();
            if (choiceof == 1) {
                newUserName(userId);
            } else if (choiceof == 2) {
                newInitials(userId);

            } else if (choiceof == 3) {
                newRoles(userId);
            } else if (choiceof == 4) {
                break;
            }
        }
    }
    public void deleteUser(){
        System.out.println("Enter userId");
        tDAO.deleteUser(sc.nextInt() - 1);
    }

    public String setUserName(){
        System.out.println("Enter username: ");
        return sc.next();
    }
    public String setInitials(){
        System.out.println("Enter initials: ");
        return sc.next();
    }
    public void setRoles(IUserDTO user){
        System.out.println("Enter number of roles: ");
        String n = sc.next();
        if(n.matches("^[0-4]$") && n.length() > 0){
            int m = Integer.valueOf(n);
            for(int i = 0; i<m;i++) {
                System.out.println("Enter roles: ");
                user.addRole(sc.next());
            }
        }
        else{
            setRoles(user);
        }
    }
}
