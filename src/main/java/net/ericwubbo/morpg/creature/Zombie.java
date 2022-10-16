package net.ericwubbo.morpg.creature;

import net.ericwubbo.morpg.Weapon;

public class Zombie extends Creature {
    public Zombie() {
        super("zombie", 30, new Weapon("hands", 2));
    }
}
