package net.ericwubbo.morpg;

public class Program {
    public static void main(String[] args) {
        Character player = new Character("Player", 50, new Weapon("sword", 6));
        Character orc = new Character("orc", 20, new Weapon("axe", 3));
        do {
            if (player.isAlive()) player.hit(orc);
            if (orc.isAlive()) orc.hit(player);
        } while (player.isAlive() && orc.isAlive());
        var survivor = player.isAlive() ? player : orc;
        System.out.println(survivor.getName() + " has been victorious!");
    }
}
