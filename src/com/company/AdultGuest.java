package com.company;

public class AdultGuest implements Guest{
    private GuestTypes type = GuestTypes.Adult;
    //Percent discount for when a payment system is introduced
    private Discount discount = new HotelDiscount(DiscountTypes.NoDiscount);

    public double getDiscount() {
        return this.discount.getPercentageDiscount();
    }
    public String getType() {return this.type.toString();}
}
