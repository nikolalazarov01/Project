package com.company;

public class ChildGuest implements Guest{
    private GuestTypes type = GuestTypes.Child;
    //Percent discount for when a payment system is introduced
    private Discount discount = new HotelDiscount(DiscountTypes.ForClildren);

    @Override
    public double getDiscount() {
        return this.discount.getPercentageDiscount();
    }
    public String getType() {return this.type.toString();}
}
