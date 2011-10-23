/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waterhack;

import java.io.IOException;



import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * 
 * @author amrosebirani
 */
public class testcommentupload {
    /**
     * 
     * @param args
     * @throws IOException
     * @throws JSONException
     */
    public static void main(String[] args) throws IOException, JSONException{
	    String url = "http://172.16.1.57:8124/WaterHackathon/resources/comment/uploadcomment";
	    System.out.println(url);
	    JSONObject j = new JSONObject();
	    j.put("restroomId", 2);
	    j.put("username", "amrose");
	    j.put("comment", "kya fart hai");
	    
	    //java.util.Date time = new java.util.Date();
	    //Timestamp timestamp = new java.sql.Timestamp(time.getTime());
	    //j.put("timestamp", 7);
	    DefaultHttpClient httpclient = new DefaultHttpClient();
	    HttpPost httpost = new HttpPost(url);
	    
	    
	    StringEntity se = new StringEntity(j.toString());
	    System.out.println(j.toString());
	    httpost.setEntity(se);
	    httpost.setHeader("Accept", "text/plain");
	    httpost.setHeader("Content-type", "application/json");

	    ResponseHandler responseHandler = new BasicResponseHandler();
	    httpclient.execute(httpost, responseHandler);
	    //responseHandler.handleResponse(null)
	    
            httpost.abort();
	    
    }
}
