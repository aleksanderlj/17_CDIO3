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
    //Constructor
    public UserDTO() {
        this.roles = new ArrayList<>();
    }
    public UserDTO createTestDTO(){
        this.userId=50;
        this.userName="Test";
        this.ini="T";
        List role = new ArrayList<>();
        role.add("Admin");
        this.roles = role;
        return this;
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



}