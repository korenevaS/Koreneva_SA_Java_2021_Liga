package com.github.korenevaS.queue.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseFreeSlots {
    private String message;
    private List<String> freeSlots;
}
