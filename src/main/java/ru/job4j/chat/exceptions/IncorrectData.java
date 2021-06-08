package ru.job4j.chat.exceptions;

public class IncorrectData {
    private String Info;

    public IncorrectData(String info) {
        Info = info;
    }

    public String getInfo() {
        return Info;
    }
}
