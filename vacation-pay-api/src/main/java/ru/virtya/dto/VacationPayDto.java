package ru.virtya.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class VacationPayDto {

    private String message;

    private double vacationPay;
}
