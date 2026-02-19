package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;
import java.util.ArrayList;
import java.util.List;

public class Goblin implements Enemy {
    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;

    private String element;
    private String aiBehavior;

    private List<Ability> abilities;
    private LootTable lootTable;

    public Goblin(String name) {
        this.name = name;

        this.health = 100;
        this.damage = 15;
        this.defense = 5;
        this.speed = 35;

        this.element = "NONE";
        this.aiBehavior = "COWARDLY";

        this.abilities = new ArrayList<>();
        this.lootTable = null;
    }

    @Override
    public String getName() { return name; }
    @Override
    public int getHealth() { return health; }
    @Override
    public int getDamage() { return damage; }
    @Override
    public int getDefense() { return defense; }
    @Override
    public int getSpeed() { return speed; }
    @Override
    public String getElement() { return element; }
    @Override
    public String getAIBehavior() { return aiBehavior; }
    @Override
    public List<Ability> getAbilities() { return abilities; }
    @Override
    public LootTable getLootTable() { return lootTable; }


    @Override
    public void displayInfo() {
        System.out.println("=== " +name+ " (Goblin) ===");
        System.out.println("Health: " + health + " | Damage: " + damage
                + " | Defense: " + defense + " | Speed: " + speed);
        System.out.println("Element: " + element + " | AI: " + aiBehavior);

        System.out.println("Abilities (" + abilities.size() + "):");
        for (Ability a : abilities) {
            System.out.println("  - "+a.getName()+" (DMG "+a.getDamage()+"): "+a.getDescription());
        }

        if (lootTable!=null) {
            System.out.println("Loot: "+lootTable.getLootInfo());
        } else {
            System.out.println("Loot: (none)");
        }
        System.out.println();
    }


    @Override
    public Enemy clone() {
        Goblin copy=new Goblin(this.name);

        copy.health=this.health;
        copy.damage=this.damage;
        copy.defense=this.defense;
        copy.speed=this.speed;
        copy.element=this.element;
        copy.aiBehavior=this.aiBehavior;

        copy.abilities=new ArrayList<>();
        for (Ability ability : this.abilities) {
            copy.abilities.add(ability.clone());
        }


        if (this.lootTable!=null) {
            copy.lootTable=this.lootTable.clone();
        }
        return copy;
    }

    @Override
    public void multiplyStats(double multiplier) {
        if (multiplier<=0) return;

        this.health=(int) Math.round(this.health * multiplier);
        this.damage=(int) Math.round(this.damage * multiplier);
        this.defense=(int) Math.round(this.defense * multiplier);
        this.speed=(int) Math.round(this.speed * multiplier);
    }

    @Override
    public void setElement(String element) {
        this.element=(element==null || element.isBlank()) ? "NONE" : element;
    }

    @Override
    public void addAbility(Ability ability) {
        if (ability!=null) {
            this.abilities.add(ability);
        }
    }

    @Override
    public void setLootTable(LootTable lootTable) {
        this.lootTable=lootTable;
    }

    @Override
    public void setAIBehavior(String aiBehavior) {
        this.aiBehavior=(aiBehavior==null || aiBehavior.isBlank()) ? "UNKNOWN" : aiBehavior;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities=(abilities==null) ? new ArrayList<>() : new ArrayList<>(abilities);
    }
}
