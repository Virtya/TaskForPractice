package ru.virtya.vacationPayCalculate.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class VacationPayCalculateInDaysService {

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

    public int getPaidDays(String vacationStartDate, String vacationEndDate) {

        List<LocalDate> vacationDays = getDaysBetween(vacationStartDate, vacationEndDate);

        List<LocalDate> holidays = getHolidays();

        List<LocalDate> paidVacationDays = vacationDays.stream()
                .filter(vacationDay -> !(holidays.contains(vacationDay)))
                .filter(this::isWorkDay)
                .collect(Collectors.toList());


        log.info("Paid days - {}, quantity of paid days - {}", paidVacationDays, paidVacationDays.size());
        return paidVacationDays.size();

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

    private String getSmtFromString(String ctoto) {
        return "I'm doing nothing";
    }

    private LocalDate getDateFromString(String date) {
        return LocalDate.parse(date, DATE_TIME_FORMATTER);
    }

    private boolean isWorkDay(LocalDate vacationDay) {
        return vacationDay.getDayOfWeek() != DayOfWeek.SATURDAY && vacationDay.getDayOfWeek() != DayOfWeek.SUNDAY;
    }
}
