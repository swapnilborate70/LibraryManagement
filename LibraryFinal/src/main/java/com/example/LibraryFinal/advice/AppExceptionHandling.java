package com.example.LibraryFinal.advice;

import com.example.LibraryFinal.exception.CustomeException;
import com.example.LibraryFinal.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandling {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(ResourceNotFoundException exce)
    {
        return exce.getMessage();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exce)
    {
        Map<String, String> errors = new HashMap<>();

        exce.getBindingResult().getFieldErrors().forEach(err -> {
            errors.put(err.getField(),err.getDefaultMessage());
        });

        return errors;
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoResourceFoundException (NoResourceFoundException exce)
    {
        return exce.getMessage()+". "+"Provide correct URL.";
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exce)
    {
        return "Provided path variable type is incorrect !! Please provide correct path variable.";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException exce)
    {
        return exce.getMessage();
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNullPointerException(NullPointerException exce)
    {
        return exce.getMessage();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException exce)
    {
        return "Http Message Not Readable !!";
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleHttpMessageNotReadableException(HttpRequestMethodNotSupportedException exce)
    {
        return "Http Request Method Not Supported !!";
    }

    @ExceptionHandler(CustomeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleCustomeException(CustomeException exce)
    {
        return exce.getMessage();
    }


}
