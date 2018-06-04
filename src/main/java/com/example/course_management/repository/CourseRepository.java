package com.example.course_management.repository;


import com.example.course_management.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Override
    List<Course> findAll();

    @Query(value = "SELECT * FROM course WHERE level = ?1", nativeQuery = true)
    List<Course> findByLevel(int level);

    @Query(value = "SELECT * FROM course WHERE level = ?1 and option = ?2 and semester = ?3 ", nativeQuery = true)
    List<Course> findByFilter(int level,String option,int semester);

}
