package com.jx372.guestbook.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.guestbook.dao.GuestbookDao;
import com.jx372.guestbook.vo.GuestbookVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class AddAction implements Action {
	
	
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String message = request.getParameter("message");
		
		GuestbookVo vo = new GuestbookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		
		new GuestbookDao().insert(vo);
		
		//리다이렉트 응답 : 브라우저에게 보내는것 
		WebUtils.redirect( "/guestbook02/gb",request,response );
	}

}
