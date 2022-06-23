package com.janaldous.monopoly.versions.original;

import com.janaldous.monopoly.core.space.PropertyGroup;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.space.factory.SpaceFactory;

import java.util.HashMap;
import java.util.Map;

public class OriginalGameboardFactory {

    private SpaceFactory spaceFactory;

    public OriginalGameboardFactory(SpaceFactory spaceFactory) {
        this.spaceFactory = spaceFactory;
    }

    /**
     * Railroad properties to rent
     */
    Map<Integer, Integer> railroadRentSchedule = new HashMap<>() {{
        put(1, 25);
        put(2, 50);
        put(3, 100);
        put(4, 200);
    }};

    public Space createSpace(String propertySpace) {
        switch (propertySpace) {
            case "Mediterranean Ave":
                return spaceFactory.createResidence("Mediterranean Ave", 60, 50, 50, 2, 10, 250, 30, PropertyGroup.PURPLE);
            case "Community Chest":
                return spaceFactory.createSpace("CommunityChest");
            case "Baltic Ave":
                return spaceFactory.createResidence("Baltic Ave", 60, 50, 50, 4, 20, 250, 30, PropertyGroup.PURPLE);
            case "Income Tax":
                return spaceFactory.createSpace("IncomeTax");
            case "Luxury Tax":
                return spaceFactory.createSpace("LuxuryTax");
            case "Reading Railroad":
                return spaceFactory.createRailroad("Reading Railroad", 200, 100, railroadRentSchedule);
            case "Pennsylvania Railroad":
                return spaceFactory.createRailroad("Pennsylvania Railroad", 200, 100, railroadRentSchedule);
            case "B & O Railroad":
                return spaceFactory.createRailroad("B & O Railroad", 200, 100, railroadRentSchedule);
            case "Short Line":
                return spaceFactory.createRailroad("Short Line", 200, 100, railroadRentSchedule);
            case "Oriental Ave":
                return spaceFactory.createResidence("Oriental Ave", 100, 50, 50, 6, 30, 550, 50, PropertyGroup.TURQUOISE);
            case "Chance":
                return spaceFactory.createSpace("Chance");
            case "Vermont Ave":
                return spaceFactory.createResidence("Vermont Ave", 100, 50, 50, 6, 30, 550, 50, PropertyGroup.TURQUOISE);
            case "Connecticut Ave":
                return spaceFactory.createResidence("Connecticut Ave", 120, 50, 50, 8, 40, 600, 60, PropertyGroup.TURQUOISE);
            case "Jail":
                return spaceFactory.createSpace("Jail");
            case "St Charles Pl":
                return spaceFactory.createResidence("St Charles Place", 140, 100, 100, 10, 50, 750, 70, PropertyGroup.PINK);
            case "Electric Company":
                return spaceFactory.createUtility("Electric Company", 150, 75);
            case "Water Company":
                return spaceFactory.createUtility("Water Company", 150, 75);
            case "States Ave":
                return spaceFactory.createResidence("States Ave", 140, 100, 100, 10, 50, 750, 70, PropertyGroup.PINK);
            case "Virginia Ave":
                return spaceFactory.createResidence("Virginia Ave", 160, 100, 100, 12, 60, 900, 80, PropertyGroup.PINK);
            case "St James Pl":
                return spaceFactory.createResidence("St James Pl", 180, 100, 100, 14, 70, 950, 90, PropertyGroup.ORANGE);
            case "Tennessee Ave":
                return spaceFactory.createResidence("Tennessee Ave", 180, 100, 100, 14, 70, 950, 90, PropertyGroup.ORANGE);
            case "New York Ave":
                return spaceFactory.createResidence("New York Ave", 200, 100, 100, 16, 80, 1000, 100, PropertyGroup.ORANGE);
            case "Free Parking":
                return spaceFactory.createSpace("FreeParking");
            case "Kentucky Ave":
                return spaceFactory.createResidence("Kentucky Ave", 220, 150, 150, 18, 90, 1050, 110, PropertyGroup.RED);
            case "Indiana Ave":
                return spaceFactory.createResidence("Indiana Ave", 220, 150, 150, 18, 90, 1050, 110, PropertyGroup.RED);
            case "Illinois Ave":
                return spaceFactory.createResidence("Illinois Ave", 240, 150, 150, 20, 100, 1100, 120, PropertyGroup.RED);
            case "Atlantic Ave":
                return spaceFactory.createResidence("Atlantic Ave", 260, 150, 150, 22, 110, 1150, 130, PropertyGroup.YELLOW);
            case "Ventnor Ave":
                return spaceFactory.createResidence("Ventnor Ave", 260, 150, 150, 22, 110, 1150, 130, PropertyGroup.YELLOW);
            case "Marvin Gardens":
                return spaceFactory.createResidence("Marvin Gardens", 260, 150, 150, 24, 120, 1200, 130, PropertyGroup.YELLOW);
            case "Pacific Ave":
                return spaceFactory.createResidence("Pacific Ave", 300, 200, 200, 26, 130, 1275, 150, PropertyGroup.GREEN);
            case "North Carolina Ave":
                return spaceFactory.createResidence("North Carolina Ave", 300, 200, 200, 26, 130, 1275, 150, PropertyGroup.GREEN);
            case "Pennsylvania Ave":
                return spaceFactory.createResidence("Pennsylvania Ave", 320, 200, 200, 28, 150, 1400, 160, PropertyGroup.GREEN);
            case "Park Pl":
                return spaceFactory.createResidence("Park Pl", 350, 200, 200, 35, 175, 1500, 175, PropertyGroup.BLUE);
            case "Boardwalk":
                return spaceFactory.createResidence("Boardwalk", 400, 200, 200, 50, 200, 2000, 200, PropertyGroup.BLUE);
            case "Go To Jail":
                return spaceFactory.createSpace("GoToJail");
            case "Go":
                return spaceFactory.createSpace("Go");
            default:
                throw new IllegalArgumentException("cannot recognize " + propertySpace);
        }
    }

    public Space[] createSpaces() {
        return new Space[]{
                createSpace("Go"),
                createSpace("Mediterranean Ave"),
                createSpace("Community Chest"),
                createSpace("Baltic Ave"),
                createSpace("Income Tax"),
                createSpace("Reading Railroad"),
                createSpace("Oriental Ave"),
                createSpace("Chance"),
                createSpace("Vermont Ave"),
                createSpace("Connecticut Ave"),
                createSpace("Jail"),
                createSpace("St Charles Pl"),
                createSpace("Electric Company"),
                createSpace("States Ave"),
                createSpace("Virginia Ave"),
                createSpace("Pennsylvania Ave"),
                createSpace("St James Pl"),
                createSpace("Community Chest"),
                createSpace("Tennessee Ave"),
                createSpace("New York Ave"),
                createSpace("Free Parking"),
                createSpace("Kentucky Ave"),
                createSpace("Chance"),
                createSpace("Indiana Ave"),
                createSpace("Illinois Ave"),
                createSpace("B & O Railroad"),
                createSpace("Atlantic Ave"),
                createSpace("Ventnor Ave"),
                createSpace("Marvin Gardens"),
                createSpace("Go To Jail"),
                createSpace("Pacific Ave"),
                createSpace("North Carolina Ave"),
                createSpace("Community Chest"),
                createSpace("Pennsylvania Ave"),
                createSpace("Short Line"),
                createSpace("Chance"),
                createSpace("Park Pl"),
                createSpace("Luxury Tax"),
                createSpace("Boardwalk"),
        };
    }

}
