package com.company;

public class ChildGuest implements Guest{
    private GuestTypes type = GuestTypes.Child;
    //Percent discount for when a payment system is introduced
    private Discount discount = new HotelDiscount(DiscountTypes.ForClildren);

    /**
     * Function that calls the method from the discount interface, that calculates the discount
     * @return Returns the discount of the current guest type
     */
    @Override
    public double getDiscount() {
        return this.discount.getPercentageDiscount();
    }

    /**
     * Function returning the guest type in String
     * @return Returns the guest type
     */
    public String getType() {return this.type.toString();}
}
