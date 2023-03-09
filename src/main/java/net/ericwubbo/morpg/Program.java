package net.ericwubbo.morpg;

import net.ericwubbo.morpg.species.Human;
import net.ericwubbo.morpg.species.Orc;

public class Program {
    public static void main(String[] args) {
        Being player = new Human("Player", 50, new Weapon("sword", 6));
        Being orc = new Orc(20, new Weapon("axe", 3));
        do {
            if (player.isAlive()) player.hit(orc);
            if (orc.isAlive()) orc.hit(player);
        } while (player.isAlive() && orc.isAlive());
        var survivor = player.isAlive() ? player : orc;
        System.out.println(survivor.getName() + " has been victorious!");
    }
}
