package exercise.modeled;

/**
 * Created by lichen@daojia.com on 2018-6-7.
 */
public class PersonId
{
    private final String id;

    public PersonId(){
        this("");
    }

    public PersonId(String id) {
        this.id = id;
    }


    public String getId(){
        return  id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonId personId = (PersonId) o;

        return id.equals( personId.id );
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "PersonId{" +
                "id='" + id + '\'' +
                '}';
    }
}
