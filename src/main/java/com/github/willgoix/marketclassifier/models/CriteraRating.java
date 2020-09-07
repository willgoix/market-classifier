package com.github.willgoix.marketclassifier.models;

import java.io.Serializable;

/**
 * @author Willian Gois (github/willgoix) - 03/09/2020
 */
public class CriteraRating implements Serializable {

    private int votesCount;
    private int votesSum;

    public void incrementVote(int value) {
        this.votesCount++;
        this.votesSum += value;
    }

    public void setVotesCount(int votesCount) {
        this.votesCount = votesCount;
    }

    public int getVotesCount() {
        return this.votesCount;
    }

    public void setVotesSum(int votesSum) {
        this.votesSum = votesSum;
    }

    public int getVotesSum() {
        return this.votesSum;
    }

    public int getRatingAverage() {
        return this.votesSum / this.votesCount;
    }
}
