package com.teran.parking.finecost.test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;

import com.telran.parking.finecost.dto.FineDto;
import com.telran.parking.finecost.dto.OwnerDto;
import com.telran.parking.finecost.service.SetFineCostServiceImpl;

@Import(TestChannelBinderConfiguration.class)
public class SetFineCostServiceTest {
	
	@Autowired
	private SetFineCostServiceImpl setFineCostService;

	@Autowired
	private InputDestination inputDestination;

	@Autowired
	private OutputDestination outputDestination;
	
	private static OwnerDto paidParkingOwner;
	private static OwnerDto freeParkingOwner;
	private static FineDto fineForPaidParkingOwner;

	@BeforeAll
	static public void setup() throws Exception {
		paidParkingOwner = new OwnerDto(2, "paidParking", "Address 2", 987654321, "test2@example.com");
		freeParkingOwner = new OwnerDto(1, "freeParking", "Address 1", 123456789, "test1@example.com");
		fineForPaidParkingOwner = new FineDto(2, "paidParking", "Address 2", 987654321, "test2@example.com", 250);
	}

	@Test
	public void testByteLengthInOutputDestination() {

		inputDestination.send(new GenericMessage<>(paidParkingOwner), "ownerTopicListener-in-0");
		
		try {
			outputDestination.wait(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] messageBytes = outputDestination.receive(100, "ownerTopicListener-out-0").getPayload();
//		byte[] messageBytes = outputDestination.receive(100).getPayload();
		assertNotEquals(messageBytes.length, 0);
	}
}
