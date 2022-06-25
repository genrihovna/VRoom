package com.vroom.ui;

import java.util.Map;

public class BusinessRoom implements VRoomDecorator{
    @Override
    public String getLabel() {
        return "Business VRoom";
    }

    @Override
    public String getTemplate() {
        return null;
    }

    @Override
    public void processSubmission(Map<String, String> params) {

    }
}
