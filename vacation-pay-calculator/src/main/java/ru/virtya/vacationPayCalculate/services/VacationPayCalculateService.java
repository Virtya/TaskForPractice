package ru.virtya.vacationPayCalculate.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.virtya.dto.VacationPayDto;

@Slf4j
@Component
public class VacationPayCalculateService {

    private static final double DAYS_IN_MONTH_AVERAGE = 29.3;

    public VacationPayDto getCalculateVacationPay(double avgSalaryPerYear, int vacationDays) {

        if (vacationDays <= 0) {
            log.error("Vacation days <= 0");
            throw new IllegalArgumentException("Your vacation days should be greater than 0");
        }

        if (avgSalaryPerYear < 0) {
            log.error("Salary < 0");
            throw new IllegalArgumentException("Your average salary per year should be greater or equal 0");
        }

        double avgSalaryPerDay = avgSalaryPerYear / DAYS_IN_MONTH_AVERAGE;
        log.info("Average earnings per day - {} rubles", avgSalaryPerDay);

        double totalPay = avgSalaryPerDay * vacationDays;
        log.info("The amount of vacation pay - {} rubles", totalPay);

        totalPay = Math.round(totalPay * 100);
        totalPay /= 100;

        return new VacationPayDto("Amount of vacation pay in rubles", totalPay);
    }
}
