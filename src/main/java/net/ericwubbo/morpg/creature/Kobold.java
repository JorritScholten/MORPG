package net.ericwubbo.morpg.creature;

import net.ericwubbo.morpg.Weapon;

public class Kobold extends Creature {
    public Kobold() {
        super("kobold", 15, new Weapon("mining pick", 4));
    }
}
