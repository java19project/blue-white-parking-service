package com.telran.parking.finecost.dto;

public record OwnerDto (
		int ownerID, 
		String ownerStatus, 
		String ownerAddress, 
		int numbeTelefon, 
		String mail) {}
