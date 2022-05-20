package com.company;

public class Menu implements MenuInterface{

    @Override
    public void Display(MenuTypes type) {
        System.out.println(type.label);
    }
}
