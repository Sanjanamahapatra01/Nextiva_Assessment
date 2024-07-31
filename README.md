# Nextiva_Assessment

# Problem statement

Both Alice & Bob have friends. Create a Java/Python/JS/Typescript console application to find all the friends of Alice and all the friends of Bob & common friends of Alice and Bob.
 Your algorithm should be able to do the following:
 - Take any 2 friends and find the common friends between the 2 friends
 - Take any 2 friends find the nth connection - for example: connection(Alice, Janice) => 2
  > Alice has friend Bob and Bob has friend Janice, if the input given is Alice and Janice the output should be 2, meaning 2nd connection, that means Janice is the second connection of Alice and Bob being the 1st connection of Alice.
  Likewise if input given is Alice and Bob, the output should be 1, for 1st connection
  - If there is no connection at all, it should return -1


## Tree Diagram for better understanding :
                                      Alice
                                        |
                                        |
                                       Bob
                                        |  \
                                        |   \
                                   Charlie   Janice
                                         |
                                         |
                                       David





FriendshipNetwork Class:
- network: A Map<String, Set<String>> that represents the social network. Each key is a person's name, and the value is a set of their friends' names.
- addFriend: Adds a friendship between two people. This function ensures that both people are in each other's friend list.
- getFriends: Returns a set of friends for a given person, or an empty set if the person is not in the network.
- commonFriends: Finds common friends between two people by intersecting their friend sets.
- connectionDistance: Uses a breadth-first search (BFS) algorithm to find the shortest path (degrees of separation) between two people. Returns -1 if there is no connection.
