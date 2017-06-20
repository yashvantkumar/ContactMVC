package com.mvc;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;




public class ControllerServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
   
    public ControllerServlet() 
    {
        super();
        System.out.println("In no-argument constructor of ControllerServlet()");
    }
    
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	System.out.println("In->process() method");
    	
    	Model model=new Model();
    	String uri=request.getRequestURI();
    	System.out.println("URI="+uri);
    	RequestDispatcher rd=null;
    	if(uri.contains("/openregisterview.do"))
    	{
    		rd=request.getRequestDispatcher("Register.jsp");
    		rd.forward(request, response);
    	}
    	if(uri.contains("/openloginview.do"))
    	{
    		rd=request.getRequestDispatcher("Login.jsp");
    		rd.forward(request, response);
    	}
    	if(uri.contains("/openEditView.do"))
    	{
    		HttpSession session=request.getSession(false);
    		if(session==null|| session.getAttribute("user")==null)
    		{
    			request.setAttribute("errormsg", "You are not logged in.");
    			rd=request.getRequestDispatcher("Error.jsp");
    			rd.forward(request, response);
    		}
    		else
    		{
    			String email=(String) session.getAttribute("user");
    			RegBean bean=model.getUserDetail(email);
    			request.setAttribute("userDetails", bean);
    			rd=request.getRequestDispatcher("EditAccount.jsp");
    			rd.forward(request, response);
    		}
    	}
    	if(uri.contains("/register.do"))
    	{
    		RegBean rb=(RegBean) request.getAttribute("reg");
    		System.out.println("Register Bean="+rb);
    		System.out.println("Bean....");
    		String result=model.register(rb);
    		System.out.println(result);
    		if(result.equals(CONSTANTS.SUCCESS))
    		{
    			System.out.println("Successfull");
    			request.setAttribute("message", "Your registration has been successfull <a href='index.jsp'>Click here to go to the HomePage.</a>");
    			rd=request.getRequestDispatcher("Success.jsp");
    			rd.forward(request, response);
    			return;
    		}
    		else
    		{
    			System.out.println("Failed...");
    			request.setAttribute("errormsg", result);
    			rd=request.getRequestDispatcher("Register.jsp");
    			rd.forward(request, response);
    			return;
    		}
    	}
    	if(uri.contains("/login.do"))
    	{
    		LoginBean lg=(LoginBean) request.getAttribute("log");
    		System.out.println(lg);
    		String result=model.authenticate(lg);
    		if(result.equals(CONSTANTS.SUCCESS))
    		{
    			HttpSession session=request.getSession(true);
    			session.setAttribute("user", lg.getEmail());
    			rd=request.getRequestDispatcher("Menu.jsp");
    			rd.forward(request, response);
    		}
    		else
    		{
    			System.out.println("Failed...");
    			request.setAttribute("errormsg", result);
    			rd=request.getRequestDispatcher("Login.jsp");
    			rd.forward(request, response);
    			return;
    		}
    	}
    	if(uri.contains("/openusers.do"))
    	{
    		List<RegBean> list=model.getUsers();
    		System.out.println(list);
    		if(list!=null)
    		{
	    		request.setAttribute("list", list);
	    		rd=request.getRequestDispatcher("Users.jsp");
	    		rd.forward(request, response);
    		}
    		else
    		{
    			request.setAttribute("message", "Something went wrong please try again sometime...!");
    			rd=request.getRequestDispatcher("Users.jsp");
    			rd.forward(request, response);
    		}
    	}
    	if(uri.contains("/logout.do"))
    	{
    		HttpSession session=request.getSession(false);
    		if(session==null||session.getAttribute("user")==null)
    		{
    			request.setAttribute("errormsg", "You are not logged in. <a href='Login.jsp'>Click</a>");
    			rd=request.getRequestDispatcher("Error.jsp");
    			rd.forward(request, response);
    		}
    		else
    		{
    			session.removeAttribute("user");
    			session.invalidate();
    			rd=request.getRequestDispatcher("Success.jsp");
        		request.setAttribute("message", "You have logged out successfully. <a href='Login.jsp'>click here to login again!</a>");
        		rd.forward(request, response);
    		}
    		
    	}
    	if(uri.contains("/EditAccount.do"))
    	{
    		HttpSession session=request.getSession(false);
    		if(session==null||session.getAttribute("user")==null)
    		{
    			request.setAttribute("errormsg", "You are logged out.login to edit your account <a href='login.jsp'>Click</a>");
    			rd=request.getRequestDispatcher("Error.jsp");
    			rd.forward(request, response);
    		}
    		else
    		{
    			RegBean bean=(RegBean) request.getAttribute("edit");
    			String email=(String) session.getAttribute("user");
    			RegBean regbean=model.getUserDetail(email);
    			if(bean.getEmail().equals(regbean.getEmail()))
    			{
    				String msg=model.UpdateRows(bean);
    				System.out.println(msg);
    				if(msg.equals(CONSTANTS.SUCCESS))
    				{
    					request.setAttribute("message", "Update successfull.click here to go back to the Menu <a href='Menu.jsp'>click</a>");
    					rd=request.getRequestDispatcher("Success.jsp");
    					rd.forward(request, response);
    				}
    				else
    				{
    					request.setAttribute("errormsg", msg);
    					rd=request.getRequestDispatcher("Error.jsp");
    					rd.forward(request, response);
    				}
    			}
    			else
    			{
    				request.setAttribute("errormsg", "You are logged out.login to edit your account <a href='login.jsp'>Click</a>");
        			rd=request.getRequestDispatcher("Error.jsp");
        			rd.forward(request, response);
    			}
    		}
    		
    	}
    	if(uri.contains("/ContactAdd.do"))
    	{
    		HttpSession session=request.getSession(false);
    		if(session==null||session.getAttribute("user")==null)
    		{
    			request.setAttribute("errormsg", "You are logged out.login to edit your account <a href='login.jsp'>Click</a>");
    			rd=request.getRequestDispatcher("Error.jsp");
    			rd.forward(request, response);
    		}
    		else
    		{
    			String email=(String) session.getAttribute("user");
    			AddContactBean bean=(AddContactBean) request.getAttribute("add");
    			System.out.println("Bean="+bean);
    			String msg=model.addContact(bean);
    			System.out.println(msg);
    		}
    	}
    	if(uri.contains("/AddContact.do"))
    	{
    		HttpSession session=request.getSession(false);
    		if(session==null||session.getAttribute("user")==null)
    		{
    			request.setAttribute("errormsg", "You are logged out.login to edit your account <a href='login.jsp'>Click</a>");
    			rd=request.getRequestDispatcher("Error.jsp");
    			rd.forward(request, response);
    		}
    		else
    		{
    			rd=request.getRequestDispatcher("AddContact.jsp");
        		rd.forward(request, response);
    		}
    		
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("In->doGet() method");
		process(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("In->doPost() method");
		process(request, response);
	}

}
