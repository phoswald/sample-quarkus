package com.github.phoswald.sample.sample;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "echoRequest")
public class EchoRequest {

    private String input;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
