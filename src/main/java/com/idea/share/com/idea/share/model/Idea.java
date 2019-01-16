package com.idea.share.com.idea.share.model;

import java.time.LocalDate;
import java.util.Objects;

public class Idea {

    private Long id;
    private String title;
    private String description;
    private LocalDate addedAt;
    private int score;
    private boolean active;

    public Idea() {
    }

    public Idea(Long id, String title, String description, LocalDate addedAt, int score, boolean active) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.addedAt = addedAt;
        this.score = score;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDate addedAt) {
        this.addedAt = addedAt;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Idea idea = (Idea) o;
        return score == idea.score &&
                active == idea.active &&
                Objects.equals(id, idea.id) &&
                Objects.equals(title, idea.title) &&
                Objects.equals(description, idea.description) &&
                Objects.equals(addedAt, idea.addedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, addedAt, score, active);
    }

    @Override
    public String toString() {
        return "Idea{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", addedAt=" + addedAt +
                ", score=" + score +
                ", active=" + active +
                '}';
    }
}
