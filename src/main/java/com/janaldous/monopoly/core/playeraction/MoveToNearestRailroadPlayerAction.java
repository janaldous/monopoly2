package com.janaldous.monopoly.core.playeraction;

import com.janaldous.monopoly.core.*;
import com.janaldous.monopoly.core.dice.Dice;
import com.janaldous.monopoly.core.exception.NotEnoughMoneyException;
import com.janaldous.monopoly.core.exception.PlayerActionException;
import com.janaldous.monopoly.core.space.PropertySpace;

import java.util.Optional;

/**
 * Advance token to the nearest Utility. If unowned, you may buy it from the Bank.
 *
 * If owned, throw dice and pay owner a total 10 (ten) times the amount thrown.
 */
public class MoveToNearestRailroadPlayerAction implements PlayerAction {
    private GameContext context;
    private PlayerAction buyProperty;

    public MoveToNearestRailroadPlayerAction(GameContext context, PlayerAction buyProperty) {
        this.context = context;
        this.buyProperty = buyProperty;
    }

    @Override
    public Optional<PlayerAction> act(Player player) throws PlayerActionException {
        Gameboard gameboard = context.getGameboard();
        Token token = context.getPlayerToken(player);
        int steps = gameboard.getProperties().get(PropertyGroup.RAILROAD).stream()
                .mapToInt(r -> gameboard.getPositionBySpaceName(r.getName()) - gameboard.getPosition(token))
                .min()
                .orElseThrow(() -> new RuntimeException("Something went wrong in calculating nearest railroad"));
        PropertySpace nearestUtility = (PropertySpace) gameboard.move(token, steps);

        if (nearestUtility.getOwner() != null) {
            return Optional.of(buyProperty);
        } else {
            int rent = nearestUtility.getRent();
            nearestUtility.getOwner().addMoney(rent);
            try {
                player.pay(rent);
            } catch (NotEnoughMoneyException e) {
                throw new PlayerActionException(e);
            }
            return Optional.empty();
        }
    }

    @Override
    public String getName() {
        return null;
    }
}
