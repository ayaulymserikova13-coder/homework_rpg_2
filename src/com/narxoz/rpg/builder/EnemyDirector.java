package com.narxoz.rpg.builder;

import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.factory.EnemyComponentFactory;

public class EnemyDirector {
    private final BasicEnemyBuilder basicBuilder;
    private final BossEnemyBuilder bossBuilder;

    public EnemyDirector() {
        this.basicBuilder = new BasicEnemyBuilder();
        this.bossBuilder = new BossEnemyBuilder();
    }

    public Enemy createMinion(String name, EnemyComponentFactory factory) {
        return basicBuilder.reset()
                .setName(name)
                .setElement("NONE")
                .setAIBehavior(factory.createAIBehavior())
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .build();
    }

    public Enemy createElite(String name, EnemyComponentFactory factory) {
        Enemy e = basicBuilder.reset()
                .setName(name)
                .setElement("ELITE")
                .setAIBehavior(factory.createAIBehavior())
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .build();

        e.multiplyStats(1.5);
        return e;
    }

    public Enemy createMiniBoss(String name, EnemyComponentFactory factory) {
        return bossBuilder.reset()
                .setName(name)
                .setHealth(450)
                .setDamage(60)
                .setDefense(25)
                .setSpeed(20)
                .setElement("BOSS")
                .setAIBehavior(factory.createAIBehavior())
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .setPhases(250, 120, 60)
                .setFlight(true, true, 15)
                .build();
    }

    public Enemy createRaidBoss(String name, EnemyComponentFactory factory) {
        return bossBuilder.reset()
                .setName(name)
                .setHealth(900)
                .setDamage(120)
                .setDefense(60)
                .setSpeed(15)
                .setElement("RAID_BOSS")
                .setAIBehavior(factory.createAIBehavior())
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .setPhases(700, 400, 150)
                .setFlight(true, true, 30)
                .build();
    }
}