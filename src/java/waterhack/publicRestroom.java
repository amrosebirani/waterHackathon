/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waterhack;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author amrosebirani
 */
@XmlRootElement

public class publicRestroom {
    public String latitude;
    public String longitude;
    public String title;
    public String description;
    public int restroomId;
    public int rating;
    public imageurlsList imageUrls;
    public int distance;
    public int duration;
    public commentsList commentlist;
    
    public publicRestroom(String latitude,String longitude,String title,String description,int restroomId,int rating,imageurlsList imageUrls,int distance,int duration,commentsList commentlist)
    {
    this.latitude=latitude;
    this.longitude=longitude;
    this.rating = rating;
    this.restroomId = restroomId;
    this.description = description;
    this.title = title;
    this.imageUrls = imageUrls;
    this.distance = distance;
    this.duration = duration;
    this.commentlist = commentlist;
    }
    
    public publicRestroom()
    {}
    
    
}
