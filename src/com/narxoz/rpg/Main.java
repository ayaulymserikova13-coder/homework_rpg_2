package com.narxoz.rpg;

import com.narxoz.rpg.builder.BasicEnemyBuilder;
import com.narxoz.rpg.builder.BossEnemyBuilder;
import com.narxoz.rpg.builder.EnemyBuilder;
import com.narxoz.rpg.builder.EnemyDirector;
import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.abilities.PoisonStab;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.factory.EnemyComponentFactory;
import com.narxoz.rpg.factory.FireComponentFactory;
import com.narxoz.rpg.factory.IceComponentFactory;
import com.narxoz.rpg.factory.ShadowComponentFactory;
import com.narxoz.rpg.prototype.EnemyRegistry;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== RPG Enemy System - Creational Patterns Capstone ===\n");

        System.out.println("====================================================");
        System.out.println("PART 1: ABSTRACT FACTORY - Themed Components");
        System.out.println("====================================================");

        EnemyComponentFactory fireFactory = new FireComponentFactory();
        EnemyComponentFactory iceFactory = new IceComponentFactory();
        EnemyComponentFactory shadowFactory = new ShadowComponentFactory();

        printFactoryInfo("FIRE", fireFactory);
        printFactoryInfo("ICE", iceFactory);
        printFactoryInfo("SHADOW", shadowFactory);

        System.out.println("\n====================================================");
        System.out.println("PART 2: BUILDER - Step-by-step construction");
        System.out.println("====================================================");

        EnemyBuilder basicBuilder=new BasicEnemyBuilder();

        Enemy fireMinion=basicBuilder
                .reset()
                .setName("Fire Minion Goblin")
                .setElement("FIRE")
                .setAIBehavior(fireFactory.createAIBehavior())
                .setLootTable(fireFactory.createLootTable())
                .setAbilities(fireFactory.createAbilities())
                .build();

        fireMinion.displayInfo();

        EnemyBuilder bossBuilder=new BossEnemyBuilder();

        Enemy fireBoss=((BossEnemyBuilder) bossBuilder)
                .reset()
                .setName("Ancient Fire Dragon")
                .setElement("FIRE")
                .setAIBehavior(fireFactory.createAIBehavior())
                .setLootTable(fireFactory.createLootTable())
                .setAbilities(fireFactory.createAbilities())
                .setPhases(300, 150, 50)
                .setFlight(true, true, 25)
                .build(); // Factory Method idea: build() returns a concrete Enemy (boss)

        fireBoss.displayInfo();

        System.out.println("\n====================================================");
        System.out.println("PART 3: PROTOTYPE - Clone templates into variants");
        System.out.println("====================================================");

        EnemyRegistry registry = new EnemyRegistry();

        registry.registerTemplate("minion_fire", fireMinion);
        registry.registerTemplate("boss_fire", fireBoss);

        System.out.println("Registered templates: " + registry.listTemplates());
        System.out.println();

        Enemy eliteMinion = registry.createFromTemplate("minion_fire");
        eliteMinion.multiplyStats(1.5);
        eliteMinion.setElement("ELITE_FIRE");

        System.out.println("---- Variant created from template: ELITE MINION ----");
        eliteMinion.displayInfo();

        Enemy raidBoss = registry.createFromTemplate("boss_fire");
        raidBoss.multiplyStats(2.0);
        raidBoss.setElement("RAID_FIRE");

        System.out.println("---- Variant created from template: RAID BOSS ----");
        raidBoss.displayInfo();

        System.out.println("---- DEEP COPY CHECK (template must NOT change) ----");

        int templateCountBefore=fireMinion.getAbilities().size();
        int cloneCountBefore=eliteMinion.getAbilities().size();

        eliteMinion.addAbility(new PoisonStab());

        int templateCountAfter=fireMinion.getAbilities().size();
        int cloneCountAfter=eliteMinion.getAbilities().size();

        System.out.println("Template abilities count (before/after): " + templateCountBefore + " / " + templateCountAfter);
        System.out.println("Clone abilities count (before/after):     " + cloneCountBefore + " / " + cloneCountAfter);
        System.out.println();

        System.out.println("TEMPLATE (should be unchanged):");
        fireMinion.displayInfo();

        System.out.println("CLONE (should have extra ability):");
        eliteMinion.displayInfo();

        System.out.println("\n====================================================");
        System.out.println("PART 4: FULL PIPELINE - Director presets + themes");
        System.out.println("====================================================");

        EnemyDirector director = new EnemyDirector();

        Enemy directorRaidBoss = director.createRaidBoss("Shadow Raid Dragon", shadowFactory);
        directorRaidBoss.displayInfo();

        Enemy directorMinion = director.createMinion("Ice Minion", iceFactory);
        directorMinion.displayInfo();

        System.out.println("\n====================================================");
        System.out.println("SUMMARY");
        System.out.println("====================================================");
        System.out.println("Abstract Factory creates themed components (abilities + loot + AI).");
        System.out.println("Builder constructs enemies step-by-step with validation.");
        System.out.println("Prototype clones templates safely (deep copy proven).");
        System.out.println("Factory Method: build() in each Builder creates a concrete Enemy.");
    }

    private static void printFactoryInfo(String themeName, EnemyComponentFactory factory) {
        System.out.println("\n-- " + themeName + " FACTORY --");

        List<Ability> abilities = factory.createAbilities();
        System.out.println("Abilities:");
        for (Ability a : abilities) {
            System.out.println("  - " + a.getName() + " (DMG " + a.getDamage() + "): " + a.getDescription());
        }

        System.out.println("AI: " + factory.createAIBehavior());
        System.out.println("Loot: " + factory.createLootTable().getLootInfo());
    }
}