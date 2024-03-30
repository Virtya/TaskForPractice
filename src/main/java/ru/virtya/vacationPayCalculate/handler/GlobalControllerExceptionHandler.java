package ru.virtya.vacationPayCalculate.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.virtya.vacationPayCalculate.dto.ErrorDto;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public @ResponseBody ResponseEntity<ErrorDto> handleMissingParams(MissingServletRequestParameterException e) {

        return new ResponseEntity<>(
                new ErrorDto(e.getMessage(), LocalDate.now()), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public @ResponseBody ResponseEntity<ErrorDto> handleIllegalArgument(IllegalArgumentException e) {

        return new ResponseEntity<>(
                new ErrorDto(e.getMessage(), LocalDate.now()), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(DateTimeParseException.class)
    public @ResponseBody ResponseEntity<ErrorDto> handleDateTimeParse(DateTimeParseException e) {

        return new ResponseEntity<>(
                new ErrorDto(e.getMessage(), LocalDate.now()), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
