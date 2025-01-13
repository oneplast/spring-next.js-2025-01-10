package com.ll.nextjs20250110.global.rsData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;

@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@Getter
public class RsData<T> {
    @NonNull
    private String resultCode;
    @NonNull
    private String msg;
    private T data;

    public RsData(String resultCode, String msg) {
        this(resultCode, msg, null);
    }

    @JsonIgnore
    public int getStatusCode() {
        return Integer.parseInt(resultCode.split("-")[0]);
    }
}
