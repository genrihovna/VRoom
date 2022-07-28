package com.vroom.model.rooms;

import com.vroom.visitor.VRoomVisitor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
/*
the data that represents a VRoom
 */

@Getter
@Setter
public class VRoom {

    public static final String VISITOR = "VISITOR";
    private String projectName;
    private String square;
    private String budget;
    private String dueDate;
    private Map<String, String> additionalProperties;

    public VRoom() {
        setAdditionalProperties(new HashMap<>());
        additionalProperties.put(VISITOR, "vRoomVisitor");
    }

    public String accept(VRoomVisitor vRoomHelper){
        return vRoomHelper.visit(this);
    }
}
