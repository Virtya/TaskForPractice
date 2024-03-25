package ru.virtya.vacationPayCalculate.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.virtya.vacationPayCalculate.dto.VacationPayDto;
import ru.virtya.vacationPayCalculate.services.VacationPayCalculateService;

@Component
public class VacationPayCalculateServiceImpl implements VacationPayCalculateService {
    private static final Logger log = LoggerFactory.getLogger(VacationPayCalculateServiceImpl.class);

    private static final double DAYS_IN_MONTH_AVERAGE = 29.3;

    @Override
    public VacationPayDto getCalculateVacationPay(double avgSalaryPerYear, int vacationDays) {

        if (vacationDays <= 0) {
            log.error("Дни отпуска <= 0");
            throw new IllegalArgumentException("Your vacation days should be greater than 0");
        }

        if (avgSalaryPerYear < 0) {
            log.error("Зарплата < 0");
            throw new IllegalArgumentException("Your average salary per year should be greater or equal 0");
        }

        double avgSalaryPerDay = avgSalaryPerYear / DAYS_IN_MONTH_AVERAGE;
        log.info("Средний заработок в день – {} рублей", avgSalaryPerDay);

        double totalPay = avgSalaryPerDay * vacationDays;
        log.info("Сумма отпускных – {} рублей", totalPay);

        totalPay = Math.round(totalPay * 100);
        totalPay /= 100;

        return new VacationPayDto("Сумма отпускных в рублях", totalPay);
    }
}
