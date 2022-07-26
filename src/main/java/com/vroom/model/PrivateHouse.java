package com.vroom.model;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
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
        additionalProperties.put(VRoom.VISITOR, "privateHouseVisitor");
    }
}
