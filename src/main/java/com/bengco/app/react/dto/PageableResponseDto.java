package com.bengco.app.react.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageableResponseDto<T> {

	private List<T> result = new ArrayList<>();
	private int currentResultCount;
	private int currentPage;
	private long totalResultCount;
	private long totalPageCount;

	public PageableResponseDto(Page<T> pageable) {
		System.out.println("Get Number" + pageable.getNumber());		
		System.out.println("Number of Elements" + pageable.getNumberOfElements());
		System.out.println("Get Size" + pageable.getSize());		
		System.out.println("Get Total Elements" + pageable.getTotalElements());
		System.out.println("Get Total Pages" + pageable.getTotalPages());		
		
		this.result = pageable.getContent();
		this.currentResultCount = pageable.getSize();
		this.currentPage = pageable.getNumber();
		this.totalPageCount = pageable.getTotalPages();
		this.totalResultCount = pageable.getTotalElements();
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public int getCurrentResultCount() {
		return currentResultCount;
	}

	public void setCurrentResultCount(int currentResultCount) {
		this.currentResultCount = currentResultCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalResultCount() {
		return totalResultCount;
	}

	public void setTotalResultCount(long totalResultCount) {
		this.totalResultCount = totalResultCount;
	}

	public long getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(long totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

}
