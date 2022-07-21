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
        String officeDescription = params.get("officeDescription");
        Map<String, String> additionalProperties = vRoom.getAdditionalProperties();
        additionalProperties.put("officeDescription", officeDescription);
        additionalProperties.put(VRoom.HELPER, "businessRoomHelper");

    }
}
