package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

public class BasicEnemyBuilder implements EnemyBuilder {

    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;

    private String element;
    private String aiBehavior;

    private List<Ability> abilities;
    private LootTable lootTable;

    public BasicEnemyBuilder() {
        reset();
    }

    @Override
    public BasicEnemyBuilder reset() {
        name="Unnamed Goblin";
        health=100;
        damage=15;
        defense=5;
        speed=35;

        element="NONE";
        aiBehavior="COWARDLY";

        abilities=new ArrayList<>();
        lootTable=null;
        return this;
    }

    @Override
    public BasicEnemyBuilder setName(String name) {
        if (name !=null && !name.isBlank()) this.name=name;
        return this;
    }

    @Override
    public BasicEnemyBuilder setHealth(int health) {
        this.health=health;
        return this;
    }

    @Override
    public BasicEnemyBuilder setDamage(int damage) {
        this.damage=damage;
        return this;
    }

    @Override
    public BasicEnemyBuilder setDefense(int defense) {
        this.defense=defense;
        return this;
    }

    @Override
    public BasicEnemyBuilder setSpeed(int speed) {
        this.speed=speed;
        return this;
    }

    @Override
    public BasicEnemyBuilder setElement(String element) {
        this.element=(element == null || element.isBlank()) ? "NONE" : element;
        return this;
    }

    @Override
    public BasicEnemyBuilder setAIBehavior(String aiBehavior) {
        this.aiBehavior=(aiBehavior == null || aiBehavior.isBlank()) ? "UNKNOWN" : aiBehavior;
        return this;
    }

    @Override
    public BasicEnemyBuilder addAbility(Ability ability) {
        if (ability!= null) abilities.add(ability);
        return this;
    }

    @Override
    public BasicEnemyBuilder setAbilities(List<Ability> abilities) {
        this.abilities=(abilities == null) ? new ArrayList<>() : new ArrayList<>(abilities);
        return this;
    }

    @Override
    public BasicEnemyBuilder setLootTable(LootTable lootTable) {
        this.lootTable=lootTable;
        return this;
    }

    @Override
    public Enemy build() {
        if (name==null || name.isBlank()) throw new IllegalStateException("Enemy must have a name");
        if (health<=0) throw new IllegalStateException("Health must be > 0");
        if (damage<0 || defense<0 || speed < 0) throw new IllegalStateException("Stats must be >= 0");

        Goblin enemy=new Goblin(name);

        enemy.multiplyStats(1.0);

        if (health != 100) enemy.multiplyStats(health / 100.0);

        enemy.setElement(element);
        enemy.setAIBehavior(aiBehavior);
        enemy.setLootTable(lootTable);

        for (Ability a : abilities) enemy.addAbility(a);

        reset();
        return enemy;
    }
}