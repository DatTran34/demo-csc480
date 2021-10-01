package edu.oswego.rest.controller.course;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import edu.oswego.rest.objects.Course;
import edu.oswego.rest.service.ICourseService;
import edu.oswego.rest.service.impl.CourseService;

import javax.ws.rs.*;
import java.util.List;

@Path("/course")
public class CourseAPI {
    private static final long serialVersionUID = 1L;
    private ICourseService courseService;
    private Gson gson = new Gson();

    public CourseAPI() {
        courseService = new CourseService();
    }

    @GET
    public String getAllCourses(){
        //TODO This method needs to ensure authentication
        try {
            List<Course> listOfCourses = courseService.findAll();
            String res = gson.toJson(listOfCourses);
            if(listOfCourses != null) return res;
            else return "There are not any courses in the database";
        } catch (NumberFormatException ne){
            System.out.println("There are not any courses in the database");
        }
        return null;
    }

    @GET
    @Path("/{courseId}")
    public String getSpecificCourse(@PathParam("courseId") String _courseId){
        //TODO This method needs to ensure authentication
        try {

            int courseId = Integer.parseInt(_courseId);
            Course course = courseService.findOne(courseId);
            String res = gson.toJson(course);
            if(course != null) return res;
            else return "Course ID provided was not formatted properly.";
        } catch (NumberFormatException ne){
            System.out.println("Course ID provided was not formatted properly.");
        }
        return null;
    }
    @POST
    public String postCourse(String payload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Course course = gson.fromJson(payload, Course.class);
        courseService.save(course);
        String res = gson.toJson(course);
        return res;
    }

    @PUT
    public String updateCourse(String payload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Course course = gson.fromJson(payload, Course.class);
        course = courseService.update(course);
        String res = gson.toJson(course);
        return res;
    }

    @DELETE
    @Path("/{courseId}")
    public String deleteSpecificCourse(@PathParam("courseId") String _courseId){
        //TODO This method needs to ensure authentication
        try {

            int courseId = Integer.parseInt(_courseId);
            Course course = courseService.findOne(courseId);
            course = courseService.delete(course);
            String res = gson.toJson(course);
            if(course != null) return res;
            else return "Course ID provided was not formatted properly.";
        } catch (NumberFormatException ne){
            System.out.println("Course ID provided was not formatted properly.");
        }
        return null;
    }
}