package com.example.LibraryFinal.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException{

    final String resourceName;

    public ResourceNotFoundException(String resourceName) {
       super(String.format("%s",resourceName));
        this.resourceName = resourceName;
    }
}
