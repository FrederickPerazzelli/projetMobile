package com.example.projetmobile;

import java.util.Date;

public class Answer {

    private int _demandId;
    private int _userId;
    private String _publishDate;
    private String _answer;

    // constructor
    public Answer() {
        super();
    }

    // constructor
    public Answer(int id, int demandId, int userId, String publishDate, String answer){
        super();
        _demandId = demandId;
        _userId = userId;
        _publishDate = publishDate;
        _answer = answer;
    }


    //  demand ID
    private int get_demandId(){ return _demandId;}
    private void set_demandId(int demandId){ _demandId = demandId;}

    // user
    private int get_userId(){ return _userId;}
    private void set_userId(int userId) { _userId = userId; }

    // pubish date
    private String get_publishDate(){ return _publishDate; }
    private void set_publishDate(String publishDate ){ _publishDate = publishDate; }

    // answer
    private String get_answer(){ return _answer; }
    private void set_answer(String answer){ _answer = answer; }


}
