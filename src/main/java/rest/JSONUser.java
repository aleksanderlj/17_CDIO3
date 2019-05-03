package rest;

public class JSONUser {
    private String id;
    private String username;
    private String initials;
    // TODO Noget med roller

    JSONUser(){}

    public JSONUser(String username, String initials){
        this.username = username;
        this.initials = initials;
        // TODO Noget med roller
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

    // TODO set og get roller


    public String getUsername() {
        return username;
    }

    public String getInitials() {
        return initials;
    }

    public String getId() {
        return id;
    }
}
