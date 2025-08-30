package yappy.command;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.exception.YappyException;
import yappy.task.Task;
import yappy.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to find tasks using a matching substring.
 * When executed, it filters the task list for matching tasks and displays them to the user.
 */
public class FindCommand extends Command {

    String query;

    /**
     * Constructs a new <code>FindCommand</code> object with the specified query.
     * This constructor initializes the DeleteCommand's Task.
     *
     * @param query String used to query for tasks.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the find command on the given task list.
     * Iterates through task list to find tasks whose description has
     * a matching substring with the query string.
     *
     * @param taskList The tasks which have been added to Yappy.
     * @param ui The ui object that controls interactions with a user.
     * @param storage The storage object that handles storing to and reading from disk.
     * @throws YappyException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws YappyException {

        ArrayList<Task> filteredList = new ArrayList<>();

        ArrayList<Task> tasks = taskList.getTasks();
        for (Task task : tasks) {
            if (task.getDescription().contains(this.query)) {
                filteredList.add(task);
            }
        }

        if (filteredList.isEmpty()) {
            ui.showMessage("\t No matching task description!");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            ui.showTasks(filteredList);
        }

    }
}
