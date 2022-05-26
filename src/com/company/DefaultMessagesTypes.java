package com.company;

public enum DefaultMessagesTypes {

    NoRoom("No suitable room is available at the moment! Sorry"),
    DataFromFileLoaded("The data from your file has been loaded!"),
    NoSuchFile("There is no such file in the given directory!");

    public final String label;

    /**
     * Constructor for the default messages enum
     * @param label The short description for the needed default message
     */
    private DefaultMessagesTypes(String label){
        this.label = label;
    }

}
