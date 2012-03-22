package app.of.k.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import app.of.k.dto.Gift;
import app.of.k.dto.People;
import app.of.k.dto.Surprise;
import app.of.k.service.FacebookService;
import app.of.k.service.GiftService;
import app.of.k.service.SurpriseService;
import app.of.k.service.UserUtilityService;
import app.of.k.social.SecurityContext;

@Controller
public class HomeController {
	
	private static final Logger log = Logger.getLogger(HomeController.class.getName());
	
	@Autowired
	private GiftService giftService;
	
	@Autowired
	private UserUtilityService userUtilityService;
	
	@Autowired
	private FacebookService facebookService;
	
	@Autowired
	private SurpriseService surpriseService;
	
	@RequestMapping(value = "/")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("main/top");
		return modelAndView;
	}
	
	@RequestMapping(value = "/mypage")
	public ModelAndView mypage() throws ParseException {
		if(!SecurityContext.userSignedIn()) {
			return null;
		}
		
		String userId = userUtilityService.getUserFacebookIdByUserId(SecurityContext.getCurrentUser().getId());
		List<Surprise> surpriseList = surpriseService.getSurpriseListById(userId);
		
		List<Surprise> myList = new ArrayList<Surprise>();
		List<Surprise> friendList = new ArrayList<Surprise>();
		for(Surprise s : surpriseList) {
			boolean isMyList = false;
			for(People ppl : s.getSenderList()) {
				if(ppl.getId().equals(userId)) {
					isMyList = ppl.isPromoter();
					break;
				}
			}
			if(isMyList) {
				myList.add(s);
			} else {
				friendList.add(s);
			}
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("myList", myList);
		modelAndView.addObject("friendList", friendList);
		modelAndView.setViewName("main/mypage");
		return modelAndView;
	}
	
	@RequestMapping(value = "/gift")
	public ModelAndView gift(Gift searchForm) throws ParseException {

		ModelAndView modelAndView = new ModelAndView();
		if(!searchForm.isEmpty()) {
			modelAndView.addObject("gifts", giftService.getGifts(searchForm));
		}
		modelAndView.addObject("searchForm", searchForm);
		modelAndView.setViewName("main/gift");
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/gift_detail")
	public ModelAndView giftDetail(@RequestParam("id") String id, Gift searchForm) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("gift", giftService.getGift(id));
		modelAndView.addObject("searchForm", searchForm);
		if(SecurityContext.userSignedIn()) {
			modelAndView.addObject("sendForm", facebookService.buildSendForm());
		} else {
			modelAndView.addObject("sendForm", new Surprise());
		}
		modelAndView.setViewName("main/gift_detail");
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/sendp1")
	public ModelAndView sendP1(Surprise sendForm) {
		sendForm.refreshList();
		surpriseService.setSequenceId(sendForm);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("sendForm", sendForm);
		modelAndView.setViewName("main/sendp1");
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/sendp2", params = "cancel")
	public ModelAndView sendP2Cancel(Surprise sendForm) {
		return giftDetail(sendForm.getGift().getId(), new Gift());
	}
	
	@RequestMapping(value = "/sendp2")
	public ModelAndView sendP2(Surprise sendForm) {
		sendForm.refreshList();
		surpriseService.insertSurprise(sendForm);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userFbId", userUtilityService.getUserFacebookIdByUserId(SecurityContext.getCurrentUser().getId()));
		modelAndView.addObject("sendForm", sendForm);
		modelAndView.setViewName("main/sendp2");
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/sendp3", params = "input")
	public ModelAndView sendP3(Surprise sendForm, HttpServletRequest request) {
		sendForm.refreshList();
		String currentUserId = userUtilityService.getUserFacebookIdByUserId(SecurityContext.getCurrentUser().getId());
		if(sendForm.getTempPayment() != null){
			surpriseService.addPayment(sendForm, currentUserId);
		}
		if(sendForm.getTempMessage() != null && !sendForm.getTempMessage().isEmpty()) {
			surpriseService.addMessage(sendForm, currentUserId);
		}
		return gotoSurprise(sendForm.getId(), request);
	}
	
	@RequestMapping(value = "/goto")
	public ModelAndView gotoSurprise(@RequestParam("id") String id, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if(!SecurityContext.userSignedIn()) {
			return loginRedirect("/goto?id=" + id, request);
		}
		String currentUserId = userUtilityService.getUserFacebookIdByUserId(SecurityContext.getCurrentUser().getId());
		Surprise sendForm = surpriseService.getSurpriseById(id);
		if(sendForm == null) {
			return home();
		} else if(!sendForm.isUserInList(currentUserId)) {
			return home();
		}
		modelAndView.addObject("userFbId", currentUserId);
		modelAndView.addObject("sendForm", sendForm);
		modelAndView.setViewName("main/sendp2");
		return modelAndView;
	}
	
	@RequestMapping(value = "/loginRd")
	public ModelAndView loginRedirect(@RequestParam("rd") String rd, HttpServletRequest request) {
		request.getSession().setAttribute("rd", rd);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rd", rd);
		modelAndView.setViewName("main/loginRd");
		return modelAndView;
		
	}
}
