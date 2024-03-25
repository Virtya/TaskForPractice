package ru.virtya.vacationPayCalculate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.virtya.vacationPayCalculate.dto.ErrorDto;
import ru.virtya.vacationPayCalculate.services.VacationPayCalculateInDaysService;
import ru.virtya.vacationPayCalculate.services.VacationPayCalculateService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@RestController
public class VacationPayCalculateController {

    private final VacationPayCalculateService vacationPayCalculateService;
    private final VacationPayCalculateInDaysService vacationPayCalculateInDaysService;

    @Autowired
    public VacationPayCalculateController(VacationPayCalculateService vacationPayCalculateService,
                                          VacationPayCalculateInDaysService vacationPayCalculateInDaysService) {
        this.vacationPayCalculateService = vacationPayCalculateService;
        this.vacationPayCalculateInDaysService = vacationPayCalculateInDaysService;
    }


    /**
     * Тут появилась куча вопросов, с таким обычно к лиду, но как есть.
     * В условии задачи сказано, что опционально на вход подаются ДНИ ухода в отпуск.
     * Условие менять не стал, оптимальней было бы с подачей только даты начала отпуска,
     * т. к. конец сможем рассчитать по количеству этих дней.
     * <p>
     * Из-за получения даты конца отпуска, количество дней отпуска "затирается",
     * теперь важны только точные даты, мало ли человек ошибся при рассчете дней отпуска.
     * <p>
     * Если получили только startDate или только endDate, обращаемся к количеству дней,
     * т. к. по условию кол-во дней обязательно.
     *
     * @param avgSalaryPerYear - средняя зарплата за год
     * @param vacationDays - количество отпускных дней
     * @param startDate - с какой даты начинать отсчет отпуска
     * @param endDate - какой датой заканчивать отсчет
     * @return Data Transfer Object с полями message и vacationPay
     */
    @GetMapping("/calculate")
    public Object getVacationPay(@RequestParam("averageSalary") double avgSalaryPerYear,
                                 @RequestParam("vacationDays") int vacationDays,
                                 @RequestParam("startDate") Optional<String> startDate,
                                 @RequestParam("endDate") Optional<String> endDate) {

        try {

            if (startDate.isPresent() && endDate.isPresent()) {
                vacationDays = vacationPayCalculateInDaysService.getPaidDays(startDate.get(), endDate.get());
            }

            return vacationPayCalculateService.getCalculateVacationPay(avgSalaryPerYear, vacationDays);

        } catch (IllegalArgumentException | DateTimeParseException e) {
            return new ResponseEntity<>(new ErrorDto(e.getMessage(), LocalDate.now()), HttpStatus.BAD_REQUEST);
        }
    }
}
