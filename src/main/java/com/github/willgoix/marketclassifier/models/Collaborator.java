package com.github.willgoix.marketclassifier.models;

import com.github.willgoix.marketclassifier.models.enums.EnumTeam;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.StringJoiner;

/**
 * @author Willian Gois (github/willgoix) - 02/09/2020
 */
@Entity
@Table(name = "collaborators")
public class Collaborator extends User {

    private static final Duration VOTE_DELAY = Duration.ofDays(7);

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "team")
    private EnumTeam team;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastVote")
    private Date lastVote;

    public Collaborator() {
        super("null", "null", Collections.emptyList());
    }

    public Collaborator(String email, String password) {
        super(email, password, Collections.emptyList());
    }

    public String getPassword() {
        return this.password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTeam(EnumTeam team) {
        this.team = team;
    }

    public EnumTeam getTeam() {
        return this.team;
    }

    public void setLastVote(Date lastVote) {
        this.lastVote = lastVote;
    }

    public Date getLastVote() {
        return this.lastVote;
    }

    public boolean canVote() {
        return this.lastVote == null || this.lastVote.toInstant().plus(VOTE_DELAY).isBefore(Instant.now());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Collaborator.class.getSimpleName() + "[", "]")
                .add("email=" + email)
                .add("password=" + password)
                .add("team=" + team)
                .add("lastVote=" + lastVote)
                .toString();
    }
}
