package com.narxoz.rpg.combat.abilities;

import com.narxoz.rpg.combat.Ability;

public class ShadowStrike implements Ability {

    @Override
    public String getName() {
        return "Shadow Strike";
    }

    @Override
    public int getDamage() {
        return 110;
    }

    @Override
    public String getDescription() {
        return "A quick strike from the shadows with high critical chance.";
    }

    @Override
    public Ability clone() {
        return new ShadowStrike();
    }
}