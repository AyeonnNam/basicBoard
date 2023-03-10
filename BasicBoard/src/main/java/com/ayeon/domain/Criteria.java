package com.ayeon.domain;

import lombok.Data;

@Data
public class Criteria {

	private int pageNum;
	private int amount;
	
	public Criteria() {
		this(1,10);
	}

	public Criteria(int pageNum, int amount) {
		// TODO Auto-generated constructor stub
		this.pageNum = pageNum;
		this.amount= amount;
	}
	
	
	
	
	
}
