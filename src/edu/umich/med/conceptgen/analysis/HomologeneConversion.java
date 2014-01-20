package edu.umich.med.conceptgen.analysis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.umich.med.conceptgen.datasource.QueryExecuter;
import edu.umich.med.conceptgen.util.SqlUtil;

/**
 * @author Vasudeva Mahavisno
 * @email vmahavis@gmail.com
 * @version 1.0 Jan 20, 2014
 */

public class HomologeneConversion
{
	private ResourceBundle sql = ResourceBundle.getBundle("edu.umich.edu.conceptGen.resource.bundle.sql");
	private QueryExecuter db = new QueryExecuter();

	public ArrayList<?> run(String species, ArrayList<String> geneIdList) throws SQLException
	{
		SqlUtil ut = new SqlUtil();
		String query = sql.getString("homologGeneIdSearch");	
		query = query.replaceFirst("\\?", species);
		query = ut.constructQueryArrayNumComma(query, geneIdList);
	
		return db.selectSingleList(query);
	}
}
