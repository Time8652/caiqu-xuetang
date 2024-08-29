package com.example.aidraw.News;

public class TopicNew {

    public String head_url, work_url;
    public String name, time, text;
    public int star, check;

    public TopicNew(String head_url, String name, String time, String text, String work_url, int star, int check) {
        this.head_url = head_url;
        this.name = name;
        this.time = time;
        this.text = text;
        this.work_url = work_url;
        this.star = star;
        this.check = check;
    }
}
