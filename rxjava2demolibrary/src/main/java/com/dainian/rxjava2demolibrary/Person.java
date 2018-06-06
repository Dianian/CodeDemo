package com.dainian.rxjava2demolibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author By FuBowen
 * @date 2018/6/6 16:29
 */
public class Person {
    private String name;
    private List<Plan> planList = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }
}
