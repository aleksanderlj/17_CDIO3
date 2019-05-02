package rest;

public class UserPass {
    private String username;
    private String password;

    public UserPass(){}

    public UserPass(String username, String password){
        this.password = password;
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}

