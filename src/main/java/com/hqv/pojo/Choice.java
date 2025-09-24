/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hqv.pojo;

/**
 *
 * @author huynh
 */
public class Choice {
    private int id;
    private String content;
    private boolean correct;
    
    
    //2 constuctors
    //ghi xuong du lieu không cần id
    public Choice(String content, boolean correct) {
        this.content = content;
        this.correct = correct;
    }
    
    //doc du lieu len cần id
    public Choice(int id, String content, boolean correct) {
        this.id = id;
        this.content = content;
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    
       
    
}
