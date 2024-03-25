package ru.virtya.vacationPayCalculate.services;

import org.springframework.stereotype.Service;
import ru.virtya.vacationPayCalculate.dto.VacationPayDto;

@Service
public interface VacationPayCalculateService {
    VacationPayDto getCalculateVacationPay(double avgSalaryPerYear, int vacationDays);
}
