package rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("formtest")
public class FormService {

    @POST
    public String createUser(@FormParam("userName") String userName,
                             @FormParam("ini") String ini){
        String user = "Name: " + userName + "\nInitials: " + ini;
        return user;
    }
}
