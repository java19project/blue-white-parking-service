package com.telran.parking.finecost.service;

import org.springframework.stereotype.Service;

import com.telran.parking.finecost.dto.FineDto;
import com.telran.parking.finecost.dto.OwnerDto;

@Service
public class SetFineCostServiceImpl implements ISetFineCostService {

	@Override
	public FineDto setFineAmount(OwnerDto owner) {
		
		if (owner.ownerStatus().equals("freeParking") )
			return null;
		
		if (owner.ownerStatus().equals("paidParking") ) {
			return new FineDto(
					owner.ownerID(),
					owner.ownerStatus(), 
					owner.ownerAddress(), 
					owner.numbeTelefon(),
					owner.mail(), 
					250);
		}
			
		return null;
	}

}
