package rest;

import dal.IUserDAO;
import dal.UserDAOCDIO3;
import dal.dto.IUserDTO;
import dal.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        dbUser.setRoles(Arrays.asList(user.getRoles()));

        db.createUser(dbUser);
    }

    @POST
    @Path("delete/{id}")
    public void deleteUser(@PathParam("id") String id) throws IUserDAO.DALException {
        db.deleteUser(Integer.parseInt(id));
    }

    @GET
    @Path("getuser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONUser getUser(@PathParam("id") String id) throws IUserDAO.DALException {
        IUserDTO dbUser = db.getUser(Integer.parseInt(id));

        return new JSONUser(
                Integer.toString(Integer.parseInt(id)),
                dbUser.getUserName(),
                dbUser.getIni(),
                dbUser.getRoles().toArray(new String[0]));
    }

    @POST
    @Path("updateuser")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(JSONUser user) throws IUserDAO.DALException {
        IUserDTO dbUser = new UserDTO(
                Integer.parseInt(user.getId()),
                user.getUsername(),
                user.getInitials(),
                Arrays.asList(user.getRoles()));

        db.updateUser(dbUser);
    }

    @GET
    @Path("userlist")
    public String getUserlist() throws IUserDAO.DALException {
        List<IUserDTO> list = db.getUserList();
        StringBuilder sArray = new StringBuilder();

        for (int n = 0 ; n<list.size() ; n++){
            sArray.append(list.get(n).getUserId());
            sArray.append(",");
        }

        sArray.deleteCharAt(sArray.length()-1);

        return sArray.toString();
    }
}