package ru.virtya.vacationPayCalculate.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.virtya.vacationPayCalculate.services.VacationPayCalculateInDaysService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class VacationPayCalculateInDaysServiceImpl implements VacationPayCalculateInDaysService {

    private static final Logger log = LoggerFactory.getLogger(VacationPayCalculateInDaysServiceImpl.class);

    private final static int CURRENT_YEAR = LocalDate.now().getYear();
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static List<LocalDate> getHolidays() {
        return Stream.of(
                LocalDate.of(CURRENT_YEAR, 1, 1),
                LocalDate.of(CURRENT_YEAR, 1, 2),
                LocalDate.of(CURRENT_YEAR, 1, 3),
                LocalDate.of(CURRENT_YEAR, 1, 4),
                LocalDate.of(CURRENT_YEAR, 1, 5),
                LocalDate.of(CURRENT_YEAR, 1, 6),
                LocalDate.of(CURRENT_YEAR, 1, 7),
                LocalDate.of(CURRENT_YEAR, 1, 8),
                LocalDate.of(CURRENT_YEAR, 2, 23),
                LocalDate.of(CURRENT_YEAR, 3, 8),
                LocalDate.of(CURRENT_YEAR, 5, 1),
                LocalDate.of(CURRENT_YEAR, 5, 8),
                LocalDate.of(CURRENT_YEAR, 5, 9),
                LocalDate.of(CURRENT_YEAR, 6, 12),
                LocalDate.of(CURRENT_YEAR, 11, 4),
                LocalDate.of(CURRENT_YEAR, 12, 31)
        ).collect(Collectors.toList());
    }

    @Override
    public int getPaidDays(String vacationStartDate, String vacationEndDate) {

        try {
            List<LocalDate> vacationDays = getDaysBetween(vacationStartDate, vacationEndDate);

            List<LocalDate> holidays = getHolidays();

            List<LocalDate> paidVacationDays = vacationDays.stream()
                    .filter(vacationDay -> !(holidays.contains(vacationDay)))
                    .filter(this::isWorkDay)
                    .collect(Collectors.toList());


            log.info("Количество оплачиваемых дней – {}", paidVacationDays.size());
            return paidVacationDays.size();

        } catch (DateTimeParseException e) {
            
            log.error(e.getMessage());
            throw e;
        }
    }

    private List<LocalDate> getDaysBetween(String vacationStartDateString, String vacationEndDateString) {

        LocalDate vacationStartDate = getDateFromString(vacationStartDateString);
        LocalDate vacationEndDate = getDateFromString(vacationEndDateString);

        List<LocalDate> vacationDays = new ArrayList<>();

        while (!vacationStartDate.isAfter(vacationEndDate)) {
            vacationDays.add(vacationStartDate);
            vacationStartDate = vacationStartDate.plusDays(1);
        }

        return vacationDays;
    }

    private LocalDate getDateFromString(String date) {
        return LocalDate.parse(date, DATE_TIME_FORMATTER);
    }

    private boolean isWorkDay(LocalDate vacationDay) {
        return vacationDay.getDayOfWeek() != DayOfWeek.SATURDAY && vacationDay.getDayOfWeek() != DayOfWeek.SUNDAY;
    }
}
