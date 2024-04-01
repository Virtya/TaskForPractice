package ru.virtya.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class VacationPayDto {

    private String message;

    private double vacationPay;
}
