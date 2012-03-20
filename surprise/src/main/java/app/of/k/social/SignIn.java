package app.of.k.social;

import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import app.of.k.util.UserCookieGenerator;

public final class SignIn implements SignInAdapter {

private final UserCookieGenerator userCookieGenerator = new UserCookieGenerator();
	
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
		SecurityContext.setCurrentUser(new User(userId));
		userCookieGenerator.addCookie(userId, request.getNativeResponse(HttpServletResponse.class));
		String rd = (String)request.getAttribute("rd", NativeWebRequest.SCOPE_SESSION);
		if(rd != null) {
			request.setAttribute("rd", null, NativeWebRequest.SCOPE_SESSION);
			return rd;
		}
		return "/";
	}

}