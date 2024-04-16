package ru.virtya.vacationPayCalculate.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.virtya.dto.ErrorDto;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public @ResponseBody ResponseEntity<ErrorDto> handleMissingParams(MissingServletRequestParameterException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new ErrorDto(e.getMessage(), LocalDate.now()), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public @ResponseBody ResponseEntity<ErrorDto> handleIllegalArgument(IllegalArgumentException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new ErrorDto(e.getMessage(), LocalDate.now()), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(DateTimeParseException.class)
    public @ResponseBody ResponseEntity<ErrorDto> handleDateTimeParse(DateTimeParseException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new ErrorDto(e.getMessage(), LocalDate.now()), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody ResponseEntity<ErrorDto> handleMethodArgumentNotValid(ConstraintViolationException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new ErrorDto(e.getMessage(), LocalDate.now()), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
