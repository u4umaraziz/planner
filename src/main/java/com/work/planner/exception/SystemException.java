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

public class SystemException extends BaseException {
    public SystemException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "001", "Some Error Occured");
    }
}
