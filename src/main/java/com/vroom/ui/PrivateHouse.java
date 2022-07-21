package com.vroom.ui;

import java.util.Map;

public class PrivateHouse implements VRoomDecorator{
    @Override
    public String getLabel() {
        return "Private House";
    }

    @Override
    public String getTemplate() {
        return "fragments/privatehouse";
    }

    @Override
    public void processSubmission(Map<String, String> params, VRoom vRoom) {
        String privateHouseDescription = params.get("privateHouseDescription");
        Map<String, String> additionalProperties = vRoom.getAdditionalProperties();

        additionalProperties.put("privateHouseDescription", privateHouseDescription);
        additionalProperties.put(VRoom.HELPER, "privateHouseHelper");
    }
}
