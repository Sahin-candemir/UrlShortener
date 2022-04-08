package com.urlshortener.url.shortener.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.urlshortener.url.shortener.exception.CodeAlreadyExists;
import com.urlshortener.url.shortener.exception.ShortUrlNotFoundException;
import com.urlshortener.url.shortener.model.ShortUrl;
import com.urlshortener.url.shortener.repository.ShortUrlRepository;
import com.urlshortener.url.shortener.util.RandomStringGenerator;

@Service 
public class ShortUrlService {

	private final ShortUrlRepository shortUrlRepository;
	private final RandomStringGenerator randomStringGenerator;
	public ShortUrlService(ShortUrlRepository shortUrlRepository, RandomStringGenerator randomStringGenerator) {
		this.shortUrlRepository = shortUrlRepository;
		this.randomStringGenerator = randomStringGenerator;
	}

	public List<ShortUrl> getAllShortUrl() {
		return shortUrlRepository.findAll();
	}

	public ShortUrl getUrlByCode(String code) {
		return shortUrlRepository.findAllByCode(code).orElseThrow(()-> new ShortUrlNotFoundException("url not found"));
	}

	public ShortUrl create(ShortUrl shortUrl) {
	
		if(shortUrl.getCode() == null || shortUrl.getCode().isEmpty()) {
			shortUrl.setCode(generateCode());
		}                                                          //herhangi bir sey dönmüş ise(data var ise)
		else if(shortUrlRepository.findAllByCode(shortUrl.getCode()).isPresent()) {
			throw new CodeAlreadyExists("code already exists");
		}
		shortUrl.setCode(shortUrl.getCode().toUpperCase());
		return shortUrlRepository.save(shortUrl);
	}

	public String generateCode() {
		String code;
		do {
			code = randomStringGenerator.generateRandomString();
		}
		while(shortUrlRepository.findAllByCode(code).isPresent());
		return code;
	}

}
