import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class TaskManager {
    private final Path FILE_PATH = Path.of("tasks.json");


    private List<Task> tasks = new ArrayList<>();

    public TaskManager() {
        this.tasks = loadTasks();
        this.listTasks("All");
    }

    private List<Task> loadTasks(){
        List<Task> stored_tasks = new ArrayList<>();
        if(!Files.exists(FILE_PATH)){
            System.out.println(FILE_PATH);
            System.out.println("File found: " + Files.exists(FILE_PATH));
            return new ArrayList<>();
        }
        try{
            String jsonContent = Files.readString(FILE_PATH);

            String[] taskList = jsonContent.replace("[", "").replace("]", "").split(("},"));

            for (String taskJson : taskList) {
                if(!taskJson.isBlank()){
                    if (!taskJson.endsWith("}")) {
                        taskJson = taskJson + "}";
                        stored_tasks.add(Task.fromJson(taskJson));
                    } else {
                        stored_tasks.add(Task.fromJson(taskJson));
                    }
                }
            }

        }catch (IOException e) {
            System.out.println("Unable to read Json file!!!");
        }
        return stored_tasks;
    }

    public void saveTasks(){
        StringBuilder sb = new StringBuilder();

        sb.append("[\n");

        for(int i =0; i<tasks.size(); i++){
            sb.append(tasks.get(i).toJson());
            if(i<tasks.size() -1 ){
                sb.append(",\n");
            }
        }
        sb.append("\n]");

        String jsonContent = sb.toString();

        try{
            Files.writeString(FILE_PATH, jsonContent);
        } catch (IOException e) {
            System.out.println("Unable to write to file!!!");
        }
    }


    public void addTask(Scanner scanner){
        System.out.print("Enter Title of the task: ");
        scanner.nextLine();
        String title = scanner.nextLine();

        System.out.print("Enter description of the task: ");
        String description = scanner.nextLine();

        Task task = new Task(title, description);
        tasks.add(task);
        System.out.println("Added");
    }

    public void listTasks(String type){
        for( Task task: tasks){
            String status = task.getStatus().toString().strip();
            if(type.equals("All") || status.equals(type)){
                System.out.println(task);
            }
        }
    }

    public void updateTask(Scanner scanner){
        System.out.print("Enter id of task to update: ");
        scanner.nextLine();

        String id = scanner.nextLine();

        System.out.print("Enter title to update: ");

        String updatedTitle = scanner.nextLine();

        System.out.print("Enter description to update: ");
        String updatedDescription = scanner.nextLine();

        Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with Id " +  id + " not found!" ));
        if(updatedTitle != null && !updatedTitle.trim().isEmpty()){
            task.updateTitle(updatedTitle);
        }

        if(updatedDescription!=null && !updatedDescription.trim().isEmpty()){
            task.updateDescription(updatedDescription);
        }

        System.out.println("Updated");

    }

    public void deleteTask(Scanner scanner){
        System.out.print("Enter id of task to delete: ");
        scanner.nextLine();

        String id = scanner.nextLine();

        Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with Id " +  id + " not found!" ));

        tasks.remove(task);
        System.out.println("Deleted");

    }

    public void updateStatus(Scanner scanner){
        System.out.print("Enter id of the task to update the status: ");
        scanner.nextLine();
        String id  = scanner.nextLine();

        Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with Id " +  id + " not found!" ));

        System.out.println("Current status: " + task.getStatus());

        System.out.print("Enter 1.In progress 2.Done: ");

        int choice = scanner.nextInt();

        if(choice == 1){
            task.markInProgress();
            System.out.println("Task marked as in progress");
        }else if(choice == 2){
            task.markDone();
            System.out.println("Task marked as done");
        } else{
            System.out.println("Invalid Choice!!!");
        }

    }

    public Optional<Task> findTask(String id){
        return  tasks.stream().filter((task) -> task.getId() == Integer.parseInt(id)).findFirst();
    }
}
