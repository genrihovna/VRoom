package com.vroom.visitor;
import com.vroom.model.VRoom;
import org.json.simple.JSONObject;
public abstract class VRoomVisitor {

    protected VRoom vRoom;

    public String visit(VRoom vRoom) {
        this.vRoom=vRoom;
        return generateJSON();
    }

    public String generateJSON(){
        String returnValue="";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("projectName", vRoom.getProjectName());
        jsonObject.put("square", vRoom.getSquare());
        jsonObject.put("budget", vRoom.getBudget());
        jsonObject.put("dueDate", vRoom.getDueDate());
        //reach into subclasses and ask them to generate any JSON they wish
        typeSpecificJSON(jsonObject);
        return jsonObject.toString();
    }

    /*
    A hook into our subclasses to see if they want to generate specific JSON.
     */
    public abstract void typeSpecificJSON(JSONObject jsonObject);
}
