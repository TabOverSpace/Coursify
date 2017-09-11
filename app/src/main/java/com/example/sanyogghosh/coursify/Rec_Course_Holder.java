package com.example.sanyogghosh.coursify;


/**
 * Created by Sanyog Ghosh on 19-07-2017.
 */
public class Rec_Course_Holder {
    public String course;
    public String rating;
    public String site;
    public String price;
    public String link;
    public String info;


    public Rec_Course_Holder(String course, String rating, String site, String price, String link,String info)
    {
        this.course=course;
        this.rating=rating;
        this.info=info;
        this.site=site;
        this.price=price;
        this.link=link;


    }

    /**
     * Get the name of the Android version
     */
    public String     course() {
        return course;
    }
    public String     rating() {
        return rating;
    }
    public String     site() {
        return site;
    }
    public String     price() {
        return price;
    }
    public String     info() {
        return info;
    }

}
