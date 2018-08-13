package com.addnumbers.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Incorrect answer")
public class IncorrectAnswerException extends RuntimeException {

}

