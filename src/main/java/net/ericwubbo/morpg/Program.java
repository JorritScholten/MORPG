package net.ericwubbo.morpg;

import net.ericwubbo.morpg.creature.Creature;
import net.ericwubbo.morpg.creature.Kobold;
import net.ericwubbo.morpg.creature.Orc;
import net.ericwubbo.morpg.creature.Zombie;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;

enum Gender {FEMALE, MALE, UNDEFINED};

public class Program {
    private static final Random rand = new Random();
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String name = getName();
        System.out.print("Please enter the hero's gender (F(emale), M(ale), N(ot relevant): ");
        Gender gender = getGender();
        Being player = new Player(name, gender, 50, new Weapon("sword", 6));
        EnemyGroup enemyGroup = getEnemies();
        World.message(player.name + " encounters " + enemyGroup.getIndefiniteName() + ".");
        do {
            if (player.isAlive()) letChoose(player, enemyGroup);
            if (enemyGroup.isAlive()) enemyGroup.hit(player);
        } while (player.isAlive() && enemyGroup.isAlive());
        var survivor = player.isAlive() ? player : enemyGroup;
        World.message(survivor.getDefiniteName() + " has been victorious!");
    }

    private static String getName() {
        System.out.print("Please enter the hero's name: ");
        do {
            String name = in.next();
            if (Character.isUpperCase(name.charAt(0))) return name;
            World.message("The hero's name should start with a capital letter! Please try again...");
        } while (true);
    }

    private static Gender getGender() {
        do {
            String genderString = in.next();
            char genderChar = Character.toLowerCase(genderString.charAt(0));
            Gender gender = switch (genderChar) {
                case 'm' -> Gender.MALE;
                case 'f' -> Gender.FEMALE;
                case 'n' -> Gender.UNDEFINED;
                default -> null;
            };
            if (gender != null) return gender;
            World.message("Please enter 'f', 'm' or 'n'!");
        } while (true);
    }

    private static void letChoose(Being player, EnemyGroup enemyGroup) {
        int lastOption = showOptions(player, enemyGroup);
        int choice = getChoice(lastOption);
        if (choice < lastOption) player.hit(enemyGroup.enemies.get(choice - 1));
        else {
            World.message(player.getName() + " retreats ignominiously from the battlefield.");
            World.message(player.getPossessive() + " adventure is over!");
            System.exit(0);
        }
    }

    private static int showOptions(Being player, EnemyGroup enemyGroup) {
        System.out.println("You have " + player.hitPoints + "HP. Please choose an option: ");
        int i = 1;
        for (Iterator<Creature> it = enemyGroup.getEnemies(); it.hasNext(); ) {
            Creature enemy = it.next();
            World.message(i++ + ". Attack " + enemy.getName() + " (" + enemy.hitPoints + " HP).");
        }
        World.message(i + ". Retreat!");
        return i;
    }

    private static int getChoice(int i) {
        int choice;
        do {
            try {
                choice = in.nextInt();
            } catch (Exception e) {
                choice = 0;
                in.next();
            }
            if (choice >= 1 && choice <= i) break;
            World.message("Choose a number between 1 and " + i + "!");
        } while (true);
        return choice;
    }

    public static EnemyGroup getEnemies() {
        int chosenType = rand.nextInt(3);
        int numCreatures = rand.nextInt(4) + 1;
        Function<Integer, Creature> supplier = switch (chosenType) {
            case 0 -> Orc::create;
            case 1 -> Kobold::create;
            case 2 -> Zombie::create;
            default -> throw new RuntimeException();
        };
        return new EnemyGroup(numCreatures, supplier);
    }
}
