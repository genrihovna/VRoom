package com.vroom.helper;

import org.json.simple.JSONObject;

import java.util.Map;

public class PrivateRoomHelper extends VRoomHelper{

    @Override
    public void typeSpecificJSON(JSONObject jsonObject) {
        Map<String, String> additionalProperties = vRoom.getAdditionalProperties();
        jsonObject.put("privateRoomDescription", additionalProperties.get("privateRoomDescription"));
    }
}
