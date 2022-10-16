package net.ericwubbo.morpg;

import net.ericwubbo.morpg.creature.Creature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class EnemyGroup implements Named {
    List<Creature> enemies = new ArrayList<>();
    Random rand = new Random();

    public EnemyGroup(int numCreatures, Supplier<Creature> creatureMaker) {
        for (int i = 0; i < numCreatures; i++) enemies.add(creatureMaker.get());
    }

    public Creature getRandomLivingEnemy() {
        int numEnemiesRemaining = enemies.size();
        if (numEnemiesRemaining == 0) throw new RuntimeException("No enemy is alive anymore!");
        return enemies.get(rand.nextInt(numEnemiesRemaining));
    }

    public void hit(Being player) {
        int numEnemiesRemaining = enemies.size();
        if (numEnemiesRemaining == 0) throw new RuntimeException("No enemy is alive anymore!");
        for (Creature enemy : enemies) enemy.hit(player);
    }

    public boolean isAlive() {
        for (int i = 0; i < enemies.size(); i++) {
            if (!enemies.get(i).isAlive()) {
                enemies.remove(i);
                i--;
            }
        }
        return enemies.size() > 0;
    }

    @Override
    public String getName() {
        int groupSize = enemies.size();
        if (groupSize == 1) return enemies.get(0).getName();
        else return "the group of " + groupSize + " " + enemies.get(0).name + "s";
    }
}
