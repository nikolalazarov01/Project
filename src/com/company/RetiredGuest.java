package com.company;

public class RetiredGuest implements Guest{
    private GuestTypes type = GuestTypes.Retired;
    //Percent discount for when a payment system is introduced
    private Discount discount = new HotelDiscount(DiscountTypes.ForRetired);

    @Override
    public double getDiscount() {
        return this.discount.getPercentageDiscount();
    }
    public String getType(){return this.type.toString();}
}
