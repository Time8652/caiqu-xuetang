package com.example.aidraw.News;

import com.example.aidraw.IntegralActivity;

import java.net.URL;

public class IntegralNew {

    public URL url;
    public String rank, name, score;

    public IntegralNew(URL url, String rank, String name, String score) {
        this.url = url;
        this.rank = rank;
        this.name = name;
        this.score = score;
    }
}
