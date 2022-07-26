package com.vroom.service;

import com.vroom.model.VRoom;
import com.vroom.model.VRoomAttributeGroups;
import com.vroom.model.VRoomDecorator;
import com.vroom.visitor.VRoomVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class VRoomService {

    @Autowired
    private ApplicationContext context;

    //all attributes that are available for selection
    @Autowired
    private VRoomAttributeGroups vRoomAttributeGroups;

    //all of the saved VRooms
    private List<VRoom> allVRooms;

    //the decorators that apply for our rooms
    private List<VRoomDecorator> vRoomDecorators;

    public VRoomService() {
        allVRooms = new ArrayList<VRoom>();
        vRoomDecorators = new ArrayList<VRoomDecorator>();
    }

    public List<String> addVRoomService(Map<String, String> requestParams) {
        //get all keys of the selected attributes
        Set<String> selectedKeys = requestParams.keySet();

        //a collection of templates that correspond to the user's selection
        List<String> templates = new ArrayList<String>();

        //iterate over the selected keys, and get the template identifier.
        for (String key : selectedKeys) {
            VRoomDecorator vRoomDecorator = vRoomAttributeGroups
                    .getDecorators().get(key);

            if(vRoomDecorator!=null){
                //a collection containing only the selected decorators.
                vRoomDecorators.add(vRoomDecorator);
                String template = vRoomDecorator.getTemplate();
                templates.add(template);
            }
        }
        return templates;
    }

    public void saveVRoomService(VRoom vRoom, Map<String, String> requestParams){
        //implement the command pattern to process our decorators.
        //iterate over our decorators and invoke the command method
        for (VRoomDecorator v: vRoomDecorators) {
            v.processSubmission(requestParams, vRoom);
        }
        //add the vroom submitted to our collection of saved vrooms
        allVRooms.add(vRoom);
    }

    public String generateJSON() {
        //String builder to contain our JSON
        StringBuilder json = new StringBuilder();

        //iterate over our collection of vrooms, and invoke the visitor.
        for (VRoom vRoom : allVRooms) {
            Map<String, String> additionalProperties = vRoom.getAdditionalProperties();
            String helperBean = additionalProperties.get(VRoom.VISITOR);
            VRoomVisitor vRoomHelper = context.getBean(helperBean, VRoomVisitor.class);

            String vRoomJSON = vRoom.accept(vRoomHelper);
            json.append(vRoomJSON);
        }
        return json.toString();
    }
}
