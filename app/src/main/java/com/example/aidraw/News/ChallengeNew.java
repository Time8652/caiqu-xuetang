package com.example.aidraw.News;

import java.net.URL;

public class ChallengeNew {

    public URL head_url, work_url;
    public String name, time;

    public ChallengeNew(URL head_url, String name, String time, URL work_url) {
        this.head_url = head_url;
        this.name = name;
        this.time = time;
        this.work_url = work_url;
    }
}
