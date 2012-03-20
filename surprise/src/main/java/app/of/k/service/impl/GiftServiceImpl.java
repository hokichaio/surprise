package app.of.k.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.of.k.dto.Gift;
import app.of.k.mapper.GiftServiceMapper;
import app.of.k.service.GiftService;

@Service("GiftService")
public class GiftServiceImpl implements GiftService {

	@Autowired
	private GiftServiceMapper giftServiceMapper;
	
	@Override
	public List<Gift> getGifts(Gift search) {
		return giftServiceMapper.getGifts(search);
	}

	@Override
	public Gift getGift(String id) {
		return giftServiceMapper.getGift(id);
	}
	
}
