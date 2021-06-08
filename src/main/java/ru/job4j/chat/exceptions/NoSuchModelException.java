package ru.job4j.chat.exceptions;

public class NoSuchModelException extends RuntimeException {
    public NoSuchModelException(String message) {
        super(message);
    }
}
