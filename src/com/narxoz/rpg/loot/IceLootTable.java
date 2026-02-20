package com.narxoz.rpg.loot;

import java.util.ArrayList;
import java.util.List;

public class IceLootTable implements LootTable {

    private final List<String> items;
    private final int gold;
    private final int exp;

    public IceLootTable() {
        items = new ArrayList<>();
        items.add("Ice Gem");
        items.add("Frost Scale");
        items.add("Ice Rune");

        gold = 220;
        exp = 110;
    }

    @Override
    public List<String> getItems() {
        return new ArrayList<>(items);
    }

    @Override
    public int getGoldDrop() {
        return gold;
    }

    @Override
    public int getExperienceDrop() {
        return exp;
    }

    @Override
    public String getLootInfo() {
        return "Items: " + items + " | Gold: " + gold + " | EXP: " + exp;
    }

    @Override
    public LootTable clone() {
        return new IceLootTable();
    }
}