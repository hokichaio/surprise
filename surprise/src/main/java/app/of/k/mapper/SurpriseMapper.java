package app.of.k.mapper;

import java.util.List;

import app.of.k.dto.People;
import app.of.k.dto.Surprise;

public interface SurpriseMapper {
	
	public Surprise getSurpriseById(String id);
	
	public void insertSurprise(Surprise surprise);
	
	public void insertReceiver(People ppl);
	
	public void insertSender(People ppl);
	
	public void increseSequence();
	
	public String getSequenceId();
	
	public void updateSurprise(Surprise surprise);
	
	public void updateSender(People ppl);
	
	public List<Surprise> getSurpriseListById(String id);

}
