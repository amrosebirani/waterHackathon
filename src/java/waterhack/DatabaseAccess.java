/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waterhack;

import java.sql.*;
import java.lang.Math.*;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author amrosebirani
 */
public class DatabaseAccess {

    public static String url = "jdbc:mysql://localhost:3306/";
    public static String db = "waterHackathondb";
    public static String lotterydb = "waterLotterydb";
    public static String driver = "com.mysql.jdbc.Driver";
    public static String user = "root";
    public static String pass = "adminadmin";
    public static String server = "http://172.16.1.57:8124/WaterHackathon/";

    public static Boolean isValid(String username, String password) {
	Connection con = null;
	Boolean retVal = false;
	try {
	    Class.forName(driver).newInstance();
	    con = DriverManager.getConnection(url + db, user, pass);
	    try {
		Statement st = con.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM  users where username='" + username + "' and password='" + password + "'");
		if (res.next()) {
		    retVal = true;
		    return retVal;
		}
		con.close();
	    } catch (SQLException s) {
		System.out.println("SQL code does not execute.");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return retVal;
    }

    public static String getfilePathfromId(String imageId) {
	Connection con = null;
	String retVal = "";
	try {
	    Class.forName(driver).newInstance();
	    con = DriverManager.getConnection(url + db, user, pass);
	    try {
		Statement st = con.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM  images where imageId='" + imageId + "'");
		if (res.next()) {
		    retVal = res.getString("imagePath");
		    return retVal;
		}
		con.close();
	    } catch (SQLException s) {
		System.out.println("SQL code does not execute.");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return retVal;
    }

    public static publicRestroomList getRestroomList(double latitude, double longitude) {
	Connection con = null;
	publicRestroomList restroomlist = new publicRestroomList();
	try {
	    Class.forName(driver).newInstance();
	    con = DriverManager.getConnection(url + db, user, pass);
	    try {
		Statement st = con.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM publicRestroom ");
		while (res.next()) {
		    try{
		    double lat = Double.valueOf(res.getString("latitude"));
		    double lon = Double.valueOf(res.getString("longitude"));
		    if (java.lang.Math.abs(latitude - lat) < .2 && java.lang.Math.abs(longitude - lon) < .2) {
			int [] retVal = distanceCalculate.getDistance(String.valueOf(latitude), String.valueOf(longitude), String.valueOf(lat), String.valueOf(lon));
			int distance = retVal[0];
			int duration = retVal[1];
			if (distance < 2000) {
			    Statement stmt = con.createStatement();
			    ResultSet resultset = stmt.executeQuery("SELECT * FROM imageRestroomMap where restroomId='" + res.getString("restroomId") + "'");
			    imageurlsList imageList = new imageurlsList();
			    while (resultset.next()) {
				image imageObj = new image();
				imageObj.imageurl = server + "resources/image/" + resultset.getInt("imageId") + "/showimage";
				imageObj.timestamp = resultset.getString("createTime");
				imageList.addentry(imageObj);

			    }

			    resultset = stmt.executeQuery("SELECT * FROM comments where restroomId='" + res.getString("restroomId") + "'");
			    commentsList commentlist = new commentsList();
			    while (resultset.next()) {
				String comment = resultset.getString("comment");
				commentlist.addentry(comment);

			    }
			    publicRestroom restroom = new publicRestroom(res.getString("latitude"), res.getString("longitude"), res.getString("title"), res.getString("description"), res.getInt("restroomId"), res.getInt("rating"), imageList, distance, duration, commentlist);
			    restroomlist.addentry(restroom);
			
			}


		    }
		    }
			catch(Exception e)
			{}
		}
		con.close();
	    } catch (SQLException s) {
		System.out.println("SQL code does not execute.");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return restroomlist;
    }

    public static void insertComment(int restroomId, String username, String comment) {
	Connection con = null;

	try {
	    Class.forName(driver);
	    con = DriverManager.getConnection(url + db, user, pass);
	    try {
		Statement st = con.createStatement();
		int val = st.executeUpdate("INSERT into comments (restroomId,username,comment) VALUES(" + restroomId + ",'" + username +"','"+ comment+"')");
		System.out.println("1 row affected");
	    } catch (SQLException s) {
		System.out.println("SQL statement is not executed!");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static int insertimage() {
	
	Connection con = null;
	int retVal = 0;
	try
	{
	Class.forName(driver).newInstance();
	    con = DriverManager.getConnection(url + db, user, pass);
	    
	    try {
		Statement st = con.createStatement();
		ResultSet res = st.executeQuery("SELECT imageId FROM  images order by imageId desc limit 1");
		if (res.next()) {
		    retVal = res.getInt("imageId");
		}
		retVal=retVal+1;
		try {
		Statement stmt = con.createStatement();
		String filename= retVal + ".jpeg";
		String filepath = "/Users/amrosebirani/water/WaterHackathon/res/" + filename;
		int val = stmt.executeUpdate("INSERT into images (imageId,imageName,imagePath) VALUES(" + retVal + ",'" + filename +"','"+ filepath+"')");
		System.out.println("1 row affected");
	    } catch (SQLException s) {
		System.out.println("SQL statement is not executed!");
	    }
		con.close();
	    } catch (SQLException s) {
		System.out.println("SQL code does not execute.");
	    }
	}
	catch (Exception e)
	{
	e.printStackTrace();
	}
	
	return retVal;
	
    }

    static void insertimageMap(int restroomId, String username,int imageId) {
	Connection con = null;

	try {
	    Class.forName(driver);
	    con = DriverManager.getConnection(url + db, user, pass);
	    try {
		Statement st = con.createStatement();
		java.util.Date time = new java.util.Date();
	         Timestamp timestamp = new java.sql.Timestamp(time.getTime());
		int val = st.executeUpdate("INSERT into imageRestroomMap (restroomId,username,imageId,createTime) VALUES(" + restroomId + ",'" + username +"',"+ imageId+",'"+timestamp.toString()+"')");
		System.out.println("1 row affected");
	    } catch (SQLException s) {
		System.out.println("SQL statement is not executed!");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void updaterating(int rating, int restroomId) {
	Connection con = null;
	try {
	    Class.forName(driver);
	    con = DriverManager.getConnection(url + db, user, pass);
	    try {
		Statement st = con.createStatement();
		ResultSet res = st.executeQuery("SELECT rating,ratingCount FROM publicRestroom where restroomId="+restroomId);
		if (res.next()) {
		    int  currentRating = res.getInt("rating");
		    int ratingCount = res.getInt("ratingCount");
		    rating = (currentRating*ratingCount + rating)/(ratingCount+1);
		    ratingCount = ratingCount +1;
		    Statement stmt = con.createStatement();
		    stmt.executeUpdate("UPDATE publicRestroom SET rating="+rating+",ratingCount="+ratingCount+" where restroomId="+restroomId);
		}
	    } catch (SQLException s) {
		System.out.println("SQL statement is not executed!");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    static boolean insertUser(String username, String password) {
	Connection con = null;
        Boolean retVal = false;
	try {
	    Class.forName(driver);
	    con = DriverManager.getConnection(url + db, user, pass);
	    try {
		Statement st = con.createStatement();
		java.util.Date time = new java.util.Date();
	         Timestamp timestamp = new java.sql.Timestamp(time.getTime());
		int val = st.executeUpdate("INSERT into users (username,password) VALUES('" +  username +"','"+ password+"')");
		System.out.println("1 row affected");
		retVal = true;
	    } catch (SQLException s) {
		System.out.println("SQL statement is not executed!");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return retVal;
    }

    static boolean isValidLotteryUser(String username, String password) {
	Connection con = null;
	Boolean retVal = false;
	try {
	    Class.forName(driver).newInstance();
	    con = DriverManager.getConnection(url + lotterydb, user, pass);
	    try {
		Statement st = con.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM  users where username='" + username + "' and password='" + password + "'");
		if (res.next()) {
		    retVal = true;
		    return retVal;
		}
		con.close();
	    } catch (SQLException s) {
		System.out.println("SQL code does not execute.");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return retVal;
    }

    static boolean insertLotteryUser(String username, String password) {
	Connection con = null;
        Boolean retVal = false;
	try {
	    Class.forName(driver);
	    con = DriverManager.getConnection(url + lotterydb, user, pass);
	    try {
		Statement st = con.createStatement();
		java.util.Date time = new java.util.Date();
	         Timestamp timestamp = new java.sql.Timestamp(time.getTime());
		int val = st.executeUpdate("INSERT into users (username,password,QRcode) VALUES('" +  username +"','"+ password+"','"+username+"')");
		System.out.println("1 row affected");
		retVal = true;
	    } catch (SQLException s) {
		System.out.println("SQL statement is not executed!");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return retVal;
    }

    static readingListResource getreadingList(int userId) {
	Connection con = null;
	readingListResource readinglist = new readingListResource();
	try {
	    Class.forName(driver).newInstance();
	    con = DriverManager.getConnection(url + lotterydb, user, pass);
	try{
	    Statement st = con.createStatement();
	ResultSet resultset = st.executeQuery("SELECT * FROM reading where userId=" + userId );
			    
			    while (resultset.next()) {
				String reading = resultset.getString("reading");
				readinglist.addentry(reading);

			    }
	}
	catch (SQLException s){
		System.out.println("SQL statement is not executed!");
	    }
	} 
	catch (Exception e) {
	    e.printStackTrace();
	}
	return readinglist;
    }
}
