/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waterhack;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author amrosebirani
 */

import org.apache.commons.httpclient.methods.PostMethod;



import java.io.FileInputStream;

import java.io.IOException;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;


/**
 * a test class for testing file uploads
 * @author amrosebirani
 */
public class httpUpload {
    //private static String url =
    // "http://localhost:8124/TribalWebServices/upload/data";

    /**
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
	DefaultHttpClient httpClient = new DefaultHttpClient();
	//httpClient.setConnectionTimeout(8000);
	FileInputStream in = new FileInputStream("/Users/amrosebirani/Desktop/vika_jigulina.jpg");
	final int bufferSize = 1024 * 1024; // 4kb buffer
	byte[] buffer = new byte[bufferSize];
	
	int read = in.read(buffer);
	    
		String url = "http://172.16.1.57:8124/WaterHackathon/resources/fileupload/data/restroomId/1/username/amrose";
		System.out.println(url);
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-type", MediaType.APPLICATION_OCTET_STREAM);
		byte[] bufferupload = new byte[read];
		for(int i=0;i<read;i++)
		{
		bufferupload[i]=buffer[i];
		}
		ByteArrayEntity barr = new ByteArrayEntity(bufferupload);
		//sets the body of the request 
		post.setEntity(barr);
		HttpResponse response = httpClient.execute(post);
		System.out.println(response.getStatusLine());
		post.abort();
	    

    }
}
