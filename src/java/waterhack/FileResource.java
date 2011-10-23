
package waterhack;



/**
 *
 * @author amrosebirani
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;


/**
 *
 * @author amrosebirani
 */
@Path("/fileupload")
public class FileResource {

    
    
    
    @Context
    private HttpHeaders headers;

    
    @POST
    @Path("/data/restroomId/{restroomId}/username/{username}")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces("text/plain")
    public Response UploadFile(byte[] buffer, @PathParam("restroomId") int restroomId, @PathParam("username") String username) throws FileNotFoundException, IOException {

	
	try {
	    	
	    int imageId = DatabaseAccess.insertimage();
	    DatabaseAccess.insertimageMap(restroomId,username,imageId);
	    String FilePath = "/Users/amrosebirani/water/WaterHackathon/res/" + imageId + ".jpeg";
	    FileOutputStream out = new FileOutputStream(FilePath);
	    out.write(buffer);
	    out.close();
	    
	    
	} catch (Exception e) {
	    
	    return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	}
	return Response.ok(MediaType.TEXT_PLAIN).build();

    }

    
}
