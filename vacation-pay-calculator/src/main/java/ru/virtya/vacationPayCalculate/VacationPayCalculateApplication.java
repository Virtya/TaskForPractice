package ru.virtya.vacationPayCalculate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Приложение принимает среднюю зарплату за 12 месяцев и количество дней отпуска -
 * отвечает суммой отпускных, которые придут сотруднику.
 * При запросе также можно указать точные дни ухода в отпуск,
 * тогда должен проводиться расчет отпускных с учётом праздников и выходных.
 */
@SpringBootApplication
public class VacationPayCalculateApplication {

	public static void main(String[] args) {

		SpringApplication.run(VacationPayCalculateApplication.class, args);
	}

}