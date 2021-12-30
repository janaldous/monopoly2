package com.janaldous.monopoly.core.space.rentstrategy;


import com.janaldous.monopoly.core.space.ResidentialSpace;

/**
 * Write a description of class NormalPropertyState here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NormalResidentialRentStrategy implements RentStrategy
{
    protected final ResidentialSpace property;
    
    public NormalResidentialRentStrategy(ResidentialSpace property) {
        this.property = property;
    }
    
    @Override
    public int calculateRent() {
        return _calculateRent();
    }
    
    protected int _calculateRent() {
        if (property.getHouseQty() == 0) {
            return property.getSiteOnlyRent();
        }
        return (property.getHouseRent()) + (property.getHotelRent());
    }
}
