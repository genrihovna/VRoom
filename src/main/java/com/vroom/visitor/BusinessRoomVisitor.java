package com.vroom.visitor;

import com.vroom.model.submission.ExtendedSubmission;
import com.vroom.model.submission.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class BusinessRoomVisitor extends VRoomVisitor {

    @Autowired
    private EthernetRoomVisitor ethernetRoomVisitor;

    @Override
    public void typeSpecificJSON(Submission submission) {
        Map<String, String> additionalProperties = vRoom.getAdditionalProperties();
        ExtendedSubmission mySubmission = (ExtendedSubmission) submission;
        mySubmission.setDescription(additionalProperties.get("officeDescription"));
        //ethernetRoomVisitor.typeSpecificJSON(jsonObject);
    }
}
