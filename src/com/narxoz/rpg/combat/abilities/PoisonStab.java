package com.narxoz.rpg.combat.abilities;

import com.narxoz.rpg.combat.Ability;

public class PoisonStab implements Ability {
    @Override
    public String getName() {
        return "Poison Stab";
    }

    @Override
    public int getDamage() {
        return 35;
    }

    @Override
    public String getDescription() {
        return "A quick stab that applies poison damage over time.";
    }

    @Override
    public Ability clone() {
        return new PoisonStab();
    }
}