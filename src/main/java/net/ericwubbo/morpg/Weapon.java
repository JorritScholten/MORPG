package net.ericwubbo.morpg;

import java.util.Random;

public class Weapon {
    private final String name;
    private final int maxDamage;
    private final Random rand = new Random();

    public Weapon(String name, int maxDamage) {
        this.name = name;
        this.maxDamage = maxDamage;
    }

    public int getDamage() {
        return rand.nextInt(maxDamage) + 1;
    }

    public String getName() {
        return name;
    }
}
