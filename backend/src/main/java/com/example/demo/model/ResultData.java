package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class ResultData {
	
	private String title;
	private String keywords;
	private String location;
	private String highlight;
	private String writer;
	private String genres;

}
