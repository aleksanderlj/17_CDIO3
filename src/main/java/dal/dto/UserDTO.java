package dal.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Standard implementation of IUserDTO
 */

public class UserDTO implements Serializable, IUserDTO {
    //Fields
    private int	userId;
    private String userName;
    private String ini;
    private List<String> roles;
    ArrayList<IUserDTO>list = new ArrayList<>();
    //Constructor
    public UserDTO(){
        this.roles = new ArrayList<>();
    }
    public UserDTO(int userId, String userName, String ini, List<String> roles) {
        this.roles = new ArrayList<>();
        this.userId = userId;
        this.userName = userName;
        this.ini = ini;
    }
    //Getters and Setters
    @Override
    public int getUserId() {
        return userId;
    }
    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }
    @Override
    public String getUserName() {
        return userName;
    }
    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Override
    public String getIni() {
        return ini;
    }
    @Override
    public void setIni(String ini) {
        this.ini = ini;
    }

    @Override
    public List<String> getRoles() {
        return roles;
    }
    @Override
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public void addRole(String role){
        this.roles.add(role);
    }
    /**
     *
     * @param role
     * @return true if role existed, false if not
     */
    @Override
    public boolean removeRole(String role){
        return this.roles.remove(role);
    }

    @Override
    public String toString() {
        return "UserDTO [userId=" + userId + ", userName=" + userName + ", ini=" + ini + ", roles=" + roles + "]";
    }
    public String userString(IUserDTO user){
        int Id = user.getUserId();
        String name = user.getUserName();
        String ini = user.getIni();
        String roles="";
        for(int i = 0; i<user.getRoles().size(); i++){
            roles+= user.getRoles().get(i)+" ";
        }
        String userString = "userId = " + Id + ", username = " + name + ", ini = "+ini+", roles = "+roles;
        return userString;
    }

}