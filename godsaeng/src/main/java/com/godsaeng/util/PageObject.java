package com.godsaeng.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageObject {

	// 현재 페이지 (기본값 1)
	private int page = 1;
	
	// 페이지당 표시할 데이터 수 (기본값 10)
	private int perPageNum = 10;
	
	// 페이지 블럭에 표시할 페이지 개수 (기본값 10)
	private int perGroupPageNum = 10;
	
	// 전체 데이터 개수 (Servic에서 세팅)
	private long totalRow;
	
	// 검색 기능
    private String key;
    private String word;

    // 계산된 값들
    private int startRow;
    private int endRow;
    private int startPage;
    private int endPage;
    private long totalPage;

    /**
     * 전체 데이터 수(totalRow)가 세팅되면 나머지 값 자동 계산
     */
    public void setTotalRow(long totalRow) {
        this.totalRow = totalRow;

        // 시작/끝 행 번호 계산 (Oracle)
        this.startRow = (page - 1) * perPageNum + 1;
        this.endRow = startRow + perPageNum - 1;

        // 전체 페이지 수
        this.totalPage = (totalRow - 1) / perPageNum + 1;

        // 시작/끝 페이지 번호 (페이지 블럭 단위)
        this.startPage = ((page - 1) / perGroupPageNum) * perGroupPageNum + 1;
        this.endPage = startPage + perGroupPageNum - 1;
        if (endPage > totalPage) endPage = (int) totalPage;
    }
}
