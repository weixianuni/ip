package yappy.ui;

import yappy.exception.YappyException;
import yappy.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    Scanner input;

    /**
     *
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     *
     * @return
     * @throws YappyException
     */
    public String readCommand() throws YappyException {
        return input.nextLine();
    }

    /**
     *
     */
    public void showLoadingError() {
        System.out.println("\t There was an error loading from the storage file!");
    }

    /**
     *
     */
    public void showGoodbye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     *
     */
    public void showWelcome() {
        System.out.println("\t Hello, I'm duke.ui.Yappy!\n\t What can I do for you?");
    }

    /**
     *
     */
    public void showWelcomeBack() {
        System.out.println("\t Hello, welcome Back!\n\t What can I do for you?");
    }
    /**
     *
     */
    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     *
     * @param message
     */
    public void showError(String message) {
        System.out.println(message);
    }

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
     *
     * @param line
     */
    public void showMessage(String line) {
        System.out.println(line);
    }
}

