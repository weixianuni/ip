public class Deadline extends Task {
    protected String by;

    public Deadline(String description, Boolean isCompleted, String by) {
        super(description, isCompleted);
        this.by = by;
    }

    @Override
    public String fileString() {
        return "T | " + this.isCompleted + " | " + this.description + " | " + this.by + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
