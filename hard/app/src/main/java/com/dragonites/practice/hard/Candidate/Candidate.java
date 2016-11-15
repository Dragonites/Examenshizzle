package com.dragonites.practice.hard.Candidate;

/**
 * Created by Dragonites on 15/11/2016.
 */

public class Candidate {
    private String name;
    private String party;
    private int age;
    private int votes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return getName();
    }
}
