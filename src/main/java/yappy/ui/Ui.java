package yappy.ui;

import yappy.exception.YappyException;
import yappy.task.Task;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The <code>UI</code> class handles all interactions with the user.
 * This class ensures a consistent user interface during the program's execution.
 */
public class Ui {

    Scanner input;

    /**
     * Constructs a new <code>Ui</code> instance with a <code>Scanner</code>
     * for reading user input.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input.
     *
     * @return The raw user input string.
     * @throws YappyException If no line was found when reading user input.
     */
    public String readCommand() throws YappyException {
        try {
            return input.nextLine();
        } catch (NoSuchElementException e) {
            throw new YappyException("\t No line was found.");
        }
    }

    /**
     *
     */
    public void showLoadingError() {
        System.out.println("\t There was an error loading from the storage file!");
    }

    /**
     * Displays the goodbye message when program exits.
     */
    public void showGoodbye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Displays the default greeting when user starts the program.
     */
    public void showWelcome() {
        System.out.println("\t Hello, I'm Yappy!\n\t What can I do for you?");
    }

    /**
     * Displays the welcome back greeting for recurring users.
     */
    public void showWelcomeBack() {
        System.out.println("\t Hello, welcome back!\n\t What can I do for you?");
    }
    /**
     * Display formatting line.
     */
    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Displays a specific error message to the user.
     *
     * @param message
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays the current tasks in the task list.
     *
     * @param tasks The Arraylist of tasks to be displayed
     */
    public void showTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("\t You currently do not have any tasks");
        }
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            System.out.println("\t " + index + "." + tasks.get(i).toString());
        }
    }

    /**
     * Displays a generic message to the user.
     *
     * @param line the message to display
     */
    public void showMessage(String line) {
        System.out.println(line);
    }
}

