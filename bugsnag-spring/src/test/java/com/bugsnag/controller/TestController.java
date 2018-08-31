package com.bugsnag.controller;

import com.bugsnag.Bugsnag;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Autowired
    private Bugsnag bugsnag;

    /**
     * Throw a runtime exception
     */
    @RequestMapping("/throw-runtime-exception")
    public void throwRuntimeException() {
        throw new RuntimeException("Test");
    }

    /**
     * Throw an exception where the severity reason is exceptionClass
     */
    @RequestMapping("/throw-type-mismatch-exception")
    public void throwTypeMismatchException() {
        throw new TypeMismatchException("Test", String.class);
    }

    /**
     * Report a handled exception where the severity reason is exceptionClass
     */
    @RequestMapping("/handled-type-mismatch-exception")
    public String handledTypeMismatchException() {
        try {
            throw new TypeMismatchException("Test", String.class);
        } catch (TypeMismatchException ex) {
            bugsnag.notify(ex);
        }
        return "The exception was handled";
    }
}
