package yappy.command;

import java.time.LocalDateTime;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.exception.YappyException;
import yappy.task.Deadline;
import yappy.ui.Ui;

/**
 * Represents a command to postpone a <code>Deadline</code> task.
 * When executed, it changes the deadline for an existing deadline task
 * any other type of task will return an error.
 */
public class PostponeCommand extends Command {
    private final int index;
    private final LocalDateTime deadline;

    /**
     * Constructor to create new <code>PostponeCommand</code> object
     * and set the index of the task to be postponed and the
     * new deadline.
     * @param index The index of the task in the task list.
     * @param deadline The new deadline.
     */
    public PostponeCommand(int index, LocalDateTime deadline) {
        this.deadline = deadline;
        this.index = index;
    }

    /**
     * Postpone a <code>Deadline</code> task to a new deadline.
     * @param taskList The tasks which have been added to Yappy.
     * @param ui The ui object that controls interactions with a user.
     * @param storage The storage object that handles storing to and reading from disk.
     * @return Message to be printed to user, either success or error.
     * @throws YappyException If the input index is out of bounds.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws YappyException {
        try {
            Deadline task = (Deadline) taskList.getTasks().get(this.index);
            return task.postpone(this.deadline);
        } catch (IndexOutOfBoundsException e) {
            throw new YappyException("Index out of bounds! Try again with a valid index");
        }
    }
}
