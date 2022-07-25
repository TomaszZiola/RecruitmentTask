package com.ziola.recruitmenttask.errors;

public class TenantNotFoundException extends RuntimeException{
    public TenantNotFoundException(String s) {
        super(s);
    }
}
