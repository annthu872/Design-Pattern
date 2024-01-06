package com.example.designpattern.authenticationScreenInterface;

import javafx.util.Callback;

public class CustomControllerFactory implements Callback<Class<?>, Object> {

    @Override
    public Object call(Class<?> type) {
        // Instantiate and return your controller here
        return IoCContainer.resolve(type);
    }
}