package com.sis.nailsgestion.util;

import java.io.Serial;

public class NotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 3963259713954730543L;

    public NotFoundException(String message) {
        super(message);
    }
}
