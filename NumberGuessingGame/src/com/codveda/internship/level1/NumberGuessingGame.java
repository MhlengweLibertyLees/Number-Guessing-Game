package com.codveda.internship.level1;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Number Guessing Game
 * Internship Task - Level 1 (Basic)
 * Author: Liberty Engetelo Chauke
 * 
 * Description:
 *  - Program generates a random number between 1 and 100.
 *  - User has limited attempts to guess the number.
 *  - Provides feedback ("Too high", "Too low").
 *  - Handles invalid inputs gracefully.
 */
public class NumberGuessingGame {

    private static final int MAX_ATTEMPTS = 7;
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 100;

    private final int targetNumber;
    private int attemptsUsed;

    public NumberGuessingGame() {
        Random random = new Random();
        this.targetNumber = random.nextInt(UPPER_BOUND - LOWER_BOUND + 1) + LOWER_BOUND;
        this.attemptsUsed = 0;
    }

    public void startGame() {
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a number between " + LOWER_BOUND + " and " + UPPER_BOUND + ".");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it. Good luck!\n");

        try (Scanner scanner = new Scanner(System.in)) {
            while (attemptsUsed < MAX_ATTEMPTS) {
                int guess = getUserGuess(scanner);
                attemptsUsed++;

                if (guess == targetNumber) {
                    System.out.println("Congratulations! You guessed the number in " + attemptsUsed + " attempts.");
                    return;
                } else if (guess < targetNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                System.out.println("Attempts left: " + (MAX_ATTEMPTS - attemptsUsed) + "\n");
            }
            System.out.println("Game Over! The correct number was: " + targetNumber);
        }
    }

    private int getUserGuess(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter your guess (" + LOWER_BOUND + "-" + UPPER_BOUND + "): ");
                int guess = scanner.nextInt();

                if (guess < LOWER_BOUND || guess > UPPER_BOUND) {
                    System.out.println("Please enter a number between " + LOWER_BOUND + " and " + UPPER_BOUND + ".");
                } else {
                    return guess;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // clear invalid input
            }
        }
    }

    public static void main(String[] args) {
        new NumberGuessingGame().startGame();
    }
}
