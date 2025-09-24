/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hqv.utils.theme;

import com.hqv.singlet.App;

/**
 *
 * @author huynh
 */
public class DefaultFactory implements ThemeFactory{
    @Override
    public String getStyleSheet() {
        return App.class.getResource("style.css").toExternalForm();
    }
}
