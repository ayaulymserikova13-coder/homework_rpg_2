package com.narxoz.rpg.loot;

import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

public class ShadowLootTable implements LootTable {

    private final List<String> items;
    private final int gold;
    private final int exp;

    public ShadowLootTable() {
        items = new ArrayList<>();
        items.add("Shadow Gem");
        items.add("Dark Essence");
        items.add("Shadow Rune");

        gold = 300;
        exp = 140;
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
        return new ShadowLootTable();
    }
}