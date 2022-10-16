package net.ericwubbo.morpg.creature;

import net.ericwubbo.morpg.Being;
import net.ericwubbo.morpg.Weapon;
import net.ericwubbo.morpg.World;

public class Orc extends Creature {
    int maxHitPoints;

    public Orc() {
        super("orc", 20, new Weapon("axe", 3));
        maxHitPoints = hitPoints;
    }

    private boolean isEnraged() {
        return hitPoints <= maxHitPoints * 0.3;
    }

    @Override protected int getDamage() {
        int damage = super.getDamage();
        if (isEnraged()) damage *= 2;
        return damage;
    }

    @Override
    public String getName() {
        String enragedText = isEnraged() ? "enraged " : "";
        return "the " + enragedText + "orc";
    }

    @Override protected void updateStatus(int damage, Being enemy) {
        boolean startsEnraged = isEnraged();
        String preEnrageName = getName();
        hitPoints -= damage;

        World.message(preEnrageName + " gets hit by " + enemy.getName() + " and drops to " + hitPoints + " hit points!");
        boolean endsEnraged = isEnraged();
        if (startsEnraged != endsEnraged) World.message(preEnrageName + " enrages!");
    }
}
