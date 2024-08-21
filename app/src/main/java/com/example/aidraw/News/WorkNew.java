package com.example.aidraw.News;

import java.net.URL;

public class WorkNew {

    public URL work_url;
    public String work_title, work_like, work_time;

    public WorkNew(URL work_url, String work_title, String work_like, String work_time) {
        this.work_url = work_url;
        this.work_title = work_title;
        this.work_like = work_like;
        this.work_time = work_time;
    }
}
