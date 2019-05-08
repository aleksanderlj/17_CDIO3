package test;

import dal.IUserDAO;
import dal.UserDAOCDIO3;
import dal.dto.IUserDTO;
import dal.dto.UserDTO;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TUI {
    private IUserDAO userDAO;

    public TUI() {
        userDAO = new UserDAOCDIO3();
    }

    public void run() throws IUserDAO.DALException {
        Scanner sc = new Scanner(System.in);
        Scanner stop = new Scanner(System.in);
        IUserDAO userDAO = new UserDAOCDIO3();
        String choice;

        do {
            System.out.println(
                    "Menu:\n"
                    + "1. Show user\n"
                    + "2. Show all users\n"
                    + "3. Create user\n"
                    + "4. Edit user\n"
                    + "5. Delete user\n"
                    + "6. Close program\n"
            );

            choice = sc.nextLine();

            switch (choice) {
                case "1": // Vis bruger
                    System.out.println("Write userID:");
                    try {
                        System.out.println(userDAO.getUser(sc.nextInt()));
                    } catch (IUserDAO.DALException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e){
                        System.out.println("Not valid userID");
                    } finally{
                        sc.nextLine();
                    }
                    break;

                case "2": // Vis alle brugere
                    List<IUserDTO> userlist = userDAO.getUserList();
                    for (int n = 0; n < userlist.size(); n++)
                        System.out.println(userlist.get(n));
                    break;

                case "3": // Opret bruger
                    System.out.println(userDAO.createUser(createUserDTO()));
                    break;

                case "4": // Opdater bruger
                    try {
                        userDAO.updateUser(updateUser());
                    } catch (IUserDAO.DALException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e){
                        System.out.println("Not valid userID");
                    }
                    break;

                case "5": // Slet bruger
                    System.out.println("Write userID:");
                    try {
                        userDAO.deleteUser(sc.nextInt());
                    } catch (InputMismatchException e){
                        System.out.println("Not valid userID");
                    } finally {
                        sc.nextLine();
                    }
                    break;

                case "6":
                    break;
            }

            System.out.println("Press ENTER to continue");
            stop.nextLine();

        } while (!choice.equalsIgnoreCase("6"));
        System.out.println("...Program closed");
        System.exit(0);
    }

    private UserDTO createUserDTO() {
        Scanner createScanner = new Scanner(System.in);
        UserDTO newUser = new UserDTO();

        System.out.println("Name:");
        newUser.setUserName(createScanner.next());

        System.out.println("Initials:");
        newUser.setIni(createScanner.next());

        /*
        System.out.println("CPR (form: xxxxxx-xxxx):");
        String cpr;
        do {
            cpr = createScanner.next();
            newUser.setCpr(cpr);
        } while (!cpr.matches("\\d{6}-\\d{4}"));
        */

        /*
        newUser.setPassword(PasswordGenerator.password());
        System.out.println("The autogenerated password is: " + newUser.getPassword());
        */

        System.out.println("Roles (write STOP to stop):");
        String role;
        do {
            role = createScanner.next();
            if (!role.equalsIgnoreCase("stop"))
                newUser.addRole(role);
        } while (!role.equalsIgnoreCase("stop"));

        return newUser;
    }

    private IUserDTO updateUser() throws IUserDAO.DALException {
        Scanner updateScanner = new Scanner(System.in);
        IUserDTO chosenUser;
        String choice;

        System.out.println("Write user ID for user you want to edit");
        chosenUser = userDAO.getUser(updateScanner.nextInt());
        updateScanner.nextLine();

        System.out.println("Chosen user: \n" + chosenUser);

        do {
            System.out.println("What do you want to edit?\n"
                    + "1. Name\n"
                    + "2. Initials\n"
                    + "3. CPR (DEPRECATED)\n"
                    + "4. Password (DEPRECATED)\n"
                    + "5. Roles\n"
                    + "6. Exit\n");

            choice = updateScanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("New name:\n");
                    chosenUser.setUserName(updateScanner.next());
                    break;

                case "2":
                    System.out.println("New initials:\n");
                    chosenUser.setIni(updateScanner.next());
                    break;

                case "3":
                    /*
                    System.out.println("New CPR (form: xxxxxx-xxxx):\n");
                    String cpr;
                    do {
                        cpr = updateScanner.next();
                        chosenUser.setCpr(cpr);
                    } while (!cpr.matches("\\d{6}-\\d{4}"));
                    break;
                    */

                case "4":
                    /*
                    System.out.println("New password:\n");
                    chosenUser.setPassword(updateScanner.next());
                    break;
                    */

                case "5":
                    List<String> roles = chosenUser.getRoles();
                    String roleChoice;
                    System.out.println("Add (1) or remove (2) roles? ");
                    System.out.println("Current roles: " + roles);
                    roleChoice = updateScanner.nextLine();
                    switch (roleChoice) {
                        case "1":
                            System.out.println("Write STOP to stop adding roles");
                            String addedRole;
                            do {
                                for (String allRoles : roles)
                                    System.out.print(allRoles + ", ");
                                System.out.println();
                                addedRole = updateScanner.next();
                                if (!addedRole.equalsIgnoreCase("stop"))
                                    chosenUser.addRole(addedRole);
                            } while (!addedRole.equalsIgnoreCase("stop"));
                            break;

                        case "2":
                            String removeRole;
                            System.out.println("Which role should be removed (write STOP to stop)?");
                            do {
                                for (String allRoles : roles)
                                    System.out.print(allRoles + ", ");
                                System.out.println();
                                removeRole = updateScanner.next();
                                chosenUser.removeRole(removeRole);
                            } while (!removeRole.equalsIgnoreCase("stop"));

                            break;
                    }

                    break;

                case "6":
                    break;
            }
        } while (!choice.equals("6"));


        return chosenUser;
    }

    public static void testMethod() throws IUserDAO.DALException {
        UserDAOCDIO3 db = new UserDAOCDIO3();
        UserDTO user = new UserDTO();
        user.setUserName("Henrik");
        user.setIni("Test4");
        List roles = new ArrayList<String>();
        roles.add("Admin");
        user.setRoles(roles);
        db.createUser(user);
    }

}
