package yappy.command;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.exception.YappyException;
import yappy.task.Task;
import yappy.ui.Ui;

public class MarkCommand extends Command {

    private int index;

    /**
     *
     * @param index
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws YappyException {
        Task markedTask = tasks.mark(index);
        ui.showMessage("\t Alrighty I have marked this task as completed:\n\t   " + markedTask.toString());
    }
}
