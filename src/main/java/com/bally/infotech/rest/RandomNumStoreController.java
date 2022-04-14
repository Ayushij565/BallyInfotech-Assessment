package com.bally.infotech.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bally.infotech.constant.RandomNumberConstants;
import com.bally.infotech.model.Message;
import com.bally.infotech.service.RandomNumStoreService;

@RestController
public class RandomNumStoreController {

	@Autowired
	public  RandomNumStoreService randomStoreService;

	@GetMapping(value = "/store/random-number/file")
	public ResponseEntity<Message> generateOneMillionRandomNumber() throws IOException{
        Random rand = new Random();
        List<Integer> randomeNumList = new ArrayList<>();
        for (int j = 1; j <= 1000000; ++j)
        	randomeNumList.add(j);
        Collections.shuffle(randomeNumList, rand);
		randomStoreService.saveRandomNumbersInFile(randomeNumList,RandomNumberConstants.FILE_STORE_RANDOM_NUM);
		Message response = new Message("Random Numbers Stored Successfully!!");
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}


	@GetMapping(value = "/get/sort-random-number/file")
	public  ResponseEntity<Message> sortOneMillionRandomNumber() throws IOException{
		randomStoreService.sortRandomNumbersInFile(RandomNumberConstants.FILE_STORE_RANDOM_NUM);
		Message response = new Message("Random Numbers sorted and stored Successfully!!");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
