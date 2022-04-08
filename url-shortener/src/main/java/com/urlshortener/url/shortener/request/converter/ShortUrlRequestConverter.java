package com.urlshortener.url.shortener.request.converter;

import org.springframework.stereotype.Component;

import com.urlshortener.url.shortener.model.ShortUrl;
import com.urlshortener.url.shortener.request.ShortUrlRequest;

@Component
public class ShortUrlRequestConverter {

	public ShortUrl converToEntity(ShortUrlRequest shortUrlRequest) {
		return ShortUrl.builder()
			    .url(shortUrlRequest.getUrl())
			    .code(shortUrlRequest.getCode())
				.build();
	}
}
