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
public class commentsList {
    public ArrayList<String> comment;
    
    public commentsList() {
	this.comment = new ArrayList();
    }

    ;

    
    public commentsList(ArrayList<String> commentList) {
	this.comment = (commentList != null) ? new ArrayList<String>(commentList) : new ArrayList();
    }
    
    public void  addentry(String comment)
    {
    this.comment.add(comment);
    }
}
