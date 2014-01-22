package edu.umich.med.conceptgen.analysis;

import java.util.ArrayList;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author Vasudeva Mahavisno
 * @email vmahavis@gmail.com
 * @version 1.0 Jan 15, 2014
 */

public class DataValidation
{
	private String species;
	private String threshold;
	private String outputType;
	private ArrayList<String> conceptType;
	private ArrayList<String> geneIdList;
	private ArrayList<String> errorReport = new ArrayList<String>();

	public DataValidation(String species, ArrayList<String> conceptType, String threshold, String outputType, ArrayList<String> geneIdList)
	{
		this.species = species;
		this.threshold = threshold;
		this.outputType = outputType;
		this.conceptType = conceptType;
		this.geneIdList = geneIdList;

		// START VALIDATION
		// -------------------------------------------------------------------------------------------------------------------------------//

		validateSpecies();
		//validateThreshold();
		validateOutputType();
		validateGeneId();
		//validateConceptType();
	}

	private void validateSpecies()
	{
		if (species.equals("Human") || species.equals("Mouse") || species.equals("Rat"))
		{
		}
		else
		{
			errorReport.add("Invalid Species");
		}
	}

	private void validateThreshold()
	{
		if(NumberUtils.isNumber(threshold))
		{
			errorReport.add("Invalid Threshold Value");
		}
	}

	private void validateOutputType()
	{
		if (outputType.equals("text") || outputType.equals("json"))
		{
			
		}
		else
		{
			errorReport.add("Invalid Output Value");
		}
	}

	private void validateGeneId()
	{

		boolean valid = true;
		for (String geneId : geneIdList)
		{
			if (!NumberUtils.isNumber(geneId))
			{
				valid = false;
			}
		}
		
		if(!valid)
		{
			errorReport.add("Invalid GeneId");
		}
	}

	private void validateConceptType()
	{
		boolean valid = true;
		ArrayList<String> conceptTypeList = new ArrayList<String>();
		conceptTypeList.add("Biocarta");
		conceptTypeList.add("GOBP");
		conceptTypeList.add("GOCC");
		conceptTypeList.add("GOMF");
		conceptTypeList.add("Kegg");
		conceptTypeList.add("Panther");
		conceptTypeList.add("pFAM");
		conceptTypeList.add("MiMI");
		conceptTypeList.add("MeSH");
		conceptTypeList.add("OMIM");
		conceptTypeList.add("Cytoband");
		conceptTypeList.add("Metabolite");
		conceptTypeList.add("DrugBank");
		conceptTypeList.add("TransFac");
		conceptTypeList.add("miRBase");
		conceptTypeList.add("ALL");
		
		for(String ct : conceptType)
		{
			if(!conceptTypeList.contains(ct))
			{
				valid = false;
			}
		}
		
		if(!valid)
		{
			errorReport.add("Invalid Concept Type");
		}
		
	}

	public ArrayList<String> getErrorReport()
	{
		return errorReport;
	}

	public void setErrorReport(ArrayList<String> errorReport)
	{
		this.errorReport = errorReport;
	}
}
