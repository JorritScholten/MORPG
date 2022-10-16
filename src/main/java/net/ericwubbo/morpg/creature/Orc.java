package net.ericwubbo.morpg.creature;

import net.ericwubbo.morpg.Being;
import net.ericwubbo.morpg.Weapon;
import net.ericwubbo.morpg.World;

public class Orc extends Creature {
    int maxHitPoints;

    public Orc(int hitPoints, Weapon weapon) {
        super("orc", hitPoints, weapon);
        maxHitPoints = hitPoints;
    }

    private boolean isEnraged() {
        return hitPoints <= maxHitPoints * 0.3;
    }

    @Override
    public void hit(Being enemy) {
        if (hitPoints <= 0) throw new RuntimeException(getName() + " is dead!");
        int damage = weapon.getDamage();
        if (isEnraged()) damage *= 2;
        World.message(getName() + " hits " + enemy.getName() + " with his " + weapon.getName() + " for "
                + damage + " hit points!");
        enemy.getWounded(damage, this);
    }

    @Override
    public String getName() {
        String enragedText = isEnraged() ? "enraged " : "";
        return "the " + enragedText + "orc";
    }

    @Override
    public void getWounded(int damage, Being enemy) {
        boolean startsEnraged = isEnraged();
        String preEnrageName = getName();
        hitPoints -= damage;

        World.message(preEnrageName + " gets hit by " + enemy.getName() + " and drops to " + hitPoints + " hit points!");
        boolean endsEnraged = isEnraged();
        if (startsEnraged != endsEnraged) World.message(preEnrageName + " enrages!");
        if (hitPoints <= 0) World.message(getName() + " dies!");
    }
}
