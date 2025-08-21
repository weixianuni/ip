public class Events extends Task {

    String when;

    public Events(String description, String when) {
        super(description);
        this.when = when;
    }

    @Override
    public String toString() {
        String from = when.split("/from")[1].split("/to")[0].strip();
        String to = when.split("/to")[1].strip();
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

