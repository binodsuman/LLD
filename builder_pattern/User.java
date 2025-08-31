package builder_pattern;

public class User {
    private final String firstName;
    private final String lastName;
    private int age;
    private String phone;
    private String address;

    public User(UserBuilder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }


    public static class UserBuilder {
        private final String firstName;
        private final String lastName;
        private int age;
        private String phone;
        private String address;

        // Mandatory attributes
        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public UserBuilder age(int age){
            this.age = age;
            return this;
        }

        public UserBuilder phone(String phone){
            this.phone = phone;
            return this;
        }

        public UserBuilder address(String address){
            this.address = address;
            return this;
        }

        public User build(){
            return new User(this);
        }

    }

    public static void main(String[] args) {
        User user1 = new UserBuilder("abc","dde")
                .address("Bangalore")
                .build();
        System.out.println(user1.firstName);
        System.out.println(user1.lastName);
        System.out.println(user1.age);
        System.out.println(user1.address);
        System.out.println(user1.phone);


    }

}
