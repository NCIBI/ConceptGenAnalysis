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
		elementList.add("5970");
		elementList.add("4790");
		elementList.add("7099");
		elementList.add("5111");
		elementList.add("3383");
		elementList.add("8517");
		elementList.add("5347");
		elementList.add("5580");
		elementList.add("1111");
		
		String owner = "anonymous";
		String conceptName = "conceptTemp";
		String species = "Human";
		String threshold = "0.05";
		String outputType = "text";

		HomologeneConversion hc = new HomologeneConversion();
		
		/* DATA VALIDATION ---------------------------------------------------------------------------------------------------------------*/
		
		DataValidation dv = new DataValidation(species, conceptType, threshold, elementList);
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
