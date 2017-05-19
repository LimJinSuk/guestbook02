package com.jx372.guestbook.action;

import com.jx372.web.action.Action;
import com.jx372.web.action.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("delete".equals(actionName))
		{
			action = new DeleteAction();
		}
		else if("add".equals(actionName))
		{
			action = new AddAction();
		}
		else if("deleteform".equals(actionName))
		{
			action = new DeleteFormAction();
		}
		else{
			action = new ListAction();
		}
		
		return action;
	}

}

