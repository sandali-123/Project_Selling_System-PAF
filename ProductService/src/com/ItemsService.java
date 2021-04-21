package com;

import model.Items;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Items") 

public class ItemsService {
	Items itemObj = new Items();
	
	//Read items
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return itemObj.readItem();
	 } 
	
	//Insert items
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertItem(
			 @FormParam("Item_Name") String Item_Name,
			 @FormParam("Item_Desc") String Item_Desc,
			 @FormParam("Item_price") String Item_price,
			 @FormParam("Stock_qty") String Stock_qty
			 
			 
			)
	{
		
		String output = itemObj.insertItem(Item_Name,Item_Desc,Item_price,Stock_qty);
		return output;
	
	}
	
	//update items
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String ItemData)
	{
		//Convert the input string to a JSON object
		 JsonObject itemObject = new JsonParser().parse(ItemData).getAsJsonObject();
		//Read the values from the JSON object
		 String Item_id = itemObject.get("Item_id").getAsString();
		 String Item_Name = itemObject.get("Item_Name").getAsString();
		 String Item_Desc = itemObject.get("Item_Desc").getAsString();
		 String Item_price = itemObject.get("Item_price").getAsString();
		 String Stock_qty = itemObject.get("Stock_qty").getAsString();
		 
		 String output = itemObj.updateItem(Item_id, Item_Name, Item_Desc, Item_price, Stock_qty);
		 return output;
	}
	
	//delete items
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String ItemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(ItemData, "", Parser.xmlParser());

	//Read the value from the element <Item_id>
	 String ItemID = doc.select("Item_id").text();
	 String output = itemObj.deleteItem(ItemID);
	
	 return output;
	}
	
	//find item relevent item name
	@GET
	@Path("/{Item_Name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String readItemName(@PathParam("Item_Name") String Item_Name)
	 {
		return itemObj.readItemName(Item_Name); 
	 }



}
