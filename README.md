# VacationPayCalculate
Program allows you to get the amount of vacation pay according to the entered average salary per year and vacation days (or exact dates of vacation)

## Test task
*Приложение "Калькулятор отпускных".
Микросервис на SpringBoot + Java 11 c одним API:
GET "/calculacte"*

*Минимальные требования: Приложение принимает твою среднюю зарплату за 12 месяцев и количество дней отпуска - отвечает суммой отпускных, которые придут сотруднику.
Доп. задание: При запросе также можно указать точные дни ухода в отпуск, тогда должен проводиться рассчет отпускных с учётом праздников и выходных*

## Requests

**Запрос с указанием средней зарплаты за год и дней отпуска:**

*localhost:8080/calculate?averageSalary=20000&vacationDays=10*
____
**Запрос с указанием точной даты ухода в отпуск и возвращения из отпуска:**

*localhost:8080/calculate?averageSalary=20000&vacationDays=10&startDate=28-04-2023&endDate=10-05-2023*
____
**Запрос, возвращающий ошибку форматирования:**

*localhost:8080/calculate?averageSalary=20000&vacationDays=10&startDate=28-4-2023&endDate=10-05-203*
____
**Запрос, возвращающий MissingParameterException:**

*localhost:8080/calculate?averageSalary=20000&vacatio=10&startDate=28-04-2023&endDate=10-05-2023*
____
**Запрос, возвращающий ошибку IllegalArgumentException:**

*localhost:8080/calculate?averageSalary=-20000&vacationDays=0*

## Image examples
**Запрос с указанием средней зарплаты за год и дней отпуска:**

![StandartRequest](https://user-images.githubusercontent.com/80851155/235643283-118f8ddc-05e3-4f2e-aaed-eb751554f55f.png)
____
**Запрос с указанием точной даты ухода в отпуск и возвращения из отпуска:**

![OptionalRequest](https://user-images.githubusercontent.com/80851155/235643914-226f4d84-73ba-4f2e-b97c-74eff1f093ba.png)
____
**Запрос, возвращающий ошибку форматирования:**

![ParseError](https://user-images.githubusercontent.com/80851155/235644145-e646cdca-0a0a-4aba-b2a2-aaf1e0cff17d.png)
____
**Запрос, возвращающий MissingParameterException:**

![MissingParameterException](https://user-images.githubusercontent.com/80851155/235644485-87a3e9da-c66d-48f3-aed4-674f06b4db46.png)
____
**Запрос, возвращающий IllegalArgumentException:**

![IllegalArgumentException](https://user-images.githubusercontent.com/80851155/235644861-87e1644e-53f2-4c18-ad9c-1f63812f30bf.png)
