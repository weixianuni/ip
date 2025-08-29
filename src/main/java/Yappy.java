import java.nio.file.Path;
import java.nio.file.Paths;

public class Yappy {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;


    /**
     * Initialise storage, tasks and ui
     *
     * @param filePath
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
     * handle the reading of input from user
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

        ui.showGoodbye();
    }


    public static void main(String[] args){

        String storagePath = Paths.get("src/main/data", "Yappy.txt").toString();


        new Yappy(storagePath).run();

    }
}
