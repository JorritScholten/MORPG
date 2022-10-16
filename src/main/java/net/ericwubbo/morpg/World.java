package net.ericwubbo.morpg;

public class World {
    public static void message(String text) {
        System.out.println(Character.toUpperCase(text.charAt(0)) + text.substring(1));
    }
}
