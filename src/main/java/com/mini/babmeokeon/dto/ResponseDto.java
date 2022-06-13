package com.mini.babmeokeon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mini.babmeokeon.model.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Setter@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T>{
    private boolean response;
    private String message;
    private List<T> storeList;

    private int likeCount;

    public ResponseDto(boolean response, String message, int likeCount) {
        this.response = response;
        this.message = message;
        this.likeCount = likeCount;
    }

    public ResponseDto(boolean response) {
        this.response = response;
    }

    public ResponseDto(boolean response, String message) {
        this.response = response;
        this.message = message;
    }

    public ResponseDto(boolean response, String message, List<T> storeList) {
        this.response = response;
        this.message = message;
        this.storeList = storeList;
    }


}