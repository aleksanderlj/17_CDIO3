package rest;

import dal.IUserDAO;
import dal.UserDAOCDIO3;
import dal.dto.IUserDTO;
import dal.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("useradmin")
public class CreateUser {
    IUserDAO db = new UserDAOCDIO3();

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createUser(JSONUser user) throws IUserDAO.DALException {
        IUserDTO dbUser = new UserDTO();
        dbUser.setUserName(user.getUsername());
        dbUser.setIni(user.getInitials());
        //dbUser.setRoles();

        db.createUser(dbUser);
    }
}
