package com.example.sanyogghosh.coursify;


/**
 * Created by Sanyog Ghosh on 19-07-2017.
 */
public class Link_Holder {
    public String course;
    public String provider;
    public String site;
    public String level;
    public String url;
    public String url_fi;
    public String img_url;



    public Link_Holder(String course, String provider, String site, String level, String url, String url_fi, String img_url)
    {
        this.course=course;
        this.provider=provider;
        this.url_fi=url_fi;
        this.site=site;
        this.level=level;
        this.url=url;
        this.img_url=img_url;


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
    public String  img_url(){return img_url;}
    public String     url_fi() {
        return url_fi;
    }


}
