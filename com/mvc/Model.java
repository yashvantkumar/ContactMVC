package com.mvc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Model
{
	public String register(RegBean bean)
	{
		String msg=bean.validate();
		if(msg.equals(CONSTANTS.SUCCESS))
		{
			Connection con=null;
			ResultSet rs=null;
			PreparedStatement ps_sel=null,ps_ins=null;
			String qr1="select * from  register_cont where email=?";
			String qr2="insert into  register_cont(name,email,pass) values(?,?,?)";
			try
			{
				con=JDBCHelper.getConnection();
				if(con==null)
				{
					return "Oops! :(( Connection to the database is failed try after sometime...!<br>";
				}
				else
				{
					ps_sel=con.prepareStatement(qr1);
					ps_sel.setString(1, bean.getEmail());
					//ps_sel.setString(2, bean.getPass());
					ps_sel.execute();
					rs=ps_sel.getResultSet();
					if(rs.next())
					{
						return "Your Email-ID already registered...<br>Click on login link to login <a href='Login.jsp'>Login</a><br>";
					}
					else
					{
						ps_ins=con.prepareStatement(qr2);
						ps_ins.setString(1, bean.getUname());
						ps_ins.setString(2, bean.getEmail());
						ps_ins.setString(3, bean.getPass());
						ps_ins.execute();
						return CONSTANTS.SUCCESS;
					}
					
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return "Error...try again!";
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps_ins);
				JDBCHelper.close(ps_sel);
				JDBCHelper.close(con);
			}
		}
		else
			return msg;
	}
	
	public String authenticate(LoginBean bean)
	{
		String msg=bean.validate();
		if(msg.equals(CONSTANTS.SUCCESS))
		{
			Connection con=null;
			ResultSet rs=null;
			PreparedStatement ps_sel=null;
			String qry="select * from  register_cont where email=? and pass=?";
			try
			{
				con=JDBCHelper.getConnection();
				if(con==null)
				{
					return "Oops! :(( Connection to the database is failed try after sometime...!<br>";
				}
				else
				{
					ps_sel=con.prepareStatement(qry);
					ps_sel.setString(1, bean.getEmail());
					ps_sel.setString(2, bean.getPass());
					ps_sel.execute();
					rs=ps_sel.getResultSet();
					if(rs.next())
					{
						return CONSTANTS.SUCCESS;
					}
					else
					{
						return "Your Email-Id is not registered<br><a href='openregisterview.do'>Click here to register</a>";
					}
				}
					
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return "we regret that something went wrong...please try again sometime<br>";
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps_sel);
				JDBCHelper.close(con);
			}
		}
		else
			return msg;
	}
	
	public List<RegBean> getUsers()
	{
		List<RegBean> list=new ArrayList<RegBean>();
		String qry="select * from  register_cont";
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps_sel=null;
		try
		{
			con=JDBCHelper.getConnection();
			if(con==null)
			{
				return null;
			}
			else
			{
				ps_sel=con.prepareStatement(qry);
				ps_sel.execute();
				rs=ps_sel.getResultSet();
				RegBean bean=null;
				while(rs.next())
				{
					String name=rs.getString("name");
					String email=rs.getString("email");
					bean=new RegBean();
					bean.setEmail(email);
					bean.setUname(name);
					
					list.add(bean);
				}
				return list;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_sel);
			JDBCHelper.close(con);
		}
	}
	public RegBean getUserDetail(String email)
	{
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps_sel=null;
		String qry="select * from  register_cont where email = ?";
		try
		{
			con=JDBCHelper.getConnection();
			if(con==null)
			{
				return null;
			}
			else
			{
				ps_sel=con.prepareStatement(qry);
				ps_sel.setString(1, email);
				ps_sel.execute();
				rs=ps_sel.getResultSet();
				RegBean bean=null;;
				if(rs.next())
				{
					bean=new RegBean();
					bean.setUname(rs.getString("name"));
					bean.setEmail(rs.getString("email"));
					bean.setPass(rs.getString("pass"));
				}
				return bean;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_sel);
			JDBCHelper.close(con);
		}
		
	}
	public String UpdateRows(RegBean bean)
	{
		String msg=bean.validate();
		if(msg.equals(CONSTANTS.SUCCESS))
		{
			Connection con=null;
			ResultSet rs=null;
			PreparedStatement ps_up=null;
			String qry="update  register_cont set name = ?,pass = ? where email = ? ";
			try
			{
				con=JDBCHelper.getConnection();
				if(con==null)
				{
					return "Oops! :(( Connection to the database is failed try after sometime...!<br>";
				}
				else
				{
					ps_up=con.prepareStatement(qry);
					ps_up.setString(1, bean.getUname());
					ps_up.setString(2, bean.getPass());
					ps_up.setString(3, bean.getEmail());
					ps_up.execute();
					return CONSTANTS.SUCCESS;
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return "Oops! :(( Connection to the database is failed try after sometime...!<br>";
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps_up);
				JDBCHelper.close(con);
			}
		}
		else
			return msg;
	}
	/**
	 * @param bean
	 * @return
	 */
	/**
	 * @param bean
	 * @return
	 */
	public String addContact(AddContactBean bean)
	{
		String msg=bean.validate();
		if(msg.contains(CONSTANTS.SUCCESS))
		{
			Connection con=null;
			ResultSet rs=null;
			PreparedStatement ps_sel=null,ps_ins=null;
			//String qry1="select * from contact_email_mvc where email = ?";
			String qry2="insert into contacts_mvc(name,address,dob,gender) values(?,?,?,?)";
			try
			{
				con=JDBCHelper.getConnection();
				if(con==null)
				{
					return "Connection to the database failed try again sometimes...<br>";
				}
				else
				{
					//ps_sel=con.prepareStatement(qry1);
					//ps_sel.setString(1, bean.getCemail());
					//ps_sel.execute();
					//rs=ps_sel.getResultSet();
					//if(rs.next())
					//{
						//return "Email already exist...<br>";
					//}
					//else
					{
						ps_ins=con.prepareStatement(qry2);
						ps_ins.setString(1,bean.getCname());
						ps_ins.setString(2, bean.getCemail());
						ps_ins.setString(3, bean.getDob());
						ps_ins.setString(4, bean.getCgen());
						ps_ins.execute();
						return CONSTANTS.SUCCESS;
					}
				}
				
				
				//return CONSTANTS.SUCCESS;
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return "";
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps_sel);
				JDBCHelper.close(ps_ins);
				JDBCHelper.close(con);
			}
		}
		else
			return msg;
	}
}
