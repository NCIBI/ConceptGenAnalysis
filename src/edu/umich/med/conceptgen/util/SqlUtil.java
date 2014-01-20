package edu.umich.med.conceptgen.util;

import java.util.ArrayList;

public class SqlUtil
{
	public String constructQuery(String query, String queryValue)
	{
		String[] values = queryValue.split(" ");
		StringBuffer q = new StringBuffer();

		for (int i = 0; i < values.length; i++)
		{
			if (i != 0)
			{
				q.append(", '" + values[i] + "'");
			}
			else
			{
				q.append("'" + values[i] + "'");
			}
		}

		return query.replaceFirst("\\?", q.toString());
	}

	public String constructQueryNewLineNumber(String query, String queryValue)
	{
		String[] values = queryValue.split("\n");
		StringBuffer q = new StringBuffer();

		for (int i = 0; i < values.length; i++)
		{
		 	try {
				double id = Double.parseDouble(values[i].trim());
				
				if (i != 0)
				{
					q.append(", " + id + " ");
				}
				else
				{
					q.append(" " + id + " ");
				}
		 	}
			catch (NumberFormatException e) {

			}

		
		}

		return query.replaceFirst("\\?", q.toString());
	}
	
	public String constructQueryNewLineString(String query, String queryValue)
	{
		String[] values = queryValue.split("\n");
		StringBuffer q = new StringBuffer();

		for (int i = 0; i < values.length; i++)
		{
			if (i != 0)
			{
				q.append(", lower('" + values[i].trim() + "')");
			}
			else
			{
				q.append("lower('" + values[i].trim() + "')");
			}
		}

		return query.replaceFirst("\\?", q.toString());
	}

	public String constructQueryComma(String query, String queryValue)
	{
		String[] values = queryValue.split(",");
		StringBuffer q = new StringBuffer();

		for (int i = 0; i < values.length; i++)
		{
			if (i != 0)
			{
				q.append(", '" + values[i].trim() + "'");
			}
			else
			{
				q.append("'" + values[i].trim() + "'");
			}
		}

		return query.replaceFirst("\\?", q.toString());
	}

	public String constructQueryArrayNumComma(String query, ArrayList list)
	{
		StringBuffer q = new StringBuffer();
		String tmp = "";

		for (int i = 0; i < list.size(); i++)
		{
			tmp = (String) list.get(i);
			if (i != 0)
			{
				q.append(", " + tmp.trim() + "");
			}
			else
			{
				q.append("" + tmp.trim() + "");
			}
		}

		return query.replaceFirst("\\?", q.toString());
	}

	public String constructQueryCommaAll(String query, String queryValue)
	{
		String[] values = queryValue.split(",");
		StringBuffer q = new StringBuffer();

		for (int i = 0; i < values.length; i++)
		{
			if (i != 0)
			{
				q.append(", '" + values[i].trim() + "'");
			}
			else
			{
				q.append("'" + values[i].trim() + "'");
			}
		}

		return query.replaceAll("\\?", q.toString());
	}

	public String constructQueryDash(String query, String queryValue)
	{
		String[] values = queryValue.split("_");
		StringBuffer q = new StringBuffer();

		for (int i = 0; i < values.length; i++)
		{
			if (i != 0)
			{
				q.append(", '" + values[i].trim() + "'");
			}
			else
			{
				q.append("'" + values[i].trim() + "'");
			}
		}

		return query.replaceFirst("\\?", q.toString());
	}
}
