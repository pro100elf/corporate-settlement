package ru.pogornev.course.taskspring2.service;

import ru.pogornev.course.taskspring2.model.TprRequest;

public interface TppProductRegisterService {
    long tppProductRegisterInsert(TprRequest request);
    void validate(TprRequest request);
}
