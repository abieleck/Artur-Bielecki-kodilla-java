package com.kodilla.testing2.config;

public class SystemNotSupportedException extends Exception {
    public SystemNotSupportedException(String systemName) {
        super("This application does not support web drivers for os " + systemName);
    }
}
