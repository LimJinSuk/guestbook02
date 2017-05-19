package com.jx372.guestbook.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.guestbook.dao.GuestbookDao;
import com.jx372.guestbook.vo.GuestbookVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<GuestbookVo> list = new GuestbookDao().getList();
		request.setAttribute("list", list);
		
		WebUtils.forward("/WEB-INF/views/index.jsp", request, response);
	

	}

}
