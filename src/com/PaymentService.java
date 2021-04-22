

package com;

import javax.ws.rs.Consumes;




import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
	import org.jsoup.*;
	import org.jsoup.parser.*;
	import org.jsoup.nodes.Document;


import model.payment;

@Path("/payment") 
public class PaymentService {
	
	payment payObj = new payment();
	
	//view payment details
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readPayment() 
	 { 
	 return payObj.readpayment(); 
	 }

	
		
		//insert payment
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String addPayment(
		 @FormParam("Pay_id") int Pay_id,
		 @FormParam("order_ID") int order_ID,
		 @FormParam("Cus_Name") String Cus_Name,
		 @FormParam("Card_No") String Card_No,
		 @FormParam("Bank") String Bank,
		 @FormParam("Tot_Amount") String Tot_Amount,
		 @FormParam("Exp_date") String Exp_date,
		 @FormParam("CVV") String CVV,
		 @FormParam("Payment_date") String Payment_date)
		{
			
		 String output = payObj.addPayment(Pay_id,order_ID,Cus_Name,Card_No,Bank,Tot_Amount,Exp_date,CVV,Payment_date );
		 return output;
		 
		}
		
		//update Payment details
		@PUT  
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePayment(String paydata) {

			JsonObject payObj2 = new JsonParser().parse(paydata).getAsJsonObject();

			int Pay_id = payObj2.get("Pay_id").getAsInt();
			int order_ID = payObj2.get("order_ID").getAsInt();
			String Cus_Name = payObj2.get("Cus_Name").getAsString();
			String Card_No = payObj2.get("Card_No").getAsString();
			String Bank = payObj2.get("Bank").getAsString();
			String Tot_Amount = payObj2.get("Tot_Amount").getAsString();
			String Exp_date =payObj2.get("Exp_date").getAsString();
			String CVV = payObj2.get("CVV").getAsString();
			String Payment_date =payObj2.get("Payment_date").getAsString();
		
			
			
			
			String output = payObj2.updatePayment(Pay_id,order_ID, Cus_Name,Card_No, Bank,Tot_Amount,Exp_date,CVV,Payment_date);

			return output;
		}
		
		//delete Payment details
				@DELETE
				@Path("/")
				@Consumes(MediaType.APPLICATION_XML)
				@Produces(MediaType.TEXT_PLAIN)
				public String deletePayment(String paydata) {
					Document doc = Jsoup.parse(paydata, "", Parser.xmlParser());
					String Pay_id = doc.select("Pay_id").text();
 
					String output = payObj.deletePayment(Pay_id);

					return output;
				}
				
}
	
		
		