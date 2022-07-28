package com.vroom.visitor;

import com.vroom.model.submission.ExtendedSubmission;
import com.vroom.model.submission.Submission;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class EthernetRoomVisitor extends VRoomVisitor{
    @Override
    public void typeSpecificJSON(Submission submission) {
        Map<String, String> additionalProperties = vRoom.getAdditionalProperties();
        ExtendedSubmission mySubmission = (ExtendedSubmission) submission;
        mySubmission.setDescription(additionalProperties.get("ethernet"));
    }

}
