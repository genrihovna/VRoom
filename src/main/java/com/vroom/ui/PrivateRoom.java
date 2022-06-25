package com.vroom.ui;

import java.util.Map;

public class PrivateRoom implements VRoomDecorator{
    @Override
    public String getLabel() {
        return "Private VRoom";
    }

    @Override
    public String getTemplate() {
        return null;
    }

    @Override
    public void processSubmission(Map<String, String> params) {

    }
}
