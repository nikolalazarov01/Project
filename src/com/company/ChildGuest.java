package com.company;

public class ChildGuest implements Guest{
    private Discount discount = new HotelDiscount(DiscountTypes.ForClildren);

    @Override
    public double getDiscount() {
        return this.discount.getPercentageDiscount();
    }
}
