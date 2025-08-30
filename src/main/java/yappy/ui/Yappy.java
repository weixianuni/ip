package yappy.ui;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.command.Command;
import yappy.exception.YappyException;

import java.nio.file.Paths;

/**
 * The {@code Yappy} class serves as the main entry point for the Yappy application.
 * The class also manages the main program loop, which continuously reads user input,
 * parses it into a <code>Command</code>, and executes it until the user exits.
 */
public class Yappy {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initialise storage, tasks and ui variables.
     *
     * @param filePath The file path to store the add tasks.
     */
    public Yappy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTask());
            ui.showLine();
            ui.showWelcomeBack();
            ui.showLine();
        } catch (YappyException e) {
            tasks = new TaskList();
            ui.showLine();
            ui.showWelcome();
            ui.showLine();
        }
    }

    /**
     * Keeps the program alive and listens for user inputs.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (YappyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.showLine();
        ui.showGoodbye();
        ui.showLine();
    }

    /**
     * Main method for program to enter.
     *
     * @param args
     */
    public static void main(String[] args){
        String storagePath = Paths.get("src/main/data", "Yappy.txt").toString();
        new Yappy(storagePath).run();
    }
}
