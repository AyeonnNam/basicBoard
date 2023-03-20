package com.ayeon.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criteria {

	private int pageNum;
	private int amount;
	private int skip;
	
	public Criteria() {
		this(1,10);
	}

	public Criteria(int pageNum, int amount) {
		// TODO Auto-generated constructor stub
		this.pageNum = pageNum;
		this.amount= amount;
	}
	
	
	public int getSkip() {
		return this.skip = (pageNum -1) * amount;
	}
    
	
	

	
	
	
}
