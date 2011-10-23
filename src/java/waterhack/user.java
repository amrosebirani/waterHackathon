/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waterhack;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author amrosebirani
 */

@XmlRootElement
public class user {
    public String username;
    public int userId;
    
    public user()
	    {}
    
    public user(String username,int userId)
    {
    this.username = username;
    this.userId = userId;
    }
}
