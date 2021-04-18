package com;

import model.Product;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Product")

public class ProductService {
	Product ProductObj = new Product();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
	 return ProductObj.readItems();
	 }

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertProduct(
			 @FormParam("Proj_type") String ProjectType,
			 @FormParam("Proj_name") String ProjectName,
			 @FormParam("Proj_link") String ProjectLink,
			 @FormParam("Proj_Desc") String ProjectDesc,
			 @FormParam("Price") String Price,
			 @FormParam("Submit_date") String SubmitDate
			 
			)
	{
		
		String output = ProductObj.insertProduct(ProjectType, ProjectName, ProjectLink, ProjectDesc, Price, SubmitDate);
	
				return output;
	
	}
	
	//update
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String ProductData)
	{
		//Convert the input string to a JSON object
		 JsonObject productObject = new JsonParser().parse(ProductData).getAsJsonObject();
		//Read the values from the JSON object
		 String ProjectID = productObject.get("Proj_id").getAsString();
		 String ProjectType = productObject.get("Proj_type").getAsString();
		 String ProjectName = productObject.get("Proj_name").getAsString();
		 String ProjectLink = productObject.get("Proj_link").getAsString();
		 String ProjectDesc = productObject.get("Proj_Desc").getAsString();
		 String Price = productObject.get("Price").getAsString();
		 String SubmitDate = productObject.get("Submit_date").getAsString();
		 String output = ProductObj.updateProduct(ProjectID, ProjectType, ProjectName, ProjectLink, ProjectDesc,Price,SubmitDate);
		return output;
	}
	
	//delete
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String ProductData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(ProductData, "", Parser.xmlParser());

	//Read the value from the element <Proj_id>
	 String ProjectID = doc.select("Proj_id").text();
	 String output = ProductObj.deleteItem(ProjectID);
	return output;
	}


}
