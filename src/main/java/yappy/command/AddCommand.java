package yappy.command;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.task.Task;
import yappy.ui.Ui;

public class AddCommand extends Command {

    private Task task;

    /**
     *
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showMessage("\t Alrighty I have added the following task:\n\t   " + this.task.toString());
    }
}
