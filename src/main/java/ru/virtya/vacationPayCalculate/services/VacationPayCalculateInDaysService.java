package ru.virtya.vacationPayCalculate.services;

import org.springframework.stereotype.Service;

@Service
public interface VacationPayCalculateInDaysService {
    int getPaidDays(String vacationStartDate, String vacationEndDate);
}
