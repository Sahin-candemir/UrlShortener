package com.urlshortener.url.shortener.controller;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortener.url.shortener.dto.ShortUrlDto;
import com.urlshortener.url.shortener.dto.converter.ShortUrlDtoConverter;
import com.urlshortener.url.shortener.model.ShortUrl;
import com.urlshortener.url.shortener.request.ShortUrlRequest;
import com.urlshortener.url.shortener.request.converter.ShortUrlRequestConverter;
import com.urlshortener.url.shortener.service.ShortUrlService;

@RestController
@RequestMapping
public class UrlController {

	
	private final ShortUrlDtoConverter shortUrlDtoConverter;
	
	private final ShortUrlRequestConverter shortUrlRequestConverter;

	private final ShortUrlService shortUrlService;
	
	public UrlController(ShortUrlDtoConverter shortUrlDtoConverter, ShortUrlRequestConverter shortUrlRequestConverter, ShortUrlService shortUrlService) {
		this.shortUrlDtoConverter = shortUrlDtoConverter;
		this.shortUrlRequestConverter = shortUrlRequestConverter;
		this.shortUrlService = shortUrlService;
	}

	@GetMapping
	public ResponseEntity<List<ShortUrlDto>> getAllUrls(){
		return new ResponseEntity<List<ShortUrlDto>>(
				shortUrlDtoConverter.convertToDto(shortUrlService.getAllShortUrl()),HttpStatus.OK);
	}
	
	@GetMapping("/show/{code}")
	public ResponseEntity<ShortUrlDto> getUrlByCode(@Valid @NotEmpty@PathVariable String code){
		return new ResponseEntity<ShortUrlDto>(
				shortUrlDtoConverter.convertToDto(shortUrlService.getUrlByCode(code)),HttpStatus.OK);
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<ShortUrlDto> redirect(@Valid @NotEmpty@PathVariable String code) throws URISyntaxException{
		
		ShortUrl shortUrl = shortUrlService.getUrlByCode(code);
		URI uri = new URI(shortUrl.getUrl());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(uri);
		return new ResponseEntity<ShortUrlDto>(
				httpHeaders,HttpStatus.SEE_OTHER);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody ShortUrlRequest shortUrlRequest){
		ShortUrl shortUrl = shortUrlRequestConverter.converToEntity(shortUrlRequest);
		return new ResponseEntity<ShortUrlDto>(shortUrlDtoConverter.convertToDto(shortUrlService.create(shortUrl)) , HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
