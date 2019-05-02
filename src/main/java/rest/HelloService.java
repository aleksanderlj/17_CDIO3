package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import dal.IUserDAO;
import dal.UserDAOCDIO3;
import dal.dto.IUserDTO;
import dal.dto.UserDTO;

@Path("hello")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
public class HelloService {
    private IUserDAO userDAO;
    private UserDTO user;

    @GET
    public String getHello(){
        return "Du er i helloservice";
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void useSQL(String ) throws IUserDAO.DALException{

        userDAO = new UserDAOCDIO3();
        user = new UserDTO();
        UserDTO test = user.createTestDTO();
        userDAO.createUser(test);
    }
    /*
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean postHello(String id, String name, String amount){
        TestClass tc = new TestClass(id, name, amount);
        return true;
    }
    */

    /*
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean postHello(String id, String name, String amount){
        TestClass tc = new TestClass(id, name, amount);
        return true;
    }
    */

    @POST
    @Path("form")
    public String postHello(
            @FormParam("id") String id,
            @FormParam("name") String name,
            @FormParam("amount") String amount){

        if (
                id.equalsIgnoreCase("correctid") &&
                        name.equalsIgnoreCase("jonatan") &&
                        amount.equalsIgnoreCase("alot")){
            return "It worked";
        }
        else{
            return "oooooooo nooooooo";
        }
    }

    @POST
    @Path("query")
    public String postQuery(
            @QueryParam("id") String id,
            @QueryParam("name") String name,
            @QueryParam("amount") String amount){

        if (
                id.equalsIgnoreCase("correctid") &&
                        name.equalsIgnoreCase("jonatan") &&
                        amount.equalsIgnoreCase("alot")){
            return "It worked";
        }
        else{
            return "oooooooo nooooooo";
        }
    }

    @POST
    @Path("{id}/{name}/{amount}")
    public String postPath(
            @PathParam("id") String id,
            @PathParam("name") String name,
            @PathParam("amount") String amount){

        if (
                id.equalsIgnoreCase("correctid") &&
                        name.equalsIgnoreCase("jonatan") &&
                        amount.equalsIgnoreCase("alot")){
            return "It worked";
        }
        else{
            return "oooooooo nooooooo";
        }
    }
}
