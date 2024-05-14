package com.mindgate.main.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.FixedDepositDetails;
import com.mindgate.main.repository.FixedDepositDetailsRepositoryInterface;

@Service
public class FixedDepositDetailsService implements FixedDepositDetailsServiceInterface {
	@Autowired
	private FixedDepositDetailsRepositoryInterface fixedDepositDetailsRepository;

	@Override
	public ResponseEntity<?> addFixedDeposit(FixedDepositDetails fixedDepositDetails) {
		
        // Get the current date and time
        Date date1 = new Date();

        // Add 14 days to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.add(Calendar.DAY_OF_MONTH, 14);
        Date date2 = calendar.getTime();
        
        fixedDepositDetails.setFixedDepositCreationDate(date1);
        fixedDepositDetails.setFixedDepositMaturityDate(date2);

		return new ResponseEntity<FixedDepositDetails>(
				fixedDepositDetailsRepository.addFixedDeposit(fixedDepositDetails), HttpStatusCode.valueOf(200));
	}

	@Override
	public ResponseEntity<List<FixedDepositDetails>> getFd(int userId) {

		return new ResponseEntity<List<FixedDepositDetails>>(fixedDepositDetailsRepository.getFD(userId),
				HttpStatusCode.valueOf(200));
	}

}
