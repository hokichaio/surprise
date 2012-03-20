package app.of.k.mapper;

import java.util.List;

import app.of.k.dto.Gift;

public interface GiftServiceMapper {
	
	public List<Gift> getGifts(Gift search);
	
	public Gift getGift(String id);

}
