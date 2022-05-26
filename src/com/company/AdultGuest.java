package com.company;

public class AdultGuest implements Guest{

    private GuestTypes type = GuestTypes.Adult;
    //Percent discount for when a payment system is introduced
    private Discount discount = new HotelDiscount(DiscountTypes.NoDiscount);

    /**
     * Function that calls the method from the discount interface, that calculates the discount
     * @return Returns the discount percentage for the current guest type
     */
    public double getDiscount() {
        return this.discount.getPercentageDiscount();
    }

    /**
     * Function returning the type in String
     * @return Returns the guest type
     */
    public String getType() {return this.type.toString();}
}
