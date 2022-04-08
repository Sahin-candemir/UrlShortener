package com.urlshortener.url.shortener.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.urlshortener.url.shortener.dto.ShortUrlDto;
import com.urlshortener.url.shortener.model.ShortUrl;

@Component
public class ShortUrlDtoConverter {

	public ShortUrlDto convertToDto(ShortUrl shortUrl) {
		return ShortUrlDto.builder()
				.id(shortUrl.getId())
				.url(shortUrl.getUrl())
				.code(shortUrl.getCode())
				.build();
	}
	public List<ShortUrlDto> convertToDto(List<ShortUrl> shortUrl){
		return shortUrl.stream().map(x->convertToDto(x)).collect(Collectors.toList());
	}
}
