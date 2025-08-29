package yappy.task;

/**
 *
 */
public class Task {
    boolean isCompleted;
    String description;

    public Task(String description, Boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     *
     */
    public void setCompleted() {
        this.isCompleted = true;
    }

    /**
     *
     */
    public void setUncompleted() {
        this.isCompleted = false;
    }

    /**
     *
     * @return
     */
    public String fileString() {
        return "";
    }

    public String toString() {
        return "[" + (this.isCompleted ? "X" : " ") + "] " + this.description;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean completed() {
        return this.isCompleted;
    }
}