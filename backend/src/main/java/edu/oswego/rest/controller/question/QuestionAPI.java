package edu.oswego.rest.controller.question;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import edu.oswego.rest.objects.Assignment;
import edu.oswego.rest.objects.Question;
import edu.oswego.rest.service.IAssignmentService;
import edu.oswego.rest.service.IQuestionService;
import edu.oswego.rest.service.impl.AssignmentService;
import edu.oswego.rest.service.impl.QuestionService;

import javax.ws.rs.*;
import java.util.List;

@Path("/question")
public class QuestionAPI {
    private static final long serialVersionUID = 1L;
    private IQuestionService questionService;
    private Gson gson = new Gson();

    public QuestionAPI() {
        questionService = new QuestionService();
    }

    @GET
    public String getAllQuestions(){
        //TODO This method needs to ensure authentication
        try {
            List<Question> listOfQuestions = questionService.findAll();
            String res = gson.toJson(listOfQuestions);
            if(listOfQuestions != null) return res;
            else return "There are not any questions in the database.";
        } catch (NumberFormatException ne){
            System.out.println("There are not any questions in the database");
        }
        return null;
    }

    @GET
    @Path("/{questionId}")
    public String getSpecificAssignment(@PathParam("questionId") String _questionId){
        //TODO This method needs to ensure authentication
        try {

            int questionId = Integer.parseInt(_questionId);
            Question question = questionService.findOne(questionId);
            String res = gson.toJson(question);
            if(question != null) return res;
            else return "Question ID provided was not formatted properly.";
        } catch (NumberFormatException ne){
            System.out.println("Question ID provided was not formatted properly.");
        }
        return null;
    }
    @POST
    public String postQuestion(String payload) throws JsonProcessingException {
        Question question = gson.fromJson(payload, Question.class);
        question = questionService.save(question);
        String res = gson.toJson(question);
        return res;
    }

    @PUT
    public String updateQuestion(String payload) throws JsonProcessingException {
        Question question = gson.fromJson(payload, Question.class);
        question = questionService.update(question);
        String res = gson.toJson(question);
        return res;
    }

    @DELETE
    @Path("/{questionId}")
    public String deleteSpecificQuestion(@PathParam("questionId") String _questionId){
        //TODO This method needs to ensure authentication
        try {

            int questionId = Integer.parseInt(_questionId);
            Question question = questionService.findOne(questionId);
            question = questionService.delete(question);
            String res = gson.toJson(question);
            if(question != null) return res;
            else return "Question ID provided was not formatted properly.";
        } catch (NumberFormatException ne){
            System.out.println("Question ID provided was not formatted properly.");
        }
        return null;
    }
}
