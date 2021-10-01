package edu.oswego.rest.controller.answer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import edu.oswego.rest.objects.Answer;
import edu.oswego.rest.service.IAnswerService;
import edu.oswego.rest.service.impl.AnswerService;

import javax.ws.rs.*;
import java.util.List;

@Path("/answer")
public class AnswerAPI {
    private static final long serialVersionUID = 1L;
    private IAnswerService answerService;
    private Gson gson = new Gson();

    public AnswerAPI() {
        answerService = new AnswerService();
    }

    @GET
    public String getAllAnswers(){
        //TODO This method needs to ensure authentication
        try {
            List<Answer> listOfAnswers = answerService.findAll();
            String res = gson.toJson(listOfAnswers);
            if(listOfAnswers != null) return res;
            else return "There are not any answers in the database.";
        } catch (NumberFormatException ne){
            System.out.println("There are not any answers in the database.");
        }
        return null;
    }

    @GET
    @Path("/{answerId}")
    public String getSpecificAnswer(@PathParam("answerId") String _answerId){
        //TODO This method needs to ensure authentication
        try {

            int answerId = Integer.parseInt(_answerId);
            Answer answer = answerService.findOne(answerId);
            String res = gson.toJson(answer);
            if(answer != null) return res;
            else return "Answer ID provided was not formatted properly.";
        } catch (NumberFormatException ne){
            System.out.println("Answer ID provided was not formatted properly.");
        }
        return null;
    }
    @POST
    public String postAnswer(String payload) throws JsonProcessingException {
        Answer answer = gson.fromJson(payload, Answer.class);
        answer = answerService.save(answer);
        String res = gson.toJson(answer);
        return res;
    }

    @PUT
    public String updateAnswer(String payload) throws JsonProcessingException {
        Answer answer = gson.fromJson(payload, Answer.class);
        answer = answerService.update(answer);
        String res = gson.toJson(answer);
        return res;
    }

    @DELETE
    @Path("/{answerId}")
    public String deleteSpecificAnswer(@PathParam("answerId") String _answerId){
        //TODO This method needs to ensure authentication
        try {

            int answerId = Integer.parseInt(_answerId);
            Answer answer = answerService.findOne(answerId);
            answer = answerService.delete(answer);
            String res = gson.toJson(answer);
            if(answer != null) return res;
            else return "Answer ID provided was not formatted properly.";
        } catch (NumberFormatException ne){
            System.out.println("Answer ID provided was not formatted properly.");
        }
        return null;
    }
}
