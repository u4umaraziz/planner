/*
 * Copyright 2017-2021 Commencis Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Commencis Limited.
 * Any reproduction of this material must contain this notice.
 */

package com.work.planner.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends BaseException {
    public ApplicationException(final String code, final String message) {
        super(HttpStatus.BAD_REQUEST, code, message);
    }
}
