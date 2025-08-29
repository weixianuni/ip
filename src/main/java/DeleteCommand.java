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
