package com.narxoz.rpg.combat.abilities;

import com.narxoz.rpg.combat.Ability;

public class FlameBreath implements Ability {

    @Override
    public String getName() {
        return "Flame Breath";
    }

    @Override
    public int getDamage() {
        return 120;
    }

    @Override
    public String getDescription() {
        return "Breathes fire in a wide cone, burning everything in front.";
    }

    @Override
    public Ability clone() {
        return new FlameBreath();
    }
}