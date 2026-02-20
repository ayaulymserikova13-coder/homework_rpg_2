package com.narxoz.rpg.loot;

import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

public class FireLootTable implements LootTable {

    private final List<String> items;
    private final int gold;
    private final int exp;

    public FireLootTable() {
        items = new ArrayList<>();
        items.add("Fire Gem");
        items.add("Dragon Scale");
        items.add("Flame Rune");

        gold = 250;
        exp = 120;
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
        return new FireLootTable();
    }
}