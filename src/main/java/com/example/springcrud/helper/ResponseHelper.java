package com.example.springcrud.helper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {
    public static ResponseEntity<Object> generateSuccessResponse(String message, HttpStatus httpStatus, Object responsObject) {
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("httpStatus", httpStatus);
        response.put("data", responsObject);

        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> generateErrorResponse(String message, HttpStatusCode httpStatusCode) {
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("httpStatus", httpStatusCode);

        return new ResponseEntity<>(response, httpStatusCode);
    }
}
