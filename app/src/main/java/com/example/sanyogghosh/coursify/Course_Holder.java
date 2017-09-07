package com.example.sanyogghosh.coursify;


/**
 * Created by Sanyog Ghosh on 19-07-2017.
 */
public class Course_Holder {
    public String course;
    public String provider;
    public String site;
    public String level;
    public String url;


    public Course_Holder(String course, String provider,String site,String level,String url)
    {
        this.course=course;
        this.provider=provider;
        this.site=site;
        this.level=level;
        this.url=url;


    }

    /**
     * Get the name of the Android version
     */
    public String  course() {
        return course;
    }
    public String  provider() {
        return provider;
    }
    public String  site() {
        return site;
    }
    public String  level() {
        return level;
    }
    public String     url() {
        return url;
    }

}
