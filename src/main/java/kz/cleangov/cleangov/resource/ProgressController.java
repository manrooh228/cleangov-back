package kz.cleangov.cleangov.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.cleangov.cleangov.service.ProgressService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/progress")
@RequiredArgsConstructor
public class ProgressController {

    final ProgressService progressService;

    @PostMapping("/update")
    public ResponseEntity<String> updateProgress(@RequestBody ProgressRequest progressRequest) {
        try {
            progressService.updateProgress(progressRequest.getUserId(), progressRequest.getTaskId(), progressRequest.getProgress());
            return ResponseEntity.ok("Progress updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating progress");
        }
    }
}