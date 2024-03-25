package ru.virtya.vacationPayCalculate.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class VacationPayDto {

    private String message;

    private double vacationPay;
}
