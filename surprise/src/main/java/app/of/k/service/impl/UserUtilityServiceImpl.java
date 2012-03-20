package app.of.k.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.of.k.mapper.UserUtilityMapper;
import app.of.k.service.UserUtilityService;

@Service("UserUtilityService")
public class UserUtilityServiceImpl implements UserUtilityService {
	
	@Autowired
	private UserUtilityMapper userUtilityMapper;

	@Override
	public String getSequenceId() {
		userUtilityMapper.increseSequence();
		return userUtilityMapper.getSequenceId();
	}

	@Override
	public String getUserIdByProviderUserId(String pid) {
		return userUtilityMapper.getUserIdByProviderUserId(pid);
	}

	@Override
	public String getUserFacebookIdByUserId(String userId) {
		return userUtilityMapper.getUserFacebookIdByUserId(userId);
	}
	

}
