package com.example.javastudy.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DecimalException extends RuntimeException {
    private static final Logger logger = LogManager.getLogger(DecimalException.class);

    public DecimalException() {
        super();
        logger.error("Server error");
    }

    public DecimalException(String msg) {
        super(msg);
        logger.error(msg);
    }

    public DecimalException(String msg, Throwable reason) {
        super(msg, reason);
        logger.error(msg);
    }

    public DecimalException(Throwable reason) {
        super(reason);
        logger.error(reason.getMessage());
    }
}
