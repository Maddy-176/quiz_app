package com.example.quiz_mircroservice.quiz_service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Responses {
    Integer id;
    String response;
}