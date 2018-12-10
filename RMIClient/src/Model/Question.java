/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author The
 */
public class Question implements Serializable{
    private int idQuest;
    private String Question;
    private String Answer1;
    private String Answer2;
    private String TrueAnswer;

    public int getIdQuest() {
        return idQuest;
    }

    public String getQuestion() {
        return Question;
    }

    public Question(int idQuest, String Question, String Answer1, String Answer2, String Answer3, String TrueAnswer, int Level) {
        this.idQuest = idQuest;
        this.Question = Question;
        this.Answer1 = Answer1;
        this.Answer2 = Answer2;
        this.TrueAnswer = TrueAnswer;
    }

    public Question() {
    }
    
    
    
    public void setIdQuest(int idQuest) {
        this.idQuest = idQuest;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public void setAnswer1(String Answer1) {
        this.Answer1 = Answer1;
    }

    public void setAnswer2(String Answer2) {
        this.Answer2 = Answer2;
    }

    public void setTrueAnswer(String TrueAnswer) {
        this.TrueAnswer = TrueAnswer;
    }

    public String getAnswer1() {
        return Answer1;
    }

    public String getAnswer2() {
        return Answer2;
    }

    public String getTrueAnswer() {
        return TrueAnswer;
    }
}
