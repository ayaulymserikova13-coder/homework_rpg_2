package com.narxoz.rpg.combat.abilities;

import com.narxoz.rpg.combat.Ability;

public class Vanish implements Ability {

    @Override
    public String getName() {
        return "Vanish";
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Becomes invisible briefly, avoiding attacks and repositioning.";
    }

    @Override
    public Ability clone() {
        return new Vanish();
    }
}