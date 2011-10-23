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
public class publicRestroomList {
    public ArrayList<publicRestroom> publicRestroom;
    
    public publicRestroomList() {
	this.publicRestroom = new ArrayList();
    }

    ;

    
    public publicRestroomList(ArrayList<publicRestroom> restroomList) {
	this.publicRestroom = (restroomList != null) ? new ArrayList<publicRestroom>(restroomList) : new ArrayList();
    }
    
    public void  addentry(publicRestroom restroom)
    {
    this.publicRestroom.add(restroom);
    }
}
