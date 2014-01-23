package edu.umich.med.conceptgen.statistics;

import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;

import edu.umich.med.conceptgen.datasource.QueryExecuter;

public class FDR {
	
	public void execute() throws SQLException, InstantiationException
	{
		ResourceBundle sql = ResourceBundle.getBundle("edu.umich.med.conceptgen.resource.bundle.engine");
		QueryExecuter db = new QueryExecuter();		
		
		String query = sql.getString("insertFisherStatsTmpPublic");

		
		Vector<String> conceptTypeId = db.getData(sql.getString("selectDistinctConceptType"));
		
		double[] tmp2 = new double[1];
		Vector values = new Vector();

		for (int i = 0; i < conceptTypeId.size(); i++) {
			tmp2 = new double[1];
			tmp2[0] = Double.parseDouble((String) conceptTypeId.get(i));
			values.add(tmp2);
		}

		db.batchExecQuery(query, values);
		db.execQuery(sql.getString("updateFisherStatsFDR"));
		db.execQuery(sql.getString("updateFisherStatsFDREase"));
		db.execQuery(sql.getString("truncateFisherStatsDemand"));
		db.execQuery(sql.getString("appendFisherStats"));
		db.execQuery(sql.getString("truncateFisherStatsDemandTmp"));
	}
}
