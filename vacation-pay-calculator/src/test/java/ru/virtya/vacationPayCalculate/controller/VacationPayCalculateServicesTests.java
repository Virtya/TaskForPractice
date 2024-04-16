package ru.virtya.vacationPayCalculate.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.virtya.vacationPayCalculate.ApplicationTest;
import ru.virtya.vacationPayCalculate.services.VacationPayCalculateInDaysService;
import ru.virtya.vacationPayCalculate.services.VacationPayCalculateService;

import java.time.format.DateTimeParseException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class VacationPayCalculateServicesTests extends ApplicationTest {

	@Autowired
	private VacationPayCalculateInDaysService vacationPayCalculateInDaysService;

	@Autowired
	private VacationPayCalculateService vacationPayCalculateService;

	@Test
	void contextLoads() {
		assertThat(vacationPayCalculateInDaysService).isNotNull();
		assertThat(vacationPayCalculateService).isNotNull();
	}

	@Test
	public void countingTotalPayForVacationShouldReturnCorrectValue() {
		assertEquals(6825.94, vacationPayCalculateService
				.getCalculateVacationPay(20000, 10)
				.getVacationPay()
		);
	}

	@Test
	public void getPaidDaysShouldReturnCorrectValue() {
		assertEquals(4, vacationPayCalculateInDaysService
				.getPaidDays("01-05-2024", "07-05-2024")
		);
	}

	@Test
	public void getPaidDaysWithIncorrectFormatShouldReturnParseException() {
		assertThrows(DateTimeParseException.class,
				() -> vacationPayCalculateInDaysService
						.getPaidDays("01-02-2024", "01.02.24")
		);
	}
}
