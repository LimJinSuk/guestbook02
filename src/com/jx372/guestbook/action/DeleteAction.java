package com.jx372.guestbook.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.guestbook.dao.GuestbookDao;
import com.jx372.guestbook.vo.GuestbookVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String password = WebUtils.checkParameter( request.getParameter( "password" ), "" );
		Long no = WebUtils.checkParameter( request.getParameter( "no" ), 0L );
		
		GuestbookVo vo = new GuestbookVo();
		vo.setNo( no );
		vo.setPassword(password);
		
		new GuestbookDao().delete(vo);
		
		WebUtils.redirect( "/guestbook02/gb",request,response );

	}

}
