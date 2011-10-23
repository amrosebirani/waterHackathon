/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waterhack;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author amrosebirani
 */

@Path("/restrooms")
public class restroomResource {
    
    @GET
    @Path("/lat/{lat}/lon/{lon}")
    @Produces({MediaType.APPLICATION_XML})
    public publicRestroomList getRestrooms (@PathParam("lat") String lat,@PathParam("lon") String lon)
    {
    double latitude = Double.valueOf(lat);
    double longitude = Double.valueOf(lon);
    publicRestroomList returnList = DatabaseAccess.getRestroomList(latitude,longitude);
    return returnList;
    }
}
