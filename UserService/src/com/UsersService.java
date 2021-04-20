package com;

//For REST Service
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Users;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Users")
public class UsersService {
	
	Users  usersobj = new Users();
	
	
	// read users(for Admin)
	
	  @GET
	  
	  @Path("/")
	  
	  @Produces(MediaType.TEXT_HTML) public String readUsers() {
	  
	  return usersobj.viewRegUsers();
	  
	  }
	  
	// view profile details
		@GET
		@Path("/profile/{U_id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		//@Produces(MediaType.TEXT_HTML)
		public String readprofile(@PathParam("U_id") String U_id) {
		
			return usersobj.viewProfile(U_id);
		
			
		}
		
		
		//find details of a certain user type(for admin)
		@GET
		@Path("/{type}")
		@Produces(MediaType.TEXT_PLAIN)
		public String readUserType(@PathParam("type") String type)
		 {
			return usersobj.readUserType(type); 
		 }
		
	  

	
	//insert Users
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertUser(
			@FormParam("fname") String fname,
			 @FormParam("lname") String lname,
			 @FormParam("nic") String nic,
			 @FormParam("address") String address,
			 @FormParam("phone") String phone,
			 @FormParam("email") String email,
			 @FormParam("username") String username,
			 @FormParam("password") String password,
			 @FormParam("type") String type)
	
	{
		
		String output = usersobj.insertUser(fname, lname, nic, address, phone, email, username, password, type);
	
				return output;
	
	}
	
	//update profile details
	
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateUser(String pdata) {

			JsonObject userObj2 = new JsonParser().parse(pdata).getAsJsonObject();

			String U_id = userObj2.get("U_id").getAsString();
			String fname = userObj2.get("fname").getAsString();
			String lname = userObj2.get("lname").getAsString();
			String nic = userObj2.get("nic").getAsString();
			String address = userObj2.get("address").getAsString();
			String phone = userObj2.get("phone").getAsString();
			String email = userObj2.get("email").getAsString();
			String username = userObj2.get("username").getAsString();
			String password = userObj2.get("password").getAsString();

			
			
			
			String output = usersobj.updateUserinfo(U_id, fname, lname, nic, address, phone,email, username, password);

			return output;
		}
		
		
	/*
	 * //change password
	 * 
	 * @PUT
	 * 
	 * @Path("/")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.TEXT_PLAIN)
	 * 
	 * public String changePassword(String pdata) {
	 * 
	 * JsonObject userObj2 = new JsonParser().parse(pdata).getAsJsonObject();
	 * 
	 * // String U_id = userObj2.get("U_id").getAsString();
	 * 
	 * String username = userObj2.get("username").getAsString(); String password =
	 * userObj2.get("password").getAsString();
	 * 
	 * 
	 * 
	 * 
	 * String output = usersobj.updatePassword( username, password);
	 * 
	 * return output; }
	 */
	  
	  
	  
	  
		//delete profile details
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteUserinfo(String pdata) {
			Document doc = Jsoup.parse(pdata, "", Parser.xmlParser());
			String U_id = doc.select("U_id").text();

			String output = usersobj.deleteUserinfo(U_id);

			return output;
		}
		
	
	// login
		@POST
		@Path("/login")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		
		
		public String getlogin(String log) {
			
		
			
			Document doc = Jsoup.parse(log, "", Parser.xmlParser());
			String username = doc.select("username").text();
			String password = doc.select("password").text();
			
			
			
			
			String output = usersobj.login(username,password);
			
			return output;
		
				
		}

}
