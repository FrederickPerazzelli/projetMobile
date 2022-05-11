package com.example.projetmobile;

import java.io.Serializable;
import java.util.Date;

public class Demand implements Serializable{

    private String _title;
    private String _subject;
    private int _category;
    private Date _publishDate;
    private String _comments;
    private int _user;
    private int _status;

    // constructor
    public Demand() {
        super();
    }

    // constructor
    public Demand(int id, String title, String subject, int category, Date publishDate, String comments, int user, int status){
        super();
        _title = title;
        _subject = subject;
        _category = category;
        _publishDate = publishDate;
        _comments = comments;
        _user = user;
        _status = status;
    }

    // title
    public String get_title(){ return _title; }
    public void set_title(String title){ _title = title;}

    // subject
    public String get_subject(){ return _subject;}
    public void set_subject(String subject){ _subject = subject;}

    // category
    public int get_category(){ return _category; }
    public void set_category(int category){ _category = category; }

    // publish date
    public Date get_publishDate(){ return _publishDate; }
    public void set_publishDate(Date publishDate) { _publishDate = publishDate; }

    // comments
    public String get_comments(){ return _comments; }
    public void set_comments(String comments){ _comments = comments; }

    // user
    public int get_user(){ return _user; }
    public void set_user(int user) { _user = user; }

    // status
    public int get_status(){ return _status; }
    public void set_status(int status) { _status = status; }

    // serialization
    public void serializeJsonDemand(Demand newDemand){

    }
}
