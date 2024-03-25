package ru.virtya.vacationPayCalculate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class ErrorDto {

    private String message;

    private LocalDate timestamp;
}
