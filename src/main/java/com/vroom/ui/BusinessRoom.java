package com.vroom.ui;

import java.util.Map;

public class BusinessRoom implements VRoomDecorator{
    @Override
    public String getLabel() {
        return "Business VRoom";
    }

    @Override
    public String getTemplate() {
        return "fragments/businessroom";
    }

    @Override
    public void processSubmission(Map<String, String> params, VRoom vRoom) {
        String workspace = params.get("workspace");
        int i=1 + 1;
    }
}
