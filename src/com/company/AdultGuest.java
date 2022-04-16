package com.company;

public class AdultGuest implements Guest{
    private Discount discount = new HotelDiscount(DiscountTypes.NoDiscount);

    public double getDiscount() {
        return this.discount.getPercentageDiscount();
    }
}
