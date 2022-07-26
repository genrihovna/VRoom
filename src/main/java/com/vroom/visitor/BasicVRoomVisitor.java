package com.vroom.visitor;

import org.json.simple.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class BasicVRoomVisitor extends VRoomVisitor {

    @Override
    public void typeSpecificJSON(JSONObject jsonObject) {

    }
}
