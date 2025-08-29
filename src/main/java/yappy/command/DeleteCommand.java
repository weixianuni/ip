package yappy.command;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.exception.YappyException;
import yappy.task.Task;
import yappy.ui.Ui;

public class DeleteCommand extends Command {

    private int index;

    /**
     *
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws YappyException {
        Task removedTask = tasks.delete(index);
        ui.showMessage("\t Alrighty I have removed the following task:\n\t   " + removedTask.toString());
    }
}
