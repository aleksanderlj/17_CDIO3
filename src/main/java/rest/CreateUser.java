package rest;

import dal.IUserDAO;
import dal.UserDAOCDIO3;
import dal.dto.IUserDTO;
import dal.dto.UserDTO;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("createuser")
public class CreateUser {

    // http://localhost:8080/17_CDIO3_war_exploded/rest/createuser
    @POST
    public String createUser(@FormParam("userName") String userName,
                           @FormParam("ini") String ini) throws IUserDAO.DALException {
        IUserDAO sql = new UserDAOCDIO3();
        IUserDTO u1 = new UserDTO();

        u1.setUserName(userName);
        u1.setIni(ini);
        u1.addRole("Admin");

        sql.createUser(u1);

        //return "Name: " + userName + "\nInitials: " + ini;
        return u1.getUserName();
    }
}
