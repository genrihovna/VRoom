package com.vroom.ui;

import com.vroom.helper.VRoomHelper;

import java.util.HashMap;
import java.util.Map;
/*
the data that represents a VRoom
 */
public class VRoom {

    public static final String HELPER = "HELPER";
    private String projectName;
    private String square;
    private String budget;
    private String dueDate;
    private Map<String, String> additionalProperties;

    public VRoom() {
        setAdditionalProperties(new HashMap<String,String>());
        additionalProperties.put(HELPER, "vRoomHelper");
    }

    public String accept(VRoomHelper vRoomHelper){
        return vRoomHelper.visit(this);
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Map<String, String> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, String> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
