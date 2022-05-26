package com.company;

public class RetiredGuest implements Guest{
    private GuestTypes type = GuestTypes.Retired;
    //Percent discount for when a payment system is introduced
    private Discount discount = new HotelDiscount(DiscountTypes.ForRetired);

    /**
     * Function that calls the discount interface's method to calculate the percentage
     * @return Returns the discount in double
     */
    @Override
    public double getDiscount() {
        return this.discount.getPercentageDiscount();
    }

    /**
     * Function that returns the type of guest
     * @return Returns the type of guest
     */
    public String getType(){return this.type.toString();}
}
