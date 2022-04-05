package com.reply.insideproject.exception;

import lombok.Getter;

@Getter
public class AlreadyExistsException extends RuntimeException {

    private String message;

    public AlreadyExistsException(String name, Class clazz) {
        String className = clazz.getName();
        className = className.substring(className.lastIndexOf(".") + 1);
        message = className + " with name: " + name + " already exists";
    }

}
