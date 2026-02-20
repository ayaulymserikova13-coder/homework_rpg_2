package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.DragonBoss;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

public class BossEnemyBuilder implements EnemyBuilder {
    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;

    private String element;
    private String aiBehavior;

    private List<Ability> abilities;
    private LootTable lootTable;

    private int phase1;
    private int phase2;
    private int phase3;

    private boolean canFly;
    private boolean hasBreathAttack;
    private int wingspan;

    public BossEnemyBuilder() {
        reset();
    }

    @Override
    public BossEnemyBuilder reset() {
        name="Unnamed Boss";
        health=500;
        damage=80;
        defense=40;
        speed=25;

        element="NONE";
        aiBehavior="AGGRESSIVE";

        abilities=new ArrayList<>();
        lootTable=null;

        phase1=300;
        phase2=150;
        phase3=50;

        canFly=true;
        hasBreathAttack=true;
        wingspan=20;

        return this;
    }

    @Override
    public BossEnemyBuilder setName(String name) {
        if (name !=null && !name.isBlank()) this.name=name;
        return this;
    }

    @Override
    public BossEnemyBuilder setHealth(int health) {
        this.health=health;
        return this;
    }

    @Override
    public BossEnemyBuilder setDamage(int damage) {
        this.damage=damage;
        return this;
    }

    @Override
    public BossEnemyBuilder setDefense(int defense) {
        this.defense=defense;
        return this;
    }

    @Override
    public BossEnemyBuilder setSpeed(int speed) {
        this.speed=speed;
        return this;
    }

    @Override
    public BossEnemyBuilder setElement(String element) {
        this.element=(element == null || element.isBlank()) ? "NONE" : element;
        return this;
    }

    @Override
    public BossEnemyBuilder setAIBehavior(String aiBehavior) {
        this.aiBehavior=(aiBehavior == null || aiBehavior.isBlank()) ? "UNKNOWN" : aiBehavior;
        return this;
    }

    @Override
    public BossEnemyBuilder addAbility(Ability ability) {
        if (ability != null) abilities.add(ability);
        return this;
    }

    @Override
    public BossEnemyBuilder setAbilities(List<Ability> abilities) {
        this.abilities=(abilities == null) ? new ArrayList<>() : new ArrayList<>(abilities);
        return this;
    }

    @Override
    public BossEnemyBuilder setLootTable(LootTable lootTable) {
        this.lootTable=lootTable;
        return this;
    }

    public BossEnemyBuilder setPhases(int p1, int p2, int p3) {
        this.phase1=p1;
        this.phase2=p2;
        this.phase3=p3;
        return this;
    }

    public BossEnemyBuilder setFlight(boolean canFly, boolean hasBreathAttack, int wingspan) {
        this.canFly=canFly;
        this.hasBreathAttack=hasBreathAttack;
        this.wingspan=wingspan;
        return this;
    }

    @Override
    public Enemy build() {
        if (name==null || name.isBlank()) throw new IllegalStateException("Boss must have a name");
        if (health <=0) throw new IllegalStateException("Health must be > 0");
        if (damage <0 || defense < 0 || speed < 0) throw new IllegalStateException("Stats must be >= 0");
        if (abilities==null || abilities.isEmpty()) throw new IllegalStateException("Boss must have at least 1 ability");
        if (phase1 <= phase2 || phase2 <= phase3) throw new IllegalStateException("Phases must be descending: p1 > p2 > p3");

        List<Ability> abilitiesCopy = new ArrayList<>(abilities);

        DragonBoss boss = new DragonBoss(
                name, health, damage, defense, speed,
                element, abilitiesCopy,
                phase1, phase2, phase3,
                lootTable, aiBehavior,
                canFly, hasBreathAttack, wingspan
        );

        reset();
        return boss;
    }
}