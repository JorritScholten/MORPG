package net.ericwubbo.morpg;

abstract public class Being implements Named {
    protected final String name;
    protected int hitPoints;
    protected final Weapon weapon;

    public String getName() {
        return name;
    }

    protected Being(String name, int hitPoints, Weapon weapon) {
        this.weapon = weapon;
        this.name = name;
        this.hitPoints = hitPoints;
    }

    protected int getDamage() {
        return weapon.getDamage();
    }

    public void hit(Being enemy) {
        if (hitPoints <= 0) throw new RuntimeException(getName()  + " is dead!");
        int damage = getDamage();
        World.message(getName() + " hits " + enemy.getName() + " with his " + weapon.getName() + " for "
                + damage + " hit points!");
        enemy.getWounded(damage, this);
    }

    public void getWounded(int damage, Being enemy) {
        updateStatus(damage, enemy);
        if (hitPoints <= 0)  World.message(getName()  + " dies!");
    }

    protected void updateStatus(int damage, Being enemy) {
        hitPoints -= damage;
        World.message(getName()  + " gets hit by " + enemy.getName()  + " and drops to " + hitPoints + " hit points!");
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }
}
