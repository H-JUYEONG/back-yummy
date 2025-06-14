package com.javaex.user.model;

import java.util.List;

public class JeffUserPersonalInfoEditVo {
	private JeffUserProfileVo userProfile;      // User profile details
    private List<JeffUserEventVo> userEvents;  // List of user events

    // Getters and Setters
    public JeffUserProfileVo getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(JeffUserProfileVo userProfile) {
        this.userProfile = userProfile;
    }

    public List<JeffUserEventVo> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(List<JeffUserEventVo> userEvents) {
        this.userEvents = userEvents;
    }

    @Override
    public String toString() {
        return "UserDetailsDto{" +
                "userProfile=" + userProfile +
                ", userEvents=" + userEvents +
                '}';
    }
}
