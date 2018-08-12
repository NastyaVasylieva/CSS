package domain;
/*import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
*/

public class Person {
    public enum Gender {
        MALE, FEMALE
    }

    private int id;
    private String firstName;
    private String lastName;
    private Gender gender;

    public Person() {
    }


    public Person(String firstName, String lastName, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Person(int id, String firstName, String lastName, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
