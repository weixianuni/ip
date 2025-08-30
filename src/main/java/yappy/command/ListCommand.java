package yappy.command;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.exception.YappyException;
import yappy.ui.Ui;

/**
 * Represents a command to list all current tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command and calls the ui object to list all current tasks.
     *
     * @param tasks The tasks which have been added to Yappy.
     * @param ui The ui object that controls interactions with a user.
     * @param storage The storage object that handles storing to and reading from disk.
     * @throws YappyException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws YappyException {
        if (tasks.getTasks().isEmpty()) {
            ui.showMessage("You currently do not have any tasks");
        } else {
            ui.showMessage("\t Here are the tasks in your list:");
            ui.showTasks(tasks.getTasks());
        }
    }
}
