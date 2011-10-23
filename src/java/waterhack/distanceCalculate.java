/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waterhack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
/**
 *
 * @author amrosebirani
 */
public class distanceCalculate {
    
    public static int[] getDistance(String lat1,String lon1,String lat2,String lon2) throws IOException, JSONException
    {
    String url = "http://maps.googleapis.com/maps/api/distancematrix/json?origins="+lat1+","+lon1+"&destinations="+lat2+","+lon2+"&mode=walking&sensor=true";
    DefaultHttpClient httpclient = new DefaultHttpClient();
	    HttpGet httpget = new HttpGet(url);
	    
	    
	    httpget.setHeader("Accept", "application/json");
	    

	    ResponseHandler responseHandler = new BasicResponseHandler();
	    HttpResponse response = httpclient.execute(httpget);
	    HttpEntity entity = response.getEntity();
	    InputStream is = null;
	    is = entity.getContent();
	    String result = "";
	    JSONObject jArray = null;
	    try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			//Log.e("log_tag", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jArray = new JSONObject(result);
		} catch (JSONException e) {
			//Log.e("log_tag", "Error parsing data " + e.toString());
		}
	    //responseHandler.handleResponse(null)
		int[] retVal = new int[2];
		try {
                JSONObject element = (JSONObject) jArray.getJSONArray("rows").get(0);
		JSONObject distancearr = (JSONObject) element.getJSONArray("elements").get(0);
		JSONObject distance = distancearr.getJSONObject("distance");
		String dist = distance.getString("value");
		JSONObject duration = distancearr.getJSONObject("duration");
		String dura = duration.getString("value");
		httpget.abort();
		
		retVal[0] = Integer.valueOf(dist);
		retVal[1] = Integer.valueOf(dura);
		}
		catch(Exception e)
		{}
    return  retVal;
    }
    
    
}
