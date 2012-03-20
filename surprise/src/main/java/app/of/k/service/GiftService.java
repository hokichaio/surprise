package app.of.k.service;

import java.util.List;
import app.of.k.dto.Gift;

public interface GiftService {

	public List<Gift> getGifts(Gift search);
	
	public Gift getGift(String id);
	
}
