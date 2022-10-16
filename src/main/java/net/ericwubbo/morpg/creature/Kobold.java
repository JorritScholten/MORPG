package net.ericwubbo.morpg.creature;

import net.ericwubbo.morpg.Weapon;

public class Kobold extends Creature {
    protected static String species = "kobold";

    public Kobold() {
        super(species, 15, new Weapon("mining pick", 4));
    }

    public Kobold(int number) {
        super(species + " " + number, 15, new Weapon("mining pick", 4));
    }

    public static Kobold create(int number) {
        if (number == 0) return new Kobold();
        return new Kobold(number);
    }

    @Override
    public String getSpecies() {
        return species;
    }
}
