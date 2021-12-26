package com.janaldous.monopoly.core.space.rentstrategy;


import com.janaldous.monopoly.core.space.ResidentialSpace;

/**
 * Write a description of class SetColorGroupState here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ResidentialPropertyGroupRentStrategy extends NormalResidentialRentStrategy {    
    public ResidentialPropertyGroupRentStrategy(ResidentialSpace property) {
        super(property);
    }

    @Override
    public int calculateRent() {
        // double on undeveloped properties
        if (property.getHouseQty() == 0) {
            return _calculateRent() * 2;
        }
        return _calculateRent();
    }
}
