package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.bank.MortgageEligibilityChecker;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.gameboard.Gameboard;
import com.janaldous.monopoly.core.gamecontext.GameContext;
import com.janaldous.monopoly.core.player.Player;
import com.janaldous.monopoly.core.space.PropertySpace;
import com.janaldous.monopoly.core.space.ResidentialSpace;
import com.janaldous.monopoly.core.space.Space;
import com.janaldous.monopoly.core.space.rentstrategy.NormalResidentialRentStrategy;
import com.janaldous.monopoly.core.token.Token;

import java.util.List;
import java.util.Optional;

public class MortgagePropertyPlayerAction implements PlayerAction {

    private final GameContext context;
    private final MortgageEligibilityChecker mortgageEligibilityChecker;
    private final PropertySpace propertyToMortgage;

    public MortgagePropertyPlayerAction(GameContext context,
                                        PropertySpace propertyToMortgage,
                                        MortgageEligibilityChecker mortgageEligibilityChecker) {
        this.context = context;
        this.mortgageEligibilityChecker = mortgageEligibilityChecker;
        this.propertyToMortgage = propertyToMortgage;
    }

    @Override
    public Optional<List<PlayerAction>> act(Player player) throws PlayerActionException {
        Gameboard gameboard = context.getGameboard();

        // sell group properties, if there are
        List<PropertySpace> playerPropertiesInGroup = player.getPropertiesByPropertyGroup().get(propertyToMortgage.getPropertyGroup());
        boolean playerOwnsPropertyGroup = playerPropertiesInGroup.size() == gameboard.getPropertySetSize(propertyToMortgage.getPropertyGroup());
        if (playerOwnsPropertyGroup) {
            playerPropertiesInGroup.forEach(this::sellBuildings);
        }

        // mortgage property
        propertyToMortgage.mortgage();

        return Optional.empty();
    }

    private void sellBuildings(PropertySpace p) {
        if (p instanceof ResidentialSpace residentialSpace) {
            residentialSpace.removeAllHouses();
        }
    }

    @Override
    public boolean isValidAction(Player player) {
        Token token = context.getPlayerToken(player);
        Gameboard gameboard = context.getGameboard();
        Space space = gameboard.getSpace(token);
        if (space instanceof PropertySpace property && player.equals(property.getOwner())) {
            return !mortgageEligibilityChecker.getEligibleProperties(player.getPropertiesByPropertyGroup()).isEmpty();
        }
        return false;
    }
    
    @Override
    public String getName() {
        return "Mortgage Property <" + propertyToMortgage.getName() + ">";
    }

    @Override
    public String toString() {
        return getName();
    }
}
