package edu.umich.med.conceptgen.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.FormParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import edu.umich.med.conceptgen.analysis.ConceptDynamicAnalysis;
import edu.umich.med.conceptgen.analysis.DataValidation;
import edu.umich.med.conceptgen.analysis.HomologeneConversion;


/**
 * @author Vasudeva Mahavisno
 * @email vmahavis@gmail.com
 */

@Path("/analysis")
public class EnrichmentService
{
	
	@SuppressWarnings("unchecked")	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void run(@FormParam("s") String species, @FormParam("ct") String ct, @FormParam("t") String threshold,
			@FormParam("ot") String outputType, @FormParam("geneid") String geneid)
	{
		
		ArrayList<String> conceptType = (ArrayList<String>) Arrays.asList(ct.split(",")); 
		ArrayList<String> elementList = (ArrayList<String>) Arrays.asList(geneid.split(",")); 
		String owner = "anonymous";
		String conceptName = "conceptTemp";

		HomologeneConversion hc = new HomologeneConversion();
		
		/* DATA VALIDATION ---------------------------------------------------------------------------------------------------------------*/
		
		DataValidation dv = new DataValidation(species, conceptType, threshold, outputType, elementList);
		if(dv.getErrorReport().isEmpty())
		{
			try
			{
				if (!species.equals("Human"))
				{
					elementList = (ArrayList<String>) hc.run(species, elementList);
				}
				new ConceptDynamicAnalysis(conceptName, elementList, owner);
				
				
				
			}
			catch (SQLException e)
			{
				System.out.println(e);
			}
		}
		else
		{
			
		}
	
	}
	

}
