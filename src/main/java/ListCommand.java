public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws YappyException {
        ui.showMessage("\t Here are the tasks in your list:");
        ui.showTasks(tasks.getTasks());
    }
}
