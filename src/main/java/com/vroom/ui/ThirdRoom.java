package com.vroom.ui;

import java.util.Map;

public class ThirdRoom implements VRoomDecorator{
    @Override
    public String getLabel() {
        return "Third VRoom";
    }

    @Override
    public String getTemplate() {
        return "fragments/thirdroom";
    }

    @Override
    public void processSubmission(Map<String, String> params, VRoom vRoom) {
        String thirdInfo = params.get("thirdInfo");
        int i=1 + 1;
    }
}
