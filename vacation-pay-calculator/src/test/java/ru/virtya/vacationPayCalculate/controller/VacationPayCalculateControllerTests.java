package ru.virtya.vacationPayCalculate.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.virtya.vacationPayCalculate.ApplicationTest;
import ru.virtya.vacationPayCalculate.controllers.VacationPayCalculateController;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class VacationPayCalculateControllerTests extends ApplicationTest {

	@Autowired
	private VacationPayCalculateController vacationPayCalculateController;

	@Test
	void contextLoads() {
		assertThat(vacationPayCalculateController).isNotNull();
	}

	@Test
	@SneakyThrows
	public void equalZeroVacationDaysShouldThrowException() {
		mockMvc.perform(
						MockMvcRequestBuilders
								.get("/calculate")
								.param("averageSalary", String.valueOf(20000))
								.param("vacationDays", String.valueOf(0))
								.characterEncoding("utf-8")
				).andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ConstraintViolationException));
	}

	@Test
	@SneakyThrows
	public void belowZeroVacationDaysShouldThrowException() {
		mockMvc.perform(
						MockMvcRequestBuilders
								.get("/calculate")
								.param("averageSalary", String.valueOf(20000))
								.param("vacationDays", String.valueOf(-5))
								.characterEncoding("utf-8")
				).andExpect(status().is2xxSuccessful())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ConstraintViolationException));
	}

	@Test
	@SneakyThrows
	public void belowZeroSalaryPerYearShouldThrowException() {
		mockMvc.perform(
						MockMvcRequestBuilders
								.get("/calculate")
								.param("averageSalary", String.valueOf(-20000))
								.param("vacationDays", String.valueOf(10))
								.characterEncoding("utf-8")
				).andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ConstraintViolationException));
	}

	@Test
	@SneakyThrows
	public void getVacationPayWithoutHolidaysShouldReturnCorrectValue() {
		String responseJson = readFileFromResource("responses/getCalculateWithoutHolidays.json");

		mockMvc.perform(
						MockMvcRequestBuilders
								.get("/calculate")
								.param("averageSalary", String.valueOf(20000))
								.param("vacationDays", String.valueOf(10))
								.characterEncoding("utf-8")
				).andExpect(status().isOk())
				.andExpect(content().json(responseJson, false));
	}

	@Test
	@SneakyThrows
	public void getVacationPayWithHolidaysShouldReturnCorrectValue() {
		String responseJson = readFileFromResource("responses/getCalculateWithHolidays.json");

		mockMvc.perform(
						MockMvcRequestBuilders
								.get("/calculate")
								.param("averageSalary", String.valueOf(20000))
								.param("vacationDays", String.valueOf(7))
								.param("startDate", "01-05-2024")
								.param("endDate", "07-05-2024")
								.characterEncoding("utf-8")
				).andExpect(status().isOk())
				.andExpect(content().json(responseJson, false));
	}
}
