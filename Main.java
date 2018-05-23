package com.PhilMarcoccia;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // System objects
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        // Game variables
        String[] enemies = { "Skeleton", "Zombie", "Warrior", "Assassin" };
        int maxEnemyHealth = 75;
        int enemyAttackDamage = 25;

        // Player variables
        int health = 100;
        int attackDamage = 100;
        int numHealthPots = 3;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 50; // Percentage

        boolean running = true;

        System.out.println("  ");
        System.out.println("*********************************************");
        System.out.println("Welcome to Falriel Lore: Dungeons!");
        System.out.println("  ");
        System.out.println("\t Of late, there have been reports of troublesome entities lurking in the western dungeon.");
        System.out.println("\t Consider this a chance for you to prove your bravery to the people of Falriel!");
        System.out.println("\t Go into the dungeon and cleanse our town of those forbidden souls!");
        System.out.println("  ");
        System.out.println("*********************************************");
        System.out.println("  ");

        INTRO:
        while(running) {
            System.out.println("What would you like to do?");
            System.out.println("\t1. Enter the dungeon.");
            System.out.println("\t2. Sorry, but I am not brave enough!");

            String input = in.nextLine();

            while (!input.equals("1") && (!input.equals("2"))) {
                System.out.println("Invalid command. Choose an option by entering 1 or 2.");
                input = in.nextLine();
            }

            if (input.equals("1")) {
                System.out.println("You enter the dungeon.");
                break INTRO;
            }

            else if (input.equals("2")) {
                System.out.println("Okay, see ya!");
                System.exit(0);
            }
        }

        GAME:
        while(running) {
            System.out.println("---------------------------------------------");

            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)];
            System.out.println("\t# " + enemy + " appeared! #\n");

            while(enemyHealth > 0) {
                System.out.println("\t Your HP: " + health);
                System.out.println("\t " + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\t What would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion");
                System.out.println("\t3. Run!");

                String input = in.nextLine();
                if(input.equals("1")) {
                    int damageDealt = rand.nextInt(attackDamage);
                    int damageTaken = rand.nextInt(enemyAttackDamage);

                    enemyHealth -= damageDealt;
                    health -= damageTaken;
                    System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
                    System.out.println("\t> You receive " + damageTaken + " in retaliation.");

                    if (health < 1) {
                        System.out.println("\t> You have taken too much damage, you are too weak to go on!");
                        break;
                    }
                }

                else if(input.equals("2")) {
                    if (numHealthPots > 0) {
                        health += healthPotionHealAmount;
                        numHealthPots--;
                        System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHealAmount + "."
                                + "\n\t> You now have " + health + " HP."
                                + "\n\t> You have " + numHealthPots + " health potions left. \n");
                    }

                    else {
                        System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one");
                    }

                }

                else if(input.equals("3")) {
                    System.out.println("\t You run away from the " + enemy + "!");
                    continue GAME;
                }

                else {
                    System.out.println("\t> Invalid command!");
                }

                if(health < 1) {
                    System.out.println("You limp out of the dungeon, weak from battle.");
                    break;
                }

                System.out.println("---------------------------------------------");
                System.out.println(" # " + enemy + " was defeated!  # ");
                System.out.println(" # You have " + health + " HP remaining. # ");

                if (rand.nextInt(100) < healthPotionDropChance) {
                    numHealthPots++;
                    System.out.println(" # The enemy dropped a health potion! # ");
                    System.out.println(" # You have " + numHealthPots + " potions. # ");
                }

                System.out.println("---------------------------------------------");
                System.out.println("What would you like to do?");
                System.out.println("1. Continue fighting");
                System.out.println("2. Exit dungeon");

                input = in.nextLine();

                while(!input.equals("1") && !input.equals("2")) {
                    System.out.println("Invalid command.");
                    input = in.nextLine();
                }

                if (input.equals("1")) {
                    System.out.println("You continue on the journey.");
                }

                else if (input.equals("2")) {
                    System.out.println("You exit the dungeon.");
                    System.out.println("###################");
                    System.out.println("Thanks for playing!");
                    System.out.println("###################");
                    break GAME;
                }
            }
        }
    }
}
