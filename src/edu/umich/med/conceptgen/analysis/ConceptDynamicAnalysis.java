package edu.umich.med.conceptgen.analysis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;

import edu.umich.med.conceptgen.datasource.QueryExecuter;

public class ConceptDynamicAnalysis {

	private ResourceBundle sql = ResourceBundle.getBundle("edu.umich.med.conceptgen.resource.bundle.sql");
	private ConceptEngine engine = new ConceptEngine();
	private ArrayList<String> conceptTypeFilter = new ArrayList<String>();
	private String dictionaryId = "322";
	private String conceptTypeId = "33";
	private String conceptId = "0";
	
	private QueryExecuter db = new QueryExecuter();
	
	
	public ConceptDynamicAnalysis (String conceptName, ArrayList<String> elementList, String owner) throws SQLException
	{
		conceptTypeFilter.add("0");

		System.out.println("Truncating Tables");
	    db.execQuery(sql.getString("truncateElementTmp"));
	    db.execQuery(sql.getString("truncateConceptDemand"));
	    db.execQuery(sql.getString("truncateConceptSetDemand"));
	    db.execQuery(sql.getString("truncateFisherStatsDemand"));
	    
	    System.out.println("Insert Element Tmp Table");
	    insertElementList(elementList);
	    System.out.println("Data Load");
	    conceptId = dataSetup(conceptName, conceptTypeId, owner, String.valueOf(elementList.size()));
	    System.out.println("Start Analysis");
	    engine.analyze(conceptId, conceptTypeFilter);
	}
	
	
	private String dataSetup(String conceptName, String conceptTypeId, String owner, String elementSize) throws SQLException
	{
		//SET CONCEPTID*********************************************************************************************************************

	    String conceptId = "1";  

	    //INSERT CONCEPT********************************************************************************************************************
	    
	    String query = sql.getString("insertConcept");
	    query = query.replaceFirst("\\?", conceptId);
	    query = query.replaceFirst("\\?", conceptName);
	    query = query.replaceFirst("\\?", conceptTypeId);
	    query = query.replaceFirst("\\?", owner);   
	    query = query.replaceFirst("\\?", dictionaryId);
	    query = query.replaceFirst("\\?", elementSize);
	    
	    db.execQuery(query);
	       
	    query = sql.getString("insertConceptSetGeneId");
	    query = query.replaceFirst("\\?", conceptId);	
	    db.execQuery(query);
	      
	    
		db.execQuery(sql.getString("truncateElementTmp"));
		
		return conceptId;   
	}
	
	private void insertElementList(ArrayList<String> elementList) throws SQLException
	{
		Vector<String> distinctElements = new Vector<String>();
		Vector<String> queryList = new Vector<String>();
		String tmp = "";
		String query = "";
		
		for (int i = 0; i < elementList.size(); i++)
		{
			tmp = (String)elementList.get(i).trim();
			if (!(tmp == null || "".equals(tmp.trim())))
			{
				if (!distinctElements.contains(tmp))
				{
					query = sql.getString("insertElementTmp");
					query = query.replaceFirst("\\?", tmp);
					queryList.add(query);
					distinctElements.add(tmp);				
				}
			}
		}
		
		db.batchExecQuery(queryList);
	}

	
}
