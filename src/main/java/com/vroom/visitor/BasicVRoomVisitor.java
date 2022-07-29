package com.vroom.visitor;

import com.vroom.model.submission.Submission;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class BasicVRoomVisitor extends VRoomVisitor {

    @Override
    public void typeSpecificJSON(Submission submission) {

    }
}
