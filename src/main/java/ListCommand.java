public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws YappyException {
        ui.showTasks(tasks.getTasks());
    }
}
