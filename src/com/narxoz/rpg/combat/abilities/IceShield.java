package com.narxoz.rpg.combat.abilities;

import com.narxoz.rpg.combat.Ability;

public class IceShield implements Ability {

    @Override
    public String getName() {
        return "Ice Shield";
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Creates a thick ice barrier that increases defense.";
    }

    @Override
    public Ability clone() {
        return new IceShield();
    }
}