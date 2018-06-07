package exercise.modeled;

import java.util.Objects;

/**
 * Created by lichen@daojia.com on 2018-6-7.
 */
public class PersonModel
{
    private final PersonId id;

    private final ContainerType containerType;

    private final String firstName;

    private final String lastName;

    private final int age;


    public PersonModel() {
        this(new PersonId( ),new ContainerType (),null,null,0);
    }

    public PersonModel(PersonId id, ContainerType containerType, String firstName, String lastName, int age) {
        this.id = Objects.requireNonNull(id,"id cannot be null");
        this.containerType = Objects.requireNonNull(containerType, "containerType cannot be null");
        this.firstName = Objects.requireNonNull(firstName, "firstName cannot be null");
        this.lastName = Objects.requireNonNull(lastName, "lastName cannot be null");
        this.age = age;
    }

    public PersonId getId() {
        return id;
    }

    public ContainerType getContainerType() {
        return containerType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonModel that = (PersonModel) o;

        if (age != that.age) return false;
        if (id != null ? !id.equals( that.id ) : that.id != null) return false;
        if (containerType != null ? !containerType.equals( that.containerType ) : that.containerType != null)
            return false;
        if (firstName != null ? !firstName.equals( that.firstName ) : that.firstName != null) return false;
        return lastName != null ? lastName.equals( that.lastName ) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (containerType != null ? containerType.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "PersonModel{" +
                "id=" + id +
                ", containerType=" + containerType +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
