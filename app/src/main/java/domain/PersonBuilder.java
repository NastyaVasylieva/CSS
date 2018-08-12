package domain;

/*import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
//
//@JsonPOJOBuilder(buildMethodName="create")
*/
public class PersonBuilder {
    private int id;
    private String firstName;
    private String lastName;
    private Person.Gender gender;


    public PersonBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public PersonBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder setGender(Person.Gender gender) {
        this.gender = gender;
        return this;
    }


    public Person create() {
        return new Person(id, firstName, lastName, gender);
    }
}