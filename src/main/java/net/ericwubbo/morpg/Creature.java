package net.ericwubbo.morpg;

public class Creature extends Being {
    public Creature(String species, int hitPoints, Weapon weapon) {
        super(species, hitPoints, weapon);
        if (!Character.isLowerCase(species.charAt(0)))
            throw new IllegalArgumentException("The species of a creature should start with a lowercase letter!");
    }

    @Override public String getName() {
        return "the " + name;
    }
}
