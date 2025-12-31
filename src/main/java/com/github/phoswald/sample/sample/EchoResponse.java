package com.github.phoswald.sample.sample;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EchoResponse")
public class EchoResponse {

    private String output;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
