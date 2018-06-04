package com.example.course_management.controller;


import com.example.course_management.PdfReport.GeneratePdfReport;
import com.example.course_management.domain.Course;
import com.example.course_management.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;


    @RequestMapping(value = "/courses",method = RequestMethod.GET)
    public ResponseEntity<List<Course>> getAllCourse(){
        List<Course> courselist;
        courselist = courseRepository.findAll();
        return new ResponseEntity<>(courselist,HttpStatus.OK);
    }


    @RequestMapping(value = "/allCourses/{level}",method = RequestMethod.GET)
    public ResponseEntity<List<Course>> getAllCourseBylevel(@PathVariable int level){
        List<Course> courselist;
        courselist = courseRepository.findByLevel(level);
        return new ResponseEntity<>(courselist,HttpStatus.OK);
    }

    @RequestMapping(value = "/allCourses/{level}/{option}/{semester}",method = RequestMethod.GET)
    public ResponseEntity<List<Course>> getAllCourseByFilter(@PathVariable int level,@PathVariable String option,@PathVariable int semester){
        List<Course> courselist;
        courselist = courseRepository.findByFilter(level,option,semester);
        return new ResponseEntity<>(courselist,HttpStatus.OK);
    }

    @RequestMapping(value = "/courses",method = RequestMethod.POST)
    public ResponseEntity< Course> saveCourse(@RequestBody Course course){
        Course course1 =courseRepository.save(course);
        return new ResponseEntity<>(course1,HttpStatus.CREATED);
    }



    @RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdfReport() throws IOException {

        List<Course> courselist=courseRepository.findAll();

        ByteArrayInputStream bis = GeneratePdfReport.courseReport(courselist);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "offline; filename = courses.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }


    @RequestMapping(value = "/course/{id}",method = RequestMethod.DELETE)
    public ResponseEntity< ? > DeleteCourse(@PathVariable int id){
        courseRepository.delete((long) id);
        return new ResponseEntity<>("delete",HttpStatus.OK);
    }



    @CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    @RequestMapping(value = "/courses",method = RequestMethod.PUT)
    public ResponseEntity< Course> updateCourse(@RequestBody Course course){
        Course course1 =courseRepository.save(course);
        return new ResponseEntity<>(course1,HttpStatus.CREATED);
    }


}
