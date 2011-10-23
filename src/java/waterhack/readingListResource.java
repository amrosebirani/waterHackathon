/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waterhack;

/**
 *
 * @author amrosebirani
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

@Path("/readings")
@XmlRootElement
public class readingListResource {
    public ArrayList<String> reading;
    
    public readingListResource() {
	this.reading = new ArrayList();
    }

    ;

    
    public readingListResource(ArrayList<String> readingList) {
	this.reading = (readingList != null) ? new ArrayList<String>(readingList) : new ArrayList();
    }
    
    public void  addentry(String reading)
    {
    this.reading.add(reading);
    }
    
    @GET
    @Path("/userId/{userId}")
    @Produces({MediaType.APPLICATION_XML})
    public readingListResource getRestrooms (@PathParam("userId") int userId)
    {
    readingListResource returnList = DatabaseAccess.getreadingList(userId);
    return returnList;
    }
    
    //@GET
    ///@Path("/add/reading/{reading}/date/{date}/month/{month}/user/{userId}")
    //@Produces("text/plain")
	    //public addReading
}
