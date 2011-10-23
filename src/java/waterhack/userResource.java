/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waterhack;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author amrosebirani
 */

@Path("/user/username/{username}/password/{password}")
public class userResource {
    
    @Path("/signIn")
    @GET
    public Response signIn(@PathParam("username") String username,@PathParam("password") String password)
    {
         if (DatabaseAccess.isValid(username,password))
	 {
	 return Response.ok().build();
	 }
	 
	 return Response.status(Status.BAD_REQUEST).entity("Invalid Username/password").build();
	 
    }
    
    @Path("/signUp")
    @GET
    public Response signUp(@PathParam("username") String username,@PathParam("password") String password)
    {
         if (DatabaseAccess.insertUser(username,password))
	 {
	 return Response.ok().build();
	 }
	 
	 return Response.status(Status.BAD_REQUEST).entity("Invalid Username/password").build();
	 
    }
    
}
