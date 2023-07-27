package com.example.project1task3;
import java.util.HashMap;
import java.util.Map;

public class ClickerModel {

    //to get the answer and track it's count
    //initialize
    Map<String,Integer> map = new HashMap<>();
    public Map<String,Integer> getCount(String answer){
        int count = 0;
        //add count
        if (map.containsKey(answer)) {
            count = map.get(answer) + 1;
        } else {
            count = 1;
        }
        //insert in map
        map.put(answer,count);
        return map;
    }

    //to clear the count
    public Map<String,Integer> clearCount(){
        //create new map
        map = new HashMap<String, Integer>() ;
        return map;
    }
}