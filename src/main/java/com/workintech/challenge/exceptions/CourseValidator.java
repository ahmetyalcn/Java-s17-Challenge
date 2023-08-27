package com.workintech.challenge.exceptions;

import com.workintech.challenge.entity.Course;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseValidator {
    public static void isCourseNotExist(Optional course){
        if (course.isEmpty()){
            throw new CourseException("course not found: ", HttpStatus.NOT_FOUND);
        }
    }
    public static void isCourseExist(List<Course> courses, Course course){
        Optional<Course> found = courses.stream().filter(c -> c.getName().equals(course.getName())).findFirst();
        if (found.isPresent()){
            throw new CourseException("course already added before", HttpStatus.NOT_FOUND);
        }
    }
    public static void isCourseNotValid(Course course){
        if (course.getId()<=0 || course.getCredit()<=0 || course.getCredit()>4 || course.getName()==null || course.getName().isEmpty()){
            throw new CourseException("course is not valid", HttpStatus.BAD_REQUEST);
        }
    }

    public static void idIdValid(int id){
        if (id<=0){
            throw new CourseException("id is invalid: "+id, HttpStatus.BAD_REQUEST);
        }
    }
    public static void idIdExist(List<Course> courses, int id){
        Optional<Course> found = courses.stream().filter(c ->c.getId()  == id).findFirst();
        if (found.isPresent()){
            throw new CourseException("id is exist: "+id, HttpStatus.BAD_REQUEST);
        }
    }

}
