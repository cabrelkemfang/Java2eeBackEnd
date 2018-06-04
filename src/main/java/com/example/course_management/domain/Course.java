package com.example.course_management.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id")
    private Long id;

    private String course_code;

    private String course_title;

    private int course_value;

    private String course_status;

    private int semester;

    private int level;

    private  String option;

    private String course_master;

    @ManyToMany(cascade = CascadeType.ALL)

    @JoinTable(name = "course_content",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "content_id", referencedColumnName = "content_id"))
    private Set<Contents> contents = new HashSet<Contents>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public int getCourse_value() {
        return course_value;
    }

    public void setCourse_value(int course_value) {
        this.course_value = course_value;
    }

    public String getCourse_status() {
        return course_status;
    }

    public void setCourse_status(String course_status) {
        this.course_status = course_status;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getCourse_master() {
        return course_master;
    }

    public void setCourse_master(String course_master) {
        this.course_master = course_master;
    }

    public Set<Contents> getContents() {
        return contents;
    }

    public void setContents(Set<Contents> contents) {
        this.contents = contents;
    }

    public Course(String course_code, String course_title, int course_value, String course_status, int semester, int level, String option, String course_master, Set<Contents> contents) {
        this.course_code = course_code;
        this.course_title = course_title;
        this.course_value = course_value;
        this.course_status = course_status;
        this.semester = semester;
        this.level = level;
        this.option = option;
        this.course_master = course_master;
        this.contents = contents;
    }

    public Course() {
    }
}
