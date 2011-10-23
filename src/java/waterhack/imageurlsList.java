
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
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class imageurlsList {
    public ArrayList<image> image;
    
    public imageurlsList() {
	this.image = new ArrayList();
    }

    ;

    
    public imageurlsList(ArrayList<image> imageList) {
	this.image = (imageList != null) ? new ArrayList<image>(imageList) : new ArrayList();
    }
    
    public void  addentry(image imageobj)
    {
    this.image.add(imageobj);
    }
}
