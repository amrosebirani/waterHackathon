/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waterhack;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;

/**
 * 
 * @author amrosebirani
 */
@XmlRootElement
@Path("/comment")
public class commentUploadResource {
    public int restroomId;
    public String username;
    public String comment;
	  
    public commentUploadResource()
    {}
    
    public commentUploadResource(int restroomId,String username,String comment)
    {
	
	this.comment = comment;
	this.restroomId = restroomId;
	this.username = username;
    }
    
    
    @Path("/uploadcomment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/plain")
    @POST
    public Response uploadcomment(JAXBElement<commentUploadResource> jaxbcomment)
    {
    try {
              commentUploadResource comment = jaxbcomment.getValue();  
              DatabaseAccess.insertComment(comment.restroomId,comment.username,comment.comment);  
                
            } catch (Exception e) {
                
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
            }
            return Response.ok(MediaType.TEXT_PLAIN).build();
    }
	    
}
