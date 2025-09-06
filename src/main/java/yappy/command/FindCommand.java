package yappy.command;

import java.util.ArrayList;
import java.util.Arrays;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.exception.YappyException;
import yappy.task.Task;
import yappy.ui.Ui;

/**
 * Represents a command to find tasks using a matching substring.
 * When executed, it filters the task list for matching tasks and displays them to the user.
 */
public class FindCommand extends Command {

    private ArrayList<String> queries;

    /**
     * Constructs a new <code>FindCommand</code> object with the specified query.
     * This constructor initializes the DeleteCommand's Task.
     *
     * @param queries Array of queries to match tasks stored
     */
    public FindCommand(String ... queries) {
        this.queries = new ArrayList<String>(Arrays.asList(queries));
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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws YappyException {

        ArrayList<Task> filteredList = new ArrayList<>();

        ArrayList<Task> tasks = taskList.getTasks();
        for (Task task : tasks) {
            for (String query : queries) {
                if (task.getDescription().contains(query)) {
                    filteredList.add(task);
                }
            }
        }

        if (filteredList.isEmpty()) {
            return ("\t No matching task description!");
        } else {
            return "\t Here are the tasks in your list:" + ui.showTasks(filteredList);
        }
    }
}
