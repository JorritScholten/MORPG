package net.ericwubbo.morpg;

public class Player extends Being {
    public Player(String name, int hitPoints, Weapon weapon) {

        super(name, hitPoints, weapon);
        if (!Character.isUpperCase(name.charAt(0)))
            throw new IllegalArgumentException("The name of a player should start with a capital letter!");
    }
}
