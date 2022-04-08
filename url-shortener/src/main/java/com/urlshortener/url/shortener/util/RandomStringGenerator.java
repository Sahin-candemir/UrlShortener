package com.urlshortener.url.shortener.util;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RandomStringGenerator {

	@Value("${codeLenght}")
	private int codeLenght;
	
	public String generateRandomString() {
	
		SecureRandom random = new SecureRandom();
		String generated = "";
		
		var letters = "abcdefghijklmnprstuvyzqw123456789".toUpperCase().chars().mapToObj(x->(char)x).collect(Collectors.toList());
		Collections.shuffle(letters);
		
		for (int i = 0; i < codeLenght; i++) {
			generated += letters.get(random.nextInt(letters.size()));
		}
		return generated;
	}
}
