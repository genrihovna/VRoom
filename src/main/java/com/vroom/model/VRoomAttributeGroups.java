package com.vroom.model;

import com.vroom.model.rooms.BusinessRoom;
import com.vroom.model.rooms.EthernetRoom;
import com.vroom.model.rooms.PrivateHouse;
import com.vroom.model.rooms.PrivateRoom;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class VRoomAttributeGroups {

    private final PrivateRoom privateRoom;
    private final PrivateHouse privateHouse;
    private final BusinessRoom businessRoom;
    private final EthernetRoom ethernetRoom;
    @Getter
    private Map<String, VRoomDecorator> decorators;

    @PostConstruct
    private void initDecorators() {
        decorators = new HashMap<>();
        decorators.put("Private House", privateHouse);
        decorators.put("Private Room", privateRoom);
        decorators.put("Business VRoom", businessRoom);
        decorators.put("Include Ethernet", ethernetRoom);
    }
}
