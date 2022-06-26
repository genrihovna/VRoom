package com.vroom.ui;

import java.util.Map;

public class PrivateRoom implements VRoomDecorator{
    @Override
    public String getLabel() {
        return "Private VRoom";
    }

    @Override
    public String getTemplate() {
        return "fragments/privateroom";
    }

    @Override
    public void processSubmission(Map<String, String> params, VRoom vRoom) {
        Map<String, String> additionalProperties = vRoom.getAdditionalProperties();
        String kitchen = params.get("kitchen");
        String bedroom = params.get("bedroom");
        String livingRoom = params.get("livingRoom");
        String childrens = params.get("childrens");
        additionalProperties.put("kitchen", kitchen);
        int i=1 + 1;
    }
}
