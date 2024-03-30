package ru.virtya.vacationPayCalculate.services;

import org.springframework.stereotype.Component;
import ru.virtya.vacationPayCalculate.dto.VacationPayDto;

@Component
public class VacationPayCalculateService {

    private static final double DAYS_IN_MONTH_AVERAGE = 29.3;

    public VacationPayDto getCalculateVacationPay(double avgSalaryPerYear, int vacationDays) {

        if (vacationDays <= 0) {
            throw new IllegalArgumentException("Your vacation days should be greater than 0");
        }

        if (avgSalaryPerYear < 0) {
            throw new IllegalArgumentException("Your average salary per year should be greater or equal 0");
        }

        double avgSalaryPerDay = avgSalaryPerYear / DAYS_IN_MONTH_AVERAGE;

        double totalPay = avgSalaryPerDay * vacationDays;

        totalPay = Math.round(totalPay * 100);
        totalPay /= 100;

        return new VacationPayDto("Сумма отпускных в рублях", totalPay);
    }
}
