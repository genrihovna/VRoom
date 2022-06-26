package com.vroom.ui;

import java.util.Map;

public class VRoomAttributeGroups {
    private Map<String, VRoomDecorator> decorators;
    public Map<String, VRoomDecorator> getDecorators() {
        return decorators;
    }

    public void setDecorators(Map<String, VRoomDecorator> decorators) {
        this.decorators = decorators;
    }
}
