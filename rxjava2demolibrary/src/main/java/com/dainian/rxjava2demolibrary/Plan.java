package com.dainian.rxjava2demolibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author By FuBowen
 * @date 2018/6/6 16:29
 */
public class Plan {
    private String time;
    private String content;
    private List<String> actionList = new ArrayList<>();



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getActionList() {
        return actionList;
    }

    public void setActionList(List<String> actionList) {
        this.actionList = actionList;
    }
}
