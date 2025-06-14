package com.javaex.debate.model;

public class JeffVoteVo {

    private int debate_id; // Debate ID
    private int member_id; // Member ID
    private String side; // Voted side ('left' or 'right')

    // Default constructor
    public JeffVoteVo() {
    }

    // Parameterized constructor
    public JeffVoteVo(int debate_id, int member_id, String side) {
        this.debate_id = debate_id;
        this.member_id = member_id;
        this.side = side;
    }

    // Getters and setters
    public int getDebate_id() {
        return debate_id;
    }

    public void setDebate_id(int debate_id) {
        this.debate_id = debate_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    // toString method
    @Override
    public String toString() {
        return "JeffVoteVo{" +
                "debate_id=" + debate_id +
                ", member_id=" + member_id +
                ", side='" + side + '\'' +
                '}';
    }
}
