package com.company;

public enum DefaultMessagesTypes {
    NoRoom("No suitable room is available at the moment! Sorry"),
    DataFromFileLoaded("The data from your file has been loaded!"),
    NoSuchFile("There is no such file in the given directory!");

    public final String label;

    private DefaultMessagesTypes(String label){
        this.label = label;
    }

}
