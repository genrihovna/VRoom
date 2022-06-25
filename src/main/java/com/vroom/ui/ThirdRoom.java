package com.vroom.ui;

import java.util.Map;

public class ThirdRoom implements VRoomDecorator{
    @Override
    public String getLabel() {
        return "Third VRoom";
    }

    @Override
    public String getTemplate() {
        return null;
    }

    @Override
    public void processSubmission(Map<String, String> params) {

    }
}
