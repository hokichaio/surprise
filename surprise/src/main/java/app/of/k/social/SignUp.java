package app.of.k.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

import app.of.k.service.UserUtilityService;

/**
 * {@link ConnectionSignUp}
 */
public class SignUp implements ConnectionSignUp {

	@Autowired
	private UserUtilityService userUtilityService;

//	private final AtomicLong userIdSequence = new AtomicLong();

//	public String execute(Connection<?> connection) {
//		return Long.toString(userIdSequence.incrementAndGet());
//	}
	public String execute(Connection<?> connection) {
		String pid = connection.getKey().getProviderUserId();
		String userId = userUtilityService.getUserIdByProviderUserId(pid);
		return (userId==null) ?  userUtilityService.getSequenceId() : userId;
	}
}