package com.javaex.user.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class JeffUserEventVo {

	 private int anniversaryId;
	 private int userId;
	 private String eventName;
	 private LocalDate eventDate; // Use LocalDate instead of LocalDateTime
	 private boolean notificationEnabled;

    // Getters and Setters
    public int getAnniversaryId() {
        return anniversaryId;
    }

    public void setAnniversaryId(int anniversaryId) {
        this.anniversaryId = anniversaryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public boolean isNotificationEnabled() {
        return notificationEnabled;
    }

    public void setNotificationEnabled(boolean notificationEnabled) {
        this.notificationEnabled = notificationEnabled;
    }

    @Override
    public String toString() {
        return "JeffUserEventVo{" +
                "anniversaryId=" + anniversaryId +
                ", userId=" + userId +
                ", eventName='" + eventName + '\'' +
                ", eventDate=" + eventDate +
                ", notificationEnabled=" + notificationEnabled +
                '}';
    }
}
