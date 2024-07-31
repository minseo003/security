package hello.securitylogin.dto;

import lombok.Builder;

import java.util.List;

public class ResponseDTO<T> {

    private String error;
    private List<T> data;

    @Builder
    public ResponseDTO(String error, List<T> data) {
        this.error = error;
        this.data = data;
    }


}
