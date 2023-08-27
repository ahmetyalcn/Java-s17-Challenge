package com.workintech.challenge.mapping;

import com.workintech.challenge.entity.*;

public class CourseResponseFactory {
    public static CourseResponse createCourseResponse(Course course, CourseGpa low, CourseGpa mid, CourseGpa high){
        if (course.getCredit()<=2){
            return new CourseResponse(course, course.getGrade().getCoefficient() * course.getCredit() * ((LowCourseGpa)low).getGpa());
        }else if (course.getCredit()==3){
            return new CourseResponse(course, course.getGrade().getCoefficient() * course.getCredit() * ((MediumCourseGpa)mid).getGpa());
        }else if (course.getCredit()==4){
            return new CourseResponse(course, course.getGrade().getCoefficient() * course.getCredit() * ((HighCourseGpa)high).getGpa());
        }
        return null;
    }
}
