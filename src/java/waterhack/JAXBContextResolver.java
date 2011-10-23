/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waterhack;

/**
 *
 * @author amrosebirani
 */
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;


@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JAXBContextResolver implements ContextResolver<JAXBContext> {

    private JAXBContext context;
    private Class[] types = {waterhack.image.class, waterhack.publicRestroom.class, waterhack.publicRestroomList.class, waterhack.imageurlsList.class,waterhack.commentsList.class};

    /**
     * JAXB context resolver class to manually assign the configuration for various JSON resources created
     * @throws Exception
     */
    public JAXBContextResolver() throws Exception {
	this.context =
		new JSONJAXBContext(
	    JSONConfiguration.mapped().arrays("imagelist").arrays("publicRestroomlist").arrays("commentlist").build(), types);
    }

    /**
     * 
     * @param objectType
     * @return
     */
    public JAXBContext getContext(Class<?> objectType) {
	for (Class type : types) {
	    if (type.equals(objectType)) {
		return context;
	    }
	}
	return null;
    }
}
