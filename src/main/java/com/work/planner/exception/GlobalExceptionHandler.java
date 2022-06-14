/*
 * Copyright 2017-2021 Commencis Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Commencis Limited.
 * Any reproduction of this material must contain this notice.
 */

package com.work.planner.exception;

import com.work.planner.model.RosterErrorResponse;
import com.work.planner.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<RosterErrorResponse> handleUnexpectedException(final ApplicationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(prepareErrorResponse(exception));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<RosterErrorResponse> handleException(final Exception exception) {
        log.error("Unexpected exception occurred: ", exception);
        final SystemException systemException = new SystemException();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(prepareErrorResponse(systemException));
    }
    private RosterErrorResponse prepareErrorResponse(final BaseException exception) {
        final RosterErrorResponse bfsErrorResponse = new RosterErrorResponse();

        bfsErrorResponse.setCode(exception.getCode());
        bfsErrorResponse.setTitle(Constants.ERROR_TITLE);
        bfsErrorResponse.setMessage(exception.getMsg());

        return bfsErrorResponse;
    }
}
