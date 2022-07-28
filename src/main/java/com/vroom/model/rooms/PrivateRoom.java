package com.vroom.model.rooms;

import com.vroom.model.VRoomDecorator;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PrivateRoom implements VRoomDecorator {
    @Override
    public String getLabel() {
        return "Private Room";
    }

    @Override
    public String getTemplate() {
        return "fragments/privateroom";
    }

    @Override
    public void processSubmission(Map<String, String> params, VRoom vRoom) {
        Map<String, String> additionalProperties = vRoom.getAdditionalProperties();
        String privateRoomDescription = params.get("privateRoomDescription");
        additionalProperties.put("privateRoomDescription", privateRoomDescription);
        additionalProperties.put(vRoom.VISITOR, "privateRoomVisitor");
    }
}