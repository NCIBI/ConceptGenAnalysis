package edu.umich.med.conceptgen.result;

/**
 * @author Vasudeva Mahavisno
 * @email vmahavis@gmail.com
 * @version 1.0 Jan 20, 2014
 */

public class EnrichmentResult
{
	private String concept_name;
	private double a;
	private double b;
	private double c;
	private double d;
	private double odds_ratio;
	private double p_value;
	private double ease_score;
	private double kappa;
	private double fdr;
	private double fdr_ease;

	public String getConcept_name()
	{
		return concept_name;
	}

	public void setConcept_name(String concept_name)
	{
		this.concept_name = concept_name;
	}

	public double getA()
	{
		return a;
	}

	public void setA(double a)
	{
		this.a = a;
	}

	public double getB()
	{
		return b;
	}

	public void setB(double b)
	{
		this.b = b;
	}

	public double getC()
	{
		return c;
	}

	public void setC(double c)
	{
		this.c = c;
	}

	public double getD()
	{
		return d;
	}

	public void setD(double d)
	{
		this.d = d;
	}

	public double getOdds_ratio()
	{
		return odds_ratio;
	}

	public void setOdds_ratio(double odds_ratio)
	{
		this.odds_ratio = odds_ratio;
	}

	public double getP_value()
	{
		return p_value;
	}

	public void setP_value(double p_value)
	{
		this.p_value = p_value;
	}

	public double getEase_score()
	{
		return ease_score;
	}

	public void setEase_score(double ease_score)
	{
		this.ease_score = ease_score;
	}

	public double getKappa()
	{
		return kappa;
	}

	public void setKappa(double kappa)
	{
		this.kappa = kappa;
	}

	public double getFdr()
	{
		return fdr;
	}

	public void setFdr(double fdr)
	{
		this.fdr = fdr;
	}

	public double getFdr_ease()
	{
		return fdr_ease;
	}

	public void setFdr_ease(double fdr_ease)
	{
		this.fdr_ease = fdr_ease;
	}
}
