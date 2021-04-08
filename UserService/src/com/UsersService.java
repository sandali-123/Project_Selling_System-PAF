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

}
