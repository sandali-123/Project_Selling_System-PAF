package com;

import model.Projects;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Projects")

public class ProjectsService {
	Projects ProjectObj = new Projects();
	//read project
	@GET
	@Path("/view")
	@Produces(MediaType.TEXT_HTML)
	
	public String readProjects()
	 {
		return ProjectObj.readProjects();
	 }
	
	//insert project
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertProject(
			
		@FormParam("Proj_type") String ProjectType,
		@FormParam("Proj_name") String ProjectName,
		@FormParam("Proj_link") String ProjectLink,
		@FormParam("Proj_Desc") String ProjectDesc,
		@FormParam("Price") String Price,
		@FormParam("Submit_date") String SubmitDate
		)
	{
		
		String result = ProjectObj.insertProject(ProjectType, ProjectName, ProjectLink, ProjectDesc, Price, SubmitDate);
		return result;
	
	}
	
	//update project
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateProject(String ProjectData)
	{
		//Convert the input string to a JSON object
		 JsonObject projectObject = new JsonParser().parse(ProjectData).getAsJsonObject();
		//Read the values from the JSON object
		 String ProjectID = projectObject.get("Proj_id").getAsString();
		 String ProjectType = projectObject.get("Proj_type").getAsString();
		 String ProjectName = projectObject.get("Proj_name").getAsString();
		 String ProjectLink = projectObject.get("Proj_link").getAsString();
		 String ProjectDesc = projectObject.get("Proj_Desc").getAsString();
		 String Price = projectObject.get("Price").getAsString();
		 String SubmitDate = projectObject.get("Submit_date").getAsString();
		 
		 String result = ProjectObj.updateProject(ProjectID, ProjectType, ProjectName, ProjectLink, ProjectDesc,Price,SubmitDate);
		 return result;
	}
	
	//delete projecct
	
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteProject(String ProjectData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(ProjectData, "", Parser.xmlParser());

		//Read the value from the element <Proj_id>
		String ProjectID = doc.select("Proj_id").text();
		
		String result = ProjectObj.deleteProject(ProjectID);
		return result;
	}
	
	//////view project relevent id
	
	@GET
	@Path("/details/{Proj_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	
	public String viewProjetct(@PathParam("Proj_id") String ProjID)
	 {
		return ProjectObj.viewProjetct(ProjID);
	 }
	
	////find project relevent name
	@GET
	@Path("/{Proj_name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String readProjectname(@PathParam("Proj_name") String Proj_name)
	 {
		return ProjectObj.readProjectname(Proj_name); 
	 }
	

}
