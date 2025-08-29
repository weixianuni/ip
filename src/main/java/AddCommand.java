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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws YappyException {
        tasks.add(task);
        ui.showMessage("\t Alrighty I have removed the following task:\n\t   " + this.task.toString());
    }
}
