package rest;

import dal.IUserDAO;
import dal.UserDAOCDIO3;
import dal.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("hello")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
public class HelloService {

    @GET
    public String getHello(){
        return "Du er i helloservice";
    }


    /*
    //@POST
    //@Path("formjson")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean postJSON(String id, String name, String amount){
        TestClass tc = new TestClass(id, name, amount);
        return true;
    }
    */

    @POST
    @Path("mysql")
    public String postMysql(String input) throws IUserDAO.DALException {
        UserDAOCDIO3 db = new UserDAOCDIO3();
        UserDTO user = new UserDTO();
        user.setUserName("Mads");
        user.setIni("sfidon");
        List roles = new ArrayList<String>();
        roles.add("Admin");
        user.setRoles(roles);
        db.createUser(user);

        return "Mysql " + input;
    }


    @POST
    @Path("postclass")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postClass(TestClass tc){
        //TestClass tc = new TestClass(id, name, amount);
        return tc.getName();
    }


    @POST
    @Path("normal")
    public String postDefault(String input){
        return "You wrote " + input;
    }

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

    /*
    @POST
    public String postHello(
            @FormParam("postsend1") String name1,
            @FormParam("postsend2") String name2,
            @FormParam("postsend3") String name3){
        return "Du postede: " + name1 + name2 + name3;
    }
    */
    /*
    @POST
    public String postHello(String input1, String input2, String input3){
        return "Du postede: " + input1 + " " + input2 + " " + input3;
    }
    */
}
