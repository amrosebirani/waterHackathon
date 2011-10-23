/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waterhack;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author amrosebirani
 */
@Path("/lotteryuser/username/{username}/password/{password}")
@XmlRootElement
public class lotteryUserResource {
    public int userId;
    public String username;
    public String password;
    public String QRcode;
    
    public lotteryUserResource()
    {}
    
    public lotteryUserResource(int UserId,String username,String password,String QRcode)
    {
    this.QRcode = QRcode;
    this.password = password;
    this.userId = userId;
    this.username = username;
    }
    
    @Path("/signIn")
    @GET
    public Response signIn(@PathParam("username") String username,@PathParam("password") String password)
    {
         if (DatabaseAccess.isValidLotteryUser(username,password))
	 {
	 return Response.ok().build();
	 }
	 
	 return Response.status(Status.BAD_REQUEST).entity("Invalid Username/password").build();
	 
    }
    
    @Path("/signUp")
    @GET
    public Response signUp(@PathParam("username") String username,@PathParam("password") String password)
    {
         if (DatabaseAccess.insertLotteryUser(username,password))
	 {
	 return Response.ok().build();
	 }
	 
	 return Response.status(Status.BAD_REQUEST).entity("Invalid Username/password").build();
	 
    }
    
}
