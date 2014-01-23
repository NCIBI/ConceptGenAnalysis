package edu.umich.med.conceptgen.result;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import edu.umich.med.conceptgen.datasource.QueryExecuter;

/**
 * @author Vasudeva Mahavisno
 * @email vmahavis@gmail.com
 * @version 1.0 Jan 23, 2014
 */

public class ResultManager
{
	private ResourceBundle sql = ResourceBundle.getBundle("edu.umich.med.conceptgen.resource.bundle.sql");
	private QueryExecuter db = new QueryExecuter();
	
	public List<EnrichmentResult> get() throws SQLException
	{
		String query = sql.getString("enrichmentResult");
		ArrayList<ArrayList<String>> d = db.selectTableFunction(query);
		List<EnrichmentResult> result = new ArrayList<EnrichmentResult>();
		EnrichmentResult er;
		
		for(ArrayList<String> list : d)
		{
			er = new EnrichmentResult();

			er.setConcept_name(list.get(0));
			er.setA(Double.parseDouble(list.get(1)));
			er.setB(Double.parseDouble(list.get(2)));
			er.setC(Double.parseDouble(list.get(3)));
			er.setD(Double.parseDouble(list.get(4)));
			er.setOdds_ratio(Double.parseDouble(list.get(65)));
			er.setP_value(Double.parseDouble(list.get(6)));
			er.setEase_score(Double.parseDouble(list.get(7)));
			er.setKappa(Double.parseDouble(list.get(8)));
			er.setFdr(Double.parseDouble(list.get(9)));
			er.setFdr_ease(Double.parseDouble(list.get(10)));
				
			result.add(er);
		}
		
		return result;
		
	}
}
