package net.ericwubbo.morpg;

abstract public class Being {
    protected final String name;
    protected final String speciesName;
    protected final Weapon weapon;
    private int hitPoints;

    protected Being(String name, String speciesName, int hitPoints, Weapon weapon) {
        this.name = name;
        this.speciesName = speciesName;
        this.hitPoints = hitPoints;
        this.weapon = weapon;
    }

    public void hit(Being enemy) {
        if (hitPoints <= 0) throw new RuntimeException(name + " is dead!");
        int damage = weapon.getDamage();
        System.out.println(getName() + " hits " + enemy.getName() + " with his " + weapon.getName() + " for "
                + damage + " hit points!");
        enemy.getWounded(damage, this);
    }

    private void getWounded(int damage, Being enemy) {
        hitPoints -= damage;
        System.out.println(getName() + " gets hit by " + enemy.getName() + " and drops to " + hitPoints + " hit points!");
        if (hitPoints <= 0) System.out.println(getName() + " dies!");
    }

    public String getName() {
        if (name.isEmpty()) return speciesName.toLowerCase();
        else return name;
    }

    public Boolean isAlive() {
        return hitPoints > 0;
    }
}
