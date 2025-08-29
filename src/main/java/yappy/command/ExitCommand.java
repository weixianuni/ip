package yappy.command;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.exception.YappyException;
import yappy.ui.Ui;

public class ExitCommand extends Command {


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks.getTasks());
        } catch (YappyException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public Boolean isExit() {
        return true;
    }
}
