package com.artfactory.project01.todayart.model;

import lombok.Getter;
import lombok.Setter;

public class Mail {
    @Getter
    @Setter
    private String from;

    @Getter
    @Setter
    private String to;

    @Getter
    @Setter
    private String subject;

    @Getter
    @Setter
    private String content;

    public Mail() {}
    public Mail(String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
