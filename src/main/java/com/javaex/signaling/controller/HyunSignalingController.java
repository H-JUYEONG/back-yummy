package com.javaex.signaling.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signaling")
public class HyunSignalingController {

	private final ConcurrentHashMap<String, ConcurrentHashMap<String, List<String>>> signalingData = new ConcurrentHashMap<>();

	  @PostMapping("/{sessionId}/{type}")
	    public ResponseEntity<String> saveSignalingData(@PathVariable String sessionId, @PathVariable String type, @RequestBody String data) {
	        if ("ice-candidate".equals(type)) {
	            signalingData.computeIfAbsent(sessionId, k -> new ConcurrentHashMap<>())
	                         .computeIfAbsent(type, k -> new ArrayList<>())
	                         .add(data);
	        } else {
	            signalingData.computeIfAbsent(sessionId, k -> new ConcurrentHashMap<>())
	                         .put(type, List.of(data));
	        }
	        return ResponseEntity.ok(type + " saved for session: " + sessionId);
	    }

	    @GetMapping("/{sessionId}/{type}")
	    public ResponseEntity<Object> getSignalingData(@PathVariable String sessionId, @PathVariable String type) {
	        List<String> data = signalingData.getOrDefault(sessionId, new ConcurrentHashMap<>()).get(type);
	        if (data == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Data not found"));
	        }
	        return ResponseEntity.ok(data.size() == 1 ? data.get(0) : data);
	    }

	    @DeleteMapping("/{sessionId}")
	    public ResponseEntity<String> deleteSession(@PathVariable String sessionId) {
	        signalingData.remove(sessionId);
	        return ResponseEntity.ok("Session " + sessionId + " deleted");
	    }
}
