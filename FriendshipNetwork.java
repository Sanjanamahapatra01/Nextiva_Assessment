import java.util.*;


class FriendshipNetwork {
   private final Map<String, Set<String>> network;


   public FriendshipNetwork() {
       this.network = new HashMap<>();
   }


   public void addFriend(String person, String friend) {
       network.computeIfAbsent(person, k -> new HashSet<>()).add(friend);
       network.computeIfAbsent(friend, k -> new HashSet<>()).add(person);
   }


   public Set<String> getFriends(String person) {
       return network.getOrDefault(person, Collections.emptySet());
   }


   public Set<String> commonFriends(String person1, String person2) {
       Set<String> friends1 = getFriends(person1);
       Set<String> friends2 = getFriends(person2);
       Set<String> common = new HashSet<>(friends1);
       //here we are calculating the common friends
       common.retainAll(friends2);
       return common;
   }


   //To get the connection we will use BFS Here(BFS is implemented Using Queue Here)
   public int connectionDistance(String start, String end) {
       if (!network.containsKey(start) || !network.containsKey(end)) {
           return -1;
       }


       Set<String> visited = new HashSet<>();
       Queue<Map.Entry<String, Integer>> queue = new LinkedList<>();
       queue.add(new AbstractMap.SimpleEntry<>(start, 0));


       while (!queue.isEmpty()) {
           Map.Entry<String, Integer> current = queue.poll();
           String person = current.getKey();
           int degree = current.getValue();


           if (person.equals(end)) {
               return degree;
           }


           if (visited.add(person)) {
               for (String friend : network.getOrDefault(person, Collections.emptySet())) {
                   if (!visited.contains(friend)) {
                       queue.add(new AbstractMap.SimpleEntry<>(friend, degree + 1));
                   }
               }
           }
       }


       return -1;
   }


   public static void main(String[] args) {
       FriendshipNetwork network = new FriendshipNetwork();


       // Add friends
       network.addFriend("Alice", "Bob");
       network.addFriend("Bob", "Charlie");
       network.addFriend("Charlie", "David");
       network.addFriend("Bob", "Janice");


       // Find all friends
       System.out.println("Alice's friends: " + network.getFriends("Alice"));
       System.out.println("Bob's friends: " + network.getFriends("Bob"));


       // Find common friends between Alice and Bob
       System.out.println("Common friends of Alice and Bob: " + network.commonFriends("Alice", "Bob"));


       // Find connection degree
       System.out.println("Connection Distance between Alice and Janice: " + network.connectionDistance("Alice", "Janice"));
       System.out.println("Connection Distance between Alice and Bob: " + network.connectionDistance("Alice", "Bob"));
       System.out.println("Connection Distance between Alice and David: " + network.connectionDistance("Alice", "David"));
       System.out.println("Connection Distance between Alice and Nonexistent: " + network.connectionDistance("Alice", "Nonexistent"));
   }
}





//Overall Time Complexity:
//
//   addFriend: O(1)
//   getFriends: O(1)
//   commonFriends: O(min(|friends1|, |friends2|))
//   connectionDegree: O(n + m)  here n denotes the no of people and m denotes the friendship
//
//Overall Space Complexity:
//
//   O(n + m) for storing the network data.
