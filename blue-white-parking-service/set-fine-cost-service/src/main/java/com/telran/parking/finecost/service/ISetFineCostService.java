package com.telran.parking.finecost.service;

import com.telran.parking.finecost.dto.FineDto;
import com.telran.parking.finecost.dto.OwnerDto;

public interface ISetFineCostService {
	FineDto setFineAmount (OwnerDto owner);

}