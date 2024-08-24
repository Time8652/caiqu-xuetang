package com.example.aidraw.News;

import java.net.URL;

public class TopicNew {

    public URL head_url, work_url;
    public String name, time, text;
    public int star;

    public TopicNew(URL head_url, String name, String time, String text, URL work_url, int star) {
        this.head_url = head_url;
        this.name = name;
        this.time = time;
        this.text = text;
        this.work_url = work_url;
        this.star = star;
    }
}
