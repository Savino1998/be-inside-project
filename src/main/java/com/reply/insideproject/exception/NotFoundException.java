package com.reply.insideproject.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final String message;

    public NotFoundException(Long id, Class clazz) {
        String className = clazz.getName();
        className = className.substring(className.lastIndexOf(".") + 1);
        message = className + " with id: " + id + " does not exists";
    }

}
