package rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("getme")
public class PostService {

    @POST
    public String postPuhsket(@FormParam("test") String test){
        return "puhsket " + test;
    }
}
