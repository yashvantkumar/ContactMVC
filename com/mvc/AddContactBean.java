package com.mvc;

public class AddContactBean 
{
	private String cname;
	private String cemail;
	private String dob;
	private String cphone;
	private String ctag;
	private String cgen;
	
	public String validate()
	{
		StringBuilder sb=new StringBuilder();
		String msg;
		if(cname==null||cname.trim().equals(""))
			sb.append("Enter the valid contact name<br>");
		if(cemail==null||cemail.trim().equals("")||!(cemail.contains("@")))
			sb.append("Enter the valid Email-id<br>");
		if(dob==null||dob.trim().equals(""))
			sb.append("Dob is mandatory<br>");
		if(cphone==null||cphone.trim().equals(""))
			sb.append("Phone number cannot be empty<br>");
		if(ctag==null||ctag.trim().equals(""))
			sb.append("Enter the valid tag<br>");
		
		msg=sb.toString();
		if(msg.contains(""))
			return CONSTANTS.SUCCESS;
		else
			return msg;
	}
	
	public AddContactBean()
	{
		System.out.println("In AddContatcBean() no-argument constructor");
	}
	
	public String getCname()
	{
		return cname;
	}
	public void setCname(String cname)
	{
		this.cname = cname;
		System.out.println("Cname="+cname);
	}
	public String getCemail() {
		return cemail;
	}
	public void setCemail(String cemail) 
	{
		this.cemail = cemail;
		System.out.println("Cemail="+cemail);
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob)
	{
		this.dob = dob;
		System.out.println("Cdob="+dob);
	}
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone)
	{
		this.cphone = cphone;
		System.out.println("Cphone="+cphone);
	}
	public String getCtag() {
		return ctag;
	}
	public void setCtag(String ctag)
	{
		this.ctag = ctag;
		System.out.println("Tag="+ctag);
	}
	public String getCgen() {
		return cgen;
	}
	public void setCgen(String cgen)
	{
		this.cgen = cgen;
		System.out.println("Cgen="+cgen);
	}
	public AddContactBean(String cname, String cemail, String dob, String cphone, String ctag, String cgen) {
		super();
		this.cname = cname;
		this.cemail = cemail;
		this.dob = dob;
		this.cphone = cphone;
		this.ctag = ctag;
		this.cgen = cgen;
	}
	@Override
	public String toString() {
		return "AddContactBean [cname=" + cname + ", cemail=" + cemail + ", dob=" + dob + ", cphone=" + cphone
				+ ", ctag=" + ctag + ", cgen=" + cgen + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cemail == null) ? 0 : cemail.hashCode());
		result = prime * result + ((cgen == null) ? 0 : cgen.hashCode());
		result = prime * result + ((cname == null) ? 0 : cname.hashCode());
		result = prime * result + ((cphone == null) ? 0 : cphone.hashCode());
		result = prime * result + ((ctag == null) ? 0 : ctag.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddContactBean other = (AddContactBean) obj;
		if (cemail == null) {
			if (other.cemail != null)
				return false;
		} else if (!cemail.equals(other.cemail))
			return false;
		if (cgen == null) {
			if (other.cgen != null)
				return false;
		} else if (!cgen.equals(other.cgen))
			return false;
		if (cname == null) {
			if (other.cname != null)
				return false;
		} else if (!cname.equals(other.cname))
			return false;
		if (cphone == null) {
			if (other.cphone != null)
				return false;
		} else if (!cphone.equals(other.cphone))
			return false;
		if (ctag == null) {
			if (other.ctag != null)
				return false;
		} else if (!ctag.equals(other.ctag))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		return true;
	}
}
