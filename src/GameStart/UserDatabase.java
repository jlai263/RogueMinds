package GameStart;

import java.util.HashMap;

class UserDatabase {
    private HashMap<String, User> userMap;

    public UserDatabase() {
        userMap = new HashMap<>();
        // Add a sample user to the database
        userMap.put("admin", new User("admin", "password"));
    }

    public void addUser(String username, String password) {
        userMap.put(username, new User(username, password));
    }

    public User getUser(String username) {
        return userMap.get(username);
    }

    public boolean containsUser(String username) {
        return userMap.containsKey(username);
    }
}