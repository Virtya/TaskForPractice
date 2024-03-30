package ru.virtya.vacationPayCalculate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {

    private String message;

    private LocalDate timestamp;
}
