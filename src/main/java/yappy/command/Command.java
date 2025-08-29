package yappy.command;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.exception.YappyException;
import yappy.ui.Ui;

public abstract class Command {

    /**
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws YappyException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws YappyException;

    /**
     *
     * @return
     */
    public Boolean isExit() {
        return false;
    }

}
