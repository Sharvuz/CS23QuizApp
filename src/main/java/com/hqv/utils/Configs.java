/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hqv.utils;

import com.hqv.services.CategoryServices;
import com.hqv.services.LevelServices;
import com.hqv.services.QuestionServices;

/**
 *
 * @author huynh
 */
public class Configs {

    public static final LevelServices levelServices = new LevelServices();
    public static final QuestionServices questionServices = new QuestionServices();
    //chi can khoi tao 1 lan
    public static final CategoryServices cateServices = new CategoryServices();
    
}
