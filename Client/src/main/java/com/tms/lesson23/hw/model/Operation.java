package com.tms.lesson23.hw.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Operation {

    private final double num1;
    private final double num2;
    private final String operation;
    private double result;

}
