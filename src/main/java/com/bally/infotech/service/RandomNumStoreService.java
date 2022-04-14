package com.bally.infotech.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bally.infotech.constant.RandomNumberConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RandomNumStoreService {

	public <T> boolean saveRandomNumbersInFile(List<Integer> num, String file) throws IOException {

		RandomAccessFile stream = null;
		try {
			stream = new RandomAccessFile(file, "rw");
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File not Found");
		}
		FileChannel channel = stream.getChannel();

		byte[] strBytes = String.valueOf(num).replaceAll("\\[|\\]", "").getBytes();
		ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
		buffer.put(strBytes);
		buffer.flip();
		try {
			channel.write(buffer);
			stream.close();
		} catch (IOException e) {
			throw new IOException("No data found in file");
		}
		try {
			channel.close();
		} catch (IOException e) {
			throw new IOException("No data found in file");
		}
		return true;
	}

	public boolean sortRandomNumbersInFile(String file) throws IOException {

		try {
			List<String> data = Arrays.asList(Files.readAllLines(Paths.get(file)).get(0).replace(" ", "").split(","));
			List<Integer> list = data.stream().map(Integer::valueOf).collect(Collectors.toList());
			Collections.sort(list);
			saveRandomNumbersInFile(list, RandomNumberConstants.FILE_SORT_SORT_RANDOM_NUM);
		} catch (IOException e) {
			throw new IOException("No data found in file");
		}
		return true;

	}
}
