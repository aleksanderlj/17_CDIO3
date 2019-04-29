package rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("getme")
public class PostService {

    @POST
    public String postPuhsket(){
        return "puhsket";
    }
}
