package app.of.k.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.of.k.dto.People;
import app.of.k.dto.Surprise;
import app.of.k.mapper.SurpriseMapper;
import app.of.k.service.SurpriseService;

@Service("SurpriseService")
public class SurpriseServiceImpl implements SurpriseService {

	@Autowired
	private SurpriseMapper surpriseMapper;
	
	@Override
	public Surprise getSurpriseById(String id) {
		return surpriseMapper.getSurpriseById(id);
	}

	@Override
	public void insertSurprise(Surprise surprise) {
		
		surpriseMapper.insertSurprise(surprise);
		for(People ppl : surprise.getReceiverList()) {
			ppl.setSurpriseId(surprise.getId());
			surpriseMapper.insertReceiver(ppl);
		}
		for(People ppl : surprise.getSenderList()) {
			ppl.setSurpriseId(surprise.getId());
			surpriseMapper.insertSender(ppl);
		}
		
	}
	
	@Override
	public void addPayment(Surprise surprise, String id) {
		int bill = surprise.getTempPayment();
		for(People ppl : surprise.getSenderList()) {
			if(ppl.getId().equals(id)){
				ppl.setSurpriseId(surprise.getId());
				ppl.setPayment(bill);
				surpriseMapper.addPayment(ppl);
			}
		}
		
	}
	
	@Override
	public void addMessage(Surprise surprise, String id) {
		String message = surprise.getTempMessage();
		for(People ppl : surprise.getSenderList()) {
			if(ppl.getId().equals(id)){
				ppl.setSurpriseId(surprise.getId());
				ppl.setMessage(message);
				surpriseMapper.addMessage(ppl);
			}
		}
		
	}

	@Override
	public List<Surprise> getSurpriseListById(String id) {
		return surpriseMapper.getSurpriseListById(id);
	}

	@Override
	public void setSequenceId(Surprise surprise) {
		surpriseMapper.increseSequence();
		String id = surpriseMapper.getSequenceId();
		surprise.setId(id);
	}

}
