# Nextiva_Assessment

# Problem statement

We have a large and complex workflow, made of tasks. And have to decide who does what, when, so it all gets done on time.
All tasks have dependency on other tasks to complete
       Each task(t) has a
   - D[t] = duration of task
   - EFT[t] = the earliest finish time for a task
   - LFT[t] = the latest finish time for a task
   - EST[t] = the earliest start time for a task
   - LST[t] = the last start time for a task
   Assume
       that “clock” starts at 0 (project starting time).
       We are given the starting task T_START where the EST[t] = 0 and LST[t] = 0
       You have to write an Java/Python/JS/Typescript console application to answer the following questions
       - Earliest time all the tasks will be completed?
       - Latest time all tasks will be completed?



#### Earliest Finish Time Calculation (Forward BFS):

Use BFS to traverse the graph from the starting task.
For each task t:
- Calculate EST[t] as the maximum of EFT of all its predecessor tasks.
- Calculate EFT[t] as EST[t] + D[t].
- This traversal determines the earliest times each task can be completed.

#### Latest Finish Time Calculation (Backward BFS):

Start from the task with the maximum EFT (the earliest time the project can be completed).
Use BFS to traverse the graph backward.
For each task t:
- Calculate LFT[t] as the minimum of LST of all its successor tasks.
- Calculate LST[t] as LFT[t] - D[t].
-This traversal determines the latest times each task can start and finish without delaying the overall project.





