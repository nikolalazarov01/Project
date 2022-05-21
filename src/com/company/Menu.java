package com.company;

public class Menu implements MenuInterface{

    //Displays the given menu from the enumerable
    @Override
    public void Display(MenuTypes type) {
        System.out.println(type.label);
    }
}
