package net.ericwubbo.morpg;

public class Character {
    private final String name;
    private int hitPoints;
    private final Weapon weapon;

    public String getName() {
        return name;
    }

    public Character(String name, int hitPoints, Weapon weapon) {
        this.weapon = weapon;
        this.name = name;
        this.hitPoints = hitPoints;
    }

    public void hit(Character enemy) {
        if (hitPoints <= 0) throw new RuntimeException(name + " is dead!");
        int damage = weapon.getDamage();
        System.out.println(name + " hits " + enemy.name + " with his " + weapon.getName() + " for "
                + damage + " hit points!");
        enemy.getWounded(damage, this);
    }

    private void getWounded(int damage, Character enemy) {
        hitPoints -= damage;
        System.out.println(name + " gets hit by " + enemy.name + " and drops to " + hitPoints + " hit points!");
        if (hitPoints <= 0) System.out.println(name + " dies!");
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }
}
