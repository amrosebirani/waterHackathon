/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waterhack;

import java.io.File;
import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author amrosebirani
 */
@Path("/image/{imageId}")
public class imageResource {
    
  @GET
  @Path("showimage")
  @Produces("image/*")
   public Response getImage(@PathParam("imageId") String imageId) {
       String filePath = DatabaseAccess.getfilePathfromId(imageId);
       File f = new File(filePath);
   
       if (!f.exists()) {
           throw new WebApplicationException(404);
       }
  
      String mt = new MimetypesFileTypeMap().getContentType(f);
      return Response.ok(f, mt).build();
  }
    
}
