package com.example.course_management.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "contents")
public class Contents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long content_id;

    @Column(name = "chapter_id")
    private Long chapter;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JsonBackReference
    @JoinTable(name = "course_content",
            joinColumns = @JoinColumn(name = "content_id", referencedColumnName = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"))
    private Course course;

    public Long getId() {
        return content_id;
    }

    public void setId(Long content_id) {
        this.content_id = content_id;
    }

    public Long getChapter() {
        return chapter;
    }

    public void setChapter(Long chapter) {
        this.chapter = chapter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Contents(Long chapter, String content, Course course) {
        this.chapter = chapter;
        this.content = content;
        this.course = course;
    }

    public Contents() {
    }
}
