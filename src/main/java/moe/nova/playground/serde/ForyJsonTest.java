package moe.nova.playground.serde;

import org.apache.fory.json.ForyJson;

import java.util.Optional;

public class ForyJsonTest {

    private static final ForyJson JSON = ForyJson.builder().build();

    public static final class User {
        public long id;
        public String name;

        public User() {
        }

        User(long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static void main(String[] args) {
        User input = new User(7, "Alice");

        String text = JSON.toJson(input);
        byte[] utf8 = JSON.toJsonBytes(input);

        User fromText = JSON.fromJson(text, User.class);
        User fromUtf8 = JSON.fromJson(utf8, User.class);

        System.out.println(text);          // {"id":7,"name":"Alice"}
        System.out.println(fromText.name); // Alice
        System.out.println(fromUtf8.name); // Alice

        Optional<User> a = Optional.of(input);
        Optional<User> b = Optional.empty();
        System.out.println(JSON.toJson(a));
        System.out.println(JSON.toJson(b));
    }
}