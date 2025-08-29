public class UnmarkCommand extends Command {

    private int index;

    /**
     *
     * @param index
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws YappyException {
        Task unmarkedTask = tasks.unmark(index);
        ui.showMessage("\t Alrighty I have marked this task as completed:\n\t  " + unmarkedTask.toString());
    }
}
