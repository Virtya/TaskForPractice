package ru.virtya.vacationPayCalculate.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.virtya.vacationPayCalculate.services.VacationPayCalculateInDaysService;
import ru.virtya.vacationPayCalculate.services.VacationPayCalculateService;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class VacationPayCalculateController {

    private final VacationPayCalculateService vacationPayCalculateService;
    private final VacationPayCalculateInDaysService vacationPayCalculateInDaysService;

    /**
     * Из-за получения даты конца отпуска, количество дней отпуска "затирается",
     * теперь важны только точные даты, мало ли человек ошибся при расчете дней отпуска.
     * <p>
     * Если получили только startDate или только endDate, обращаемся к количеству дней,
     * т. к. по условию кол-во дней обязательно.
     *
     * @param avgSalaryPerYear - средняя зарплата за год
     * @param vacationDays - количество отпускных дней
     * @param startDate - с какой даты начинать отсчет отпуска
     * @param endDate - какой датой заканчивать отсчет
     * @return DTO с полями message и vacationPay
     */
    @GetMapping("/calculate")
    public Object getVacationPay(@RequestParam("averageSalary") double avgSalaryPerYear,
                                 @RequestParam("vacationDays") int vacationDays,
                                 @RequestParam("startDate") Optional<String> startDate,
                                 @RequestParam("endDate") Optional<String> endDate
    ) {
        log.info("Handle request to calculate vacation pay");
        log.debug("Request parameters: {}, {}", avgSalaryPerYear, vacationDays);
        log.debug("Optional parameters: {}, {}", startDate, endDate);

        if (startDate.isPresent() && endDate.isPresent()) {
            vacationDays = vacationPayCalculateInDaysService.getPaidDays(startDate.get(), endDate.get());
        }

        return vacationPayCalculateService.getCalculateVacationPay(avgSalaryPerYear, vacationDays);

    }
}
