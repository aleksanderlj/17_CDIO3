package rest;

public class JSONUser {
    private String id;
    private String username;
    private String initials;
    private String[] roles;

    JSONUser(){}

    public JSONUser(String username, String initials, String[] roles){
        this.username = username;
        this.initials = initials;
        this.roles = roles;
    }

    public JSONUser(String id, String username, String initials, String[] roles){
        this.id = id;
        this.username = username;
        this.initials = initials;
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getInitials() {
        return initials;
    }

    public String getId() {
        return id;
    }

    public String[] getRoles() {
        return roles;
    }
}
