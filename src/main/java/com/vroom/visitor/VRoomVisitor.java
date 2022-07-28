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

        Submission submission = new ExtendedSubmission();

        submission.setProjectName(vRoom.getProjectName());
        submission.setBudget(vRoom.getBudget());
        submission.setSquare(vRoom.getSquare());
        submission.setDueDate(vRoom.getDueDate());

        typeSpecificJSON(submission);
        return SubmissionUtil.getInstance().parseSubmission(submission);
    }

    /*
    A hook into our subclasses to see if they want to generate specific JSON.
     */
    public abstract void typeSpecificJSON(Submission submission);
}
