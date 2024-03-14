package ru.pogornev.course.taskspring2.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class TpResponse {
    private int instanceId;
    private List<Long> registerId;
    private int supplementaryAgreementId;
}
