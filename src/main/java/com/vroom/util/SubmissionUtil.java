package com.vroom.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vroom.model.submission.Submission;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubmissionUtil {

    private static SubmissionUtil submissionUtil;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static SubmissionUtil getInstance() {
        if (submissionUtil == null) {
            submissionUtil = new SubmissionUtil();
        }
        return submissionUtil;
    }

    public String parseSubmission(Submission submission){
        try {
            return objectMapper.writeValueAsString(submission);
        } catch (JsonProcessingException e) {
            //ignored
        }
        return "Error parsing submission";
    }


}
