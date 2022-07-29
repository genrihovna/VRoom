package com.vroom.model.submission;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Submission {

    private String projectName;
    private String square;
    private String budget;
    private String dueDate;

    private String description;

}
