package com.skp.demo.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Message {
    private String key;
    private String value;
}
