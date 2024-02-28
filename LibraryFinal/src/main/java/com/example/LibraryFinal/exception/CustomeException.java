package com.example.LibraryFinal.exception;

import lombok.Data;

@Data
public class CustomeException extends RuntimeException{

    final String resourceName;

    public CustomeException(String resourceName) {
        super(String.format("%s",resourceName));
        this.resourceName = resourceName;
    }

}
