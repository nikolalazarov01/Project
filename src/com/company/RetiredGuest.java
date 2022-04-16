package com.company;

public class RetiredGuest implements Guest{
    private Discount discount = new HotelDiscount(DiscountTypes.ForRetired);

    @Override
    public double getDiscount() {
        return this.discount.getPercentageDiscount();
    }
}
