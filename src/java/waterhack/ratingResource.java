/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waterhack;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author amrosebirani
 */
@Path("/rating/{rating}")
public class ratingResource {
    
    @GET
    @Produces("text/plain")
    @Path("/restroomId/{restroomId}")
    public Response uploadRating(@PathParam("rating") int rating,@PathParam("restroomId") int restroomId)
    {
    try {
	    	
	    DatabaseAccess.updaterating(rating,restroomId);
	    
	    
	} catch (Exception e) {
	    
	    return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	}
	return Response.ok(MediaType.TEXT_PLAIN).build();
    }
}
