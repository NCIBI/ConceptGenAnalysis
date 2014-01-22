package edu.umich.med.conceptgen.service;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import edu.umich.med.conceptgen.analysis.ConceptDynamicAnalysis;
import edu.umich.med.conceptgen.analysis.DataValidation;
import edu.umich.med.conceptgen.analysis.HomologeneConversion;

/**
 * @author Vasudeva Mahavisno
 * @email vmahavis@gmail.com
 * @version 1.0 Jan 22, 2014
 */

public class TestServices
{

	@Test
	public void test()
	{
		//ArrayList<String> conceptType = (ArrayList<String>) Arrays.asList(ct.split(",")); 
		//ArrayList<String> elementList = (ArrayList<String>) Arrays.asList(geneid.split(",")); 
		
		ArrayList<String> conceptType = new ArrayList<String>();
		conceptType.add("0");
		ArrayList<String> elementList = new ArrayList<String>();
		elementList.add("196792");
		elementList.add("196860");
		elementList.add("196872");
		elementList.add("196883");
		elementList.add("196913");
		elementList.add("196951");

		
		String owner = "anonymous";
		String conceptName = "conceptTemp";
		String species = "Human";
		String threshold = "0.05";
		String outputType = "text";

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
			System.out.println("Data Invalid!");
			for(String error : dv.getErrorReport())
			{
				System.out.println(error);
			}
		}
	
	}

}
