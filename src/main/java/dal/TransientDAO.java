package dal;

import dal.dto.IUserDTO;
import dal.dto.UserDTO;
import test.TransientUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransientDAO implements IUserDAO{
    private ArrayList<IUserDTO> list;
    public TransientDAO(){
        this.list = new ArrayList<>();
        IUserDTO Andreas = new UserDTO(1, "Andreas", "AS", null);
        Andreas.addRole("Admin");
        IUserDTO Aleksander = new UserDTO(2, "Aleksander", "AJ", null);
        Aleksander.addRole("Pharmaceut");
        IUserDTO Josephine = new UserDTO(3, "Josephine", "JW", null);
        Josephine.addRole("Laborant");
        IUserDTO Jonatan = new UserDTO(4, "Jonatan", "JD", null);
        Jonatan.addRole("Produktionsleder");
        IUserDTO Søren = new UserDTO(5, "Søren", "SH", null);
        Søren.addRole("Admin");
        IUserDTO Theodor = new UserDTO(6, "Theodor", "TG", null);
        Theodor.addRole("Pharmaceut");
        list.add(Andreas);
        list.add(Aleksander);
        list.add(Josephine);
        list.add(Jonatan);
        list.add(Søren);
        list.add(Theodor);
    }
    int UserIdCounter = 6;

    public int createUser(IUserDTO user){
        TransientUI t = new TransientUI();
        IUserDTO model = new UserDTO(++UserIdCounter, t.setUserName(), t.setInitials(), null);
        t.setRoles(model);
        list.add(model);
        return UserIdCounter;
    }

    public IUserDTO getUser(int userId){
        return list.get(userId);
    }

    public List<IUserDTO> getUserList(){
        return list;
    }

    public void updateUser(IUserDTO user){

    }
    public void deleteUser(int userId){
        list.remove(userId);
    }
}
