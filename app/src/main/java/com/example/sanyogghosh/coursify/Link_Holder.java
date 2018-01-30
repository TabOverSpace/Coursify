package com.example.sanyogghosh.coursify;


/**
 * Created by Sanyog Ghosh on 19-07-2017.
 */
public class Link_Holder {

    public String site;
    public String udemy;
    public String udacity;
    public String coursera;
    public String edX;


    public Link_Holder(String site, String udemy, String udacity, String coursera, String edX)
    {
        this.site=site;
        this.udemy=udemy;
        this.udacity=udacity;
        this.coursera=coursera;
        this.edX=edX;
    }

    public String  getSite() {
        return site;
    }
    public String  getUdemy() {
        return udemy;
    }
    public String  getUdacity() {
        return udacity;
    }
    public String  getCoursera() {
        return coursera;
    }
    public String  getEdX() {
        return edX;
    }
}
