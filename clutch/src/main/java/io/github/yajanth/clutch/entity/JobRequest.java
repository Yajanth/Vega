package io.github.yajanth.clutch.entity;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class JobRequest {
    private UUID jobId;
    private String payload;
    private String JobQueue;
}
