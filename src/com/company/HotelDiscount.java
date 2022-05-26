package com.company;

public class HotelDiscount implements Discount{
    private DiscountTypes type;

    //Discount for the different types of guests

    /**
     * Constructor for the hotel discount class
     * @param type (For children, for retired, no discount...)
     */
    public HotelDiscount(DiscountTypes type){
        this.type = type;
    }

    /**
     * Function that returns the discount
     * @return Returns the discount percentage as a double
     */
    @Override
    public double getPercentageDiscount() {
        if(type == DiscountTypes.ForClildren){
            return 0.1;
        }
        else if(type == DiscountTypes.ForRetired){
            return 0.05;
        }
        else{
            return 0;
        }
    }
}
