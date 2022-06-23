package com.janaldous.monopoly.core.space.factory;


import com.janaldous.monopoly.core.space.PropertyGroup;
import com.janaldous.monopoly.core.playeraction.PlayerActionFactory;
import com.janaldous.monopoly.core.space.*;

import java.util.Arrays;
import java.util.Map;

public class SpaceFactory
{
    private final PlayerActionFactory playerActionFactory;
    
    public SpaceFactory(PlayerActionFactory playerActionFactory) {
        this.playerActionFactory = playerActionFactory;
    }

    public Space createSpace(String spaceName) {
        switch (spaceName) {
            case "Go":
                return new GoSpace(playerActionFactory);
            case "IncomeTax":
                return new IncomeTaxSpace(playerActionFactory, "Income Tax", 200, 10);
            case "LuxuryTax":
                return new LuxuryTaxSpace(playerActionFactory, "Luxury Tax", 75);
            case "CommunityChest":
            case "Chance":
                return new PickCardSpace(playerActionFactory);
            case "Jail":
                return new JailSpace();
            case "FreeParking":
                return new DoNothingSpace("Free Parking");
            case "GoToJail":
                return new GoToJailSpace(playerActionFactory);
            default:
                throw new IllegalArgumentException("Do not recognize space name " + spaceName);
        }
    }
    
    public ResidentialSpace createResidence(String propertyName, int value, int houseValue, int hotelValue,
                                            int siteOnlyRent, int houseRent, int hotelRent, int mortgageValue, PropertyGroup colorGroup) {
        return new ResidentialSpace(propertyName, value, colorGroup, houseValue, hotelValue,
                    siteOnlyRent, houseRent, hotelRent, 
                    Arrays.asList(playerActionFactory.createPayRentAction()),
                    Arrays.asList(playerActionFactory.createBuyPropertyAction(), playerActionFactory.createSellPropertyAction(), playerActionFactory.createBuyHouseAction()), mortgageValue);
    }
    
    public UtilityCompanySpace createUtility(String propertyName, int value, int mortgageValue) {
        return new UtilityCompanySpace(propertyName, value,
            Arrays.asList(playerActionFactory.createPayRentAction()),
            Arrays.asList(playerActionFactory.createBuyPropertyAction(), playerActionFactory.createSellPropertyAction()), mortgageValue);
    }

    public RailroadSpace createRailroad(String propertyName, int value, int mortgageValue, Map<Integer, Integer> noOfPropertiesToRent) {
        return new RailroadSpace(propertyName, value,
                noOfPropertiesToRent,
                Arrays.asList(playerActionFactory.createPayRentAction()),
                Arrays.asList(playerActionFactory.createBuyPropertyAction(), playerActionFactory.createSellPropertyAction()), mortgageValue);
    }
}
