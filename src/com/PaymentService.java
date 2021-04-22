

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
	public String readItems() 
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
		
		
}
	
		
		