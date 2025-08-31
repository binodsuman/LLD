package builder_pattern;

public class Person {
    private final String name;
    private final String phone;
    private final String city;
    private final String county;

    public Person(PersonBuilder builder){
        this.name = builder.name;
        this.phone = builder.phone;
        this.city = builder.city;
        this.county = builder.county;
    }

    public static class PersonBuilder{
        private String name;
        private String phone;
        private  String city;
        private  String county;

        public PersonBuilder(String name, String phone){
            this.name = name;
            this.phone = phone;
        }

        public PersonBuilder city(String city){
            this.city = city;
            return this;
        }

        public PersonBuilder county(String country){
            this.county = country;
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }

    public static void main(String[] args) {
        Person person = new Person.PersonBuilder("Binod", "9999").build();
        System.out.println(person.name);
        System.out.println(person.phone);
        System.out.println(person.city);
        System.out.println(person.county);

    }

}
