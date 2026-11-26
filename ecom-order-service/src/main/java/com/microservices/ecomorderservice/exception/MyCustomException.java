package com.microservices.ecomorderservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;

public class MyCustomException extends RuntimeException{
    private final HttpStatusCode httpStatusCode;
    private final HttpHeaders httpHeaders;

    public MyCustomException(HttpStatusCode httpStatusCode, HttpHeaders httpHeaders) {
        this.httpStatusCode = httpStatusCode;
        this.httpHeaders = httpHeaders;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }
}
