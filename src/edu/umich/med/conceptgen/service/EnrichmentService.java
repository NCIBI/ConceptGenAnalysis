package edu.umich.med.conceptgen.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import edu.umich.med.conceptgen.analysis.ConceptDynamicAnalysis;
import edu.umich.med.conceptgen.analysis.DataValidation;
import edu.umich.med.conceptgen.analysis.HomologeneConversion;
import edu.umich.med.conceptgen.result.EnrichmentResult;
import edu.umich.med.conceptgen.result.ResultManager;

/**
 * @author Vasudeva Mahavisno
 * @email vmahavis@gmail.com
 */

public class EnrichmentService
{
	@GET
	@Path("/analysis/text")
	@Produces(MediaType.TEXT_HTML)
	public List<EnrichmentResult> run(@FormParam("s") String species, @FormParam("ct") String ct, @FormParam("t") String threshold,
			@FormParam("geneid") String geneid)
	{

		ArrayList<String> conceptType = (ArrayList<String>) Arrays.asList(ct.split(","));
		ArrayList<String> elementList = (ArrayList<String>) Arrays.asList(geneid.split(","));
		String owner = "anonymous";
		String conceptName = "conceptTemp";

		HomologeneConversion hc = new HomologeneConversion();
		ResultManager rm = new ResultManager();
		DataValidation dv = new DataValidation(species, conceptType, threshold, elementList);
		if (dv.getErrorReport().isEmpty())
		{
			try
			{
				if (!species.equals("Human"))
				{
					elementList = (ArrayList<String>) hc.run(species, elementList);
				}
				new ConceptDynamicAnalysis(conceptName, elementList, owner);
				return rm.get();
			}
			catch (SQLException e)
			{
				System.out.println(e);
				return null;
			}
		}
		else
		{
			return null;
		}
	}
}
