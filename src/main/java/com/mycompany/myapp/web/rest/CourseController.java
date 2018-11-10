package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.dto.CourseDto;
import com.mycompany.myapp.domain.dto.CourseWithTNDto;
import com.mycompany.myapp.service.CourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping
@Api(value="Course Service Controller", description = "Controller for find couses information")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping(path = "/api/course/findAllCourses", produces = "application/json")
    public HttpEntity<List<CourseDto>> findAllCourses(){
        List<CourseDto> allCourses = courseService.findAllCourses();

        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

    @GetMapping(path = "/api/course/findAllCoursesDto", produces = "application/json")
    public HttpEntity<List<CourseDto>> findAllCoursesDto(){
        List<CourseDto> allCourses = courseService.findAllCoursesDtoFromDB();

        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

    @GetMapping(path = "/api/course/findAllCoursesWithTNDto", produces = "application/json")
    public HttpEntity<List<CourseWithTNDto>> findAllCoursesWithTNDto(){
        List<CourseWithTNDto> allCourses = courseService.findAllCoursesDtoWithTeacherNameFromDB();

        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

    @PostMapping(path = "/api/course/registerCourse/{courseName}", produces = "application/json")
    public HttpStatus registerCourse(@PathVariable String courseName) {
        try {
            courseService.registerCourse(courseName);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.UNPROCESSABLE_ENTITY;
        }
    }

    @PostMapping(path = "/api/course/addCourse", produces = "application/json")
    public HttpStatus addCourse(@RequestBody @NotNull CourseDto course){
        try {
            courseService.addCourse(course);
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
