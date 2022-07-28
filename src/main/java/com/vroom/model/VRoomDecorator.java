package com.vroom.model;

import com.vroom.model.rooms.VRoom;

import java.util.Map;

/*
A decorator design pattern that provides attributes
that are specific to a vroom type or subtype
*/
public interface VRoomDecorator {
    /**
     * label that will allow customer to decide whether or not apply this decorator
     * @return a label that describes the decorator
     */
    String getLabel();
    /**
     * HTML template that the customer may wish to populate for this vroom type
     * @return HTML template
     */
    String getTemplate();

    /**
     * How to handle request parameter (name-value pairs)
     * of the submitted form
     * @param params request parameter
     */
    void processSubmission(Map<String, String> params, VRoom vRoom);
}