package com.urlshortener.url.shortener.request;


import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShortUrlRequest {

	@NotNull
	@NotEmpty
	private String url;
	
	private String code;
}
