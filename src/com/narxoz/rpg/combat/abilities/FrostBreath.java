package com.narxoz.rpg.combat.abilities;

import com.narxoz.rpg.combat.Ability;

public class FrostBreath implements Ability {

    @Override
    public String getName() {
        return "Frost Breath";
    }

    @Override
    public int getDamage() {
        return 95;
    }

    @Override
    public String getDescription() {
        return "Breathes icy wind that slows enemies and deals frost damage.";
    }

    @Override
    public Ability clone() {
        return new FrostBreath();
    }
}