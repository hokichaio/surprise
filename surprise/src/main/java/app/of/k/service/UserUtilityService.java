package app.of.k.service;

public interface UserUtilityService {
	
	public String getSequenceId();
	
	public String getUserIdByProviderUserId(String pid);
	
	public String getUserFacebookIdByUserId(String userId);

}
