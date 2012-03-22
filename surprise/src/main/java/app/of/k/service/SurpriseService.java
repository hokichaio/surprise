package app.of.k.service;

import java.util.List;

import app.of.k.dto.Surprise;

public interface SurpriseService {

	public Surprise getSurpriseById(String id);
	
	public void insertSurprise(Surprise surprise);
	
	public void addPayment(Surprise surprise, String id);
	
	public void addMessage(Surprise surprise, String id);
	
	public List<Surprise> getSurpriseListById(String id);
	
	public void setSequenceId(Surprise surprise);
}
