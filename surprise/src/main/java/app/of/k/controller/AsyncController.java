package app.of.k.controller;

import java.text.ParseException;
import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import app.of.k.service.FacebookService;
import app.of.k.social.SecurityContext;

@Controller
@RequestMapping(value = "/friends")
public class AsyncController {
	
	private static final Logger log = Logger.getLogger(AsyncController.class.getName());
	
	@Autowired
	private FacebookService facebookService;
	
	@RequestMapping(value = "/facebook")
	public ModelAndView friends(@RequestParam("to") String to, Model model) throws ParseException {

		ModelAndView modelAndView = new ModelAndView();
		
		if(SecurityContext.userSignedIn()) {
			List<FacebookProfile> friends = facebookService.getFriends(SecurityContext.getCurrentUser().getId());
			modelAndView.addObject("friends", friends);
			modelAndView.addObject("birthday", facebookService.getRecentBdayList(friends));
			modelAndView.addObject("to" , to);
			modelAndView.setViewName("async/friends");
			return modelAndView;
		}
		
		return null;
	}
	
}
