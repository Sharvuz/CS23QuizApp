/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hqv.utils.theme;

import com.hqv.singlet.App;
import javafx.scene.Scene;

/**
 *
 * @author huynh
 */
public class ThemeManager {

    private static ThemeFactory themeFactory = new DefaultFactory();

    //refactor cho 1 cai setter de doi theme
    /**
     * @param aThemeFactory the themeFactory to set
     */
    public static void setThemeFactory(ThemeFactory aThemeFactory) {
        themeFactory = aThemeFactory;
    }

    //dung chung
    public static void applyTheme(Scene scene) {
        scene.getRoot().getStylesheets().clear();
        scene.getRoot().getStylesheets().add(themeFactory.getStyleSheet());
    }
}
