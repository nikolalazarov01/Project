package com.company;

public class Menu implements MenuInterface{

    /**
     * Function that displays given menu
     * @param type Type of the menu being displayed
     */
    @Override
    public void Display(MenuTypes type) {
        System.out.println(type.label);
    }
}
