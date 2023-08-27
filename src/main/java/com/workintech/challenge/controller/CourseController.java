package com.workintech.challenge.controller;

import com.workintech.challenge.entity.Course;
import com.workintech.challenge.entity.CourseGpa;
import com.workintech.challenge.exceptions.CourseValidator;
import com.workintech.challenge.mapping.CourseResponse;
import com.workintech.challenge.mapping.CourseResponseFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private List<Course> courses;
    private CourseGpa lowGpa;
    private CourseGpa mediumGpa;
    private CourseGpa highGpa;

    @Autowired
    public CourseController(@Qualifier("lowCourseGpa") CourseGpa lowGpa,
                            @Qualifier("mediumCourseGpa") CourseGpa mediumGpa,
                            @Qualifier("highCourseGpa") CourseGpa highGpa) {
        this.lowGpa = lowGpa;
        this.mediumGpa = mediumGpa;
        this.highGpa = highGpa;
    }

    @PostConstruct
    public void init(){
        courses = new ArrayList<>();
    }
    @GetMapping("/")
    public List<Course> get(){
        return courses;
    }

    @GetMapping("/{name}")
    public Course get(@PathVariable String name){
        Optional<Course> found = courses.stream().filter(course -> course.getName().equals(name)).findFirst();
        CourseValidator.isCourseNotExist(found);
        return found.get();
    }

    @PostMapping("/")
    public CourseResponse save(@RequestBody Course course){
        CourseValidator.isCourseNotValid(course);
        CourseValidator.idIdExist(courses,course.getId());
        CourseValidator.isCourseExist(courses,course);

        courses.add(course);
        return CourseResponseFactory.createCourseResponse(course,lowGpa,mediumGpa,highGpa);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable int id, @RequestBody Course course){
        Optional<Course> found = courses.stream().filter(c -> c.getId() == id).findFirst();
        CourseValidator.isCourseNotExist(found);
        CourseValidator.isCourseNotValid(course);
        int index = courses.indexOf(found.get());
        courses.set(index,course);
        return course;
    }
    @DeleteMapping("/{id}")
    public Course delete(@PathVariable int id){
        Optional<Course> found = courses.stream().filter(c -> c.getId() == id).findFirst();
        CourseValidator.isCourseNotExist(found);
        courses.remove(found.get());
        return found.get();
    }
}
