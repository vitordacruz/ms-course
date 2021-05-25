package com.devsuperior.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.entities.Worker;

@Service
public class PaymentService {
	
	@Value("${hr-worker.host}")
	private String workerHost;
	
	@Autowired
	private RestTemplate restTemplate;

	public Payment getPayment(long workedId, int days)	{
		
		Map<String , String> uriVariables = new HashMap<>();
		
		uriVariables.put("id", "" + workedId);
		
		ResponseEntity<Worker> response = restTemplate.getForEntity(workerHost + "/workers/{id}", Worker.class, uriVariables);		
		
		Worker worker = response.getBody();
		
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
		
	}
}
