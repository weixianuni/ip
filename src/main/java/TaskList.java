import java.sql.Array;
import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks;

    /**
     *
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     *
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     *
     * @param task
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     *
     * @param index
     * @return
     */
    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    /**
     *
     * @param index
     * @return
     */
    public Task mark(int index) {
        Task task = this.tasks.get(index);
        task.setCompleted();
        return task;
    }

    /**
     *
     * @param index
     * @return
     */
    public Task unmark(int index) {
        Task task = this.tasks.get(index);
        task.setUncompleted();
        return task;
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(this.tasks);
    }
}
