import java.util.Scanner;


public class Yappy {

    public static void printFormat(String[] messages) {
        System.out.println("\t____________________________________________________________");
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) throws YappyException{

        Task[] library = new Task[100];
        int numOfItems = 0;

        printFormat(new String[]{"\t\sHello! I'm Yappy\n" + "\t\sWhat can I do for you?"});

        Scanner input = new Scanner(System.in);
        while (true) {
            String inputLine = input.nextLine();
            if (inputLine.equals("bye")) {
                break;
            } else if (inputLine.equals("list")) {

                String[] messages = new String[numOfItems + 1];
                messages[0] = "\t\sHere are the tasks in your list:";
                for (int i = 0; i < numOfItems; i++) {
                    int num = i + 1;
                    Task task = library[i];
                    messages[i + 1] = "\t\s" + num + "." + task.toString();
                }
                printFormat(messages);
            } else if (inputLine.startsWith("mark")) {
                if (!inputLine.trim().contains(" ")) {
                    throw new YappyException("Please specify the index of the task you wish to mark as completed!");
                } else if (Integer.parseInt(inputLine.split(" ")[1]) - 1 > numOfItems) {
                    throw new YappyException("Please specify a valid task index.\n\t\sNow you have " + numOfItems + (numOfItems==1 ? " task ": " tasks ") + "in the list.");
                }
                int index = Integer.parseInt(inputLine.split(" ")[1]) - 1;
                library[index].setCompleted();
                printFormat(new String[]{"\t\sAlrighty I have marked this task as completed:" + "\n\t\s\s" + library[index].toString()});
            } else if (inputLine.startsWith("unmark")) {
                if (!inputLine.trim().contains(" ")) {
                    throw new YappyException("Please specify the index of the task you wish to mark as uncompleted!");
                }
                int index = Integer.parseInt(inputLine.split(" ")[1]) - 1;
                library[index].setUncompleted();
                printFormat(new String[]{"\t\sAlrighty I have marked this task as not completed yet:" + "\n\t\s\s" + library[index].toString()});
            } else {
                if (inputLine.startsWith("todo")) {
                    if (inputLine.length() == 1) {
                        throw new YappyException("Please specify the ");
                    }
                    String description = inputLine.substring("todo".length()).trim();
                    library[numOfItems] = new ToDo(description);
                } else if (inputLine.startsWith("event")) {
                    String description = inputLine.substring("event".length()).trim().split("/from")[0];
                    String when = inputLine.split("/from")[1];
                    library[numOfItems] = new Event(description, when);
                } else if (inputLine.startsWith("deadline")) {
                    String description = inputLine.substring("deadline".length()).trim().split("/by")[0].strip();
                    String by = inputLine.split("/by")[1].strip();
                    library[numOfItems] = new Deadline(description, by);
                }

                numOfItems++;


                printFormat(new String[]{"\t\sAdded the following task:\n\t\s\s\s" + library[numOfItems - 1].toString(),
                                        "\t\sNow you have " + numOfItems + (numOfItems==1 ? " task ": " tasks ") + "in the list."});
            }
        }

        printFormat(new String[]{"\tBye. Hope to see you again soon!"});

    }
}
