package com.mvc;

import java.io.Serializable;

public class LoginBean implements Serializable
{
	private String email,pass;
	public LoginBean()
	{
		System.out.println("In LoginBean() no-argument constructor");
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
		System.out.println("Email="+email);
	}
	public String getPass()
	{
		return pass;
	}
	public void setPass(String pass)
	{
		this.pass = pass;
		System.out.println("Pass="+pass);
	}
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginBean other = (LoginBean) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		return true;
	}
	@Override
	public String toString()
	{
		return "LoginBean [email=" + email + ", pass=" + pass + "]";
	}
	
	public String validate()
	{
		StringBuilder sb=new StringBuilder();
		String msg;
		if(email==null||email.trim().equals("")||!(email.contains("@")))
			sb.append("Email is mandatory to login<br>");
		if(pass==null||pass.trim().equals(""))
			sb.append("Password is mandatory<br>");
		msg=sb.toString();
		if(msg.equals(""))
			return CONSTANTS.SUCCESS;
		else
			return msg;
	}
	
}
