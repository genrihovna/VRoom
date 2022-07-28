package com.vroom.model.rooms;

import com.vroom.model.VRoomDecorator;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EthernetRoom implements VRoomDecorator {
    @Override
    public String getLabel() {
        return "Include Ethernet";
    }

    @Override
    public String getTemplate() {
        return "fragments/ethernetRoom";
    }

    @Override
    public void processSubmission(Map<String, String> params, VRoom vRoom) {
        String ethernet = params.get("ethernet");
        Map<String, String> additionalProperties = vRoom.getAdditionalProperties();
        additionalProperties.put("ethernet", ethernet);
        additionalProperties.put(VRoom.VISITOR, "ethernetRoomVisitor");
    }
}
