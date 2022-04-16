package com.company;

public class HotelDiscount implements Discount{
    private DiscountTypes type;

    public HotelDiscount(DiscountTypes type){
        this.type = type;
    }

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
