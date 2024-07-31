import java.util.*;


public class WorkflowScheduler {


    class Task {
        String name;
        int duration;
        List<Task> dependencies;
        int est = 0; 
        int eft = 0;
        int lst = Integer.MAX_VALUE; 
        int lft = Integer.MAX_VALUE; 


        Task(String name, int duration) {
            this.name = name;
            this.duration = duration;
            this.dependencies = new ArrayList<>();
        }
    }


   private Map<String, Task> tasks;


   public WorkflowScheduler() {
       tasks = new HashMap<>();
   }


   public void addTask(String name, int duration) {
       tasks.put(name, new Task(name, duration));
   }


   public void addDependency(String fromTask, String toTask) {
       Task from = tasks.get(fromTask);
       Task to = tasks.get(toTask);
       if (from != null && to != null) {
           to.dependencies.add(from);
       }
   }


   public void calculateTimes() {
       
       for (Task task : tasks.values()) {
           calculateEFT(task);
       }

       int maxEFT = tasks.values().stream().mapToInt(t -> t.eft).max().orElse(0);

       for (Task task : tasks.values()) {
           task.lft = maxEFT;
           task.lst = maxEFT - task.duration;
       }
       for (Task task : tasks.values()) {
           calculateLST(task);
       }
   }


   private void calculateEFT(Task task) {
       if (task.dependencies.isEmpty()) {
           task.est = 0;
       } else {
           for (Task dependency : task.dependencies) {
               calculateEFT(dependency);
               task.est = Math.max(task.est, dependency.eft);
           }
       }
       task.eft = task.est + task.duration;
   }


   private void calculateLST(Task task) {
       if (task.dependencies.isEmpty()) {
           task.lst = task.lft - task.duration;
       } else {
           for (Task dependency : task.dependencies) {
               dependency.lft = Math.min(dependency.lft, task.lst);
               dependency.lst = dependency.lft - dependency.duration;
               calculateLST(dependency);
           }
       }
   }


   public int getEarliestCompletionTime() {
       return tasks.values().stream().mapToInt(t -> t.eft).max().orElse(0);
   }


   public int getLatestCompletionTime() {
       return tasks.values().stream().mapToInt(t -> t.lft).max().orElse(0);
   }


   public static void main(String[] args) {
       WorkflowScheduler scheduler = new WorkflowScheduler();


       
       scheduler.addTask("T1", 4);
       scheduler.addTask("T2", 2);
       scheduler.addTask("T3", 3);
       scheduler.addTask("T4", 5);
       scheduler.addTask("T5", 7);  
       scheduler.addTask("T6", 1);
       scheduler.addTask("T7", 2);
       scheduler.addTask("T8", 4);  

     
       scheduler.addDependency("T1", "T2");
       scheduler.addDependency("T1", "T3");
       scheduler.addDependency("T2", "T4");
       scheduler.addDependency("T3", "T4");
       scheduler.addDependency("T4", "T5");
       scheduler.addDependency("T5", "T6");
       scheduler.addDependency("T5", "T7");
       scheduler.addDependency("T1", "T8"); 
       scheduler.addDependency("T8", "T6"); 


       scheduler.calculateTimes();


       System.out.println("Earliest Completion Time: " + scheduler.getEarliestCompletionTime());
       System.out.println("Latest Completion Time: " + scheduler.getLatestCompletionTime());
   }
}
