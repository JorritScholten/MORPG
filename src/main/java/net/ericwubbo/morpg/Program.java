package net.ericwubbo.morpg;

import net.ericwubbo.morpg.creature.Creature;
import net.ericwubbo.morpg.creature.Kobold;
import net.ericwubbo.morpg.creature.Orc;
import net.ericwubbo.morpg.creature.Zombie;

import java.util.Random;
import java.util.function.Supplier;

public class Program {
    public static Random rand = new Random();

    public static void main(String[] args) {
        Being player = new Player("Carl", 50, new Weapon("sword", 6));
        EnemyGroup enemyGroup = getEnemies();
        World.message(player.name + " encounters " + enemyGroup.getName() + ".");
        do {
            if (player.isAlive()) player.hit(enemyGroup.getRandomLivingEnemy());
            if (enemyGroup.isAlive()) enemyGroup.hit(player);
        } while (player.isAlive() && enemyGroup.isAlive());
        var survivor = player.isAlive() ? player : enemyGroup;
        World.message(survivor.getName() + " has been victorious!");
    }

    public static EnemyGroup getEnemies() {
        int chosenType = rand.nextInt(3);
        int numCreatures = rand.nextInt(4) + 1;
        Supplier<Creature> supplier = switch (chosenType) {
            case 0 -> Orc::new;
            case 1 -> Kobold::new;
            case 2 -> Zombie::new;
            default -> throw new RuntimeException();
        };
        return new EnemyGroup(numCreatures, supplier);
    }
}
