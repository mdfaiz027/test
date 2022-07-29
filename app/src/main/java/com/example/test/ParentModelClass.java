package com.example.test;

import android.net.Uri;

import java.util.List;

public class ParentModelClass {

    String title;
    List<Uri> childModelClassList;

    public ParentModelClass(String title, List<Uri> childModelClassList) {
        this.title = title;
        this.childModelClassList = childModelClassList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Uri> getChildModelClassList() {
        return childModelClassList;
    }

    public void setChildModelClassList(List<Uri> childModelClassList) {
        this.childModelClassList = childModelClassList;
    }
}
