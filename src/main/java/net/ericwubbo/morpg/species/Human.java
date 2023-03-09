package net.ericwubbo.morpg.species;

import net.ericwubbo.morpg.Being;
import net.ericwubbo.morpg.Weapon;


public class Human extends Being {
    public Human(String name, int hitPoints, Weapon weapon) {
        super(name, "Human", hitPoints, weapon);
    }
}
