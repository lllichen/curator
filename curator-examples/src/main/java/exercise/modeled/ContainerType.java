package exercise.modeled;

/**
 * Created by lichen@daojia.com on 2018-6-7.
 */
public class ContainerType
{
    public ContainerType(int typeId) {
        this.typeId = typeId;
    }

    private final int typeId;

    public ContainerType() {
        this(0);
    }

    public int getTypeId() {
        return typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerType that = (ContainerType) o;

        return typeId == that.typeId;
    }

    @Override
    public int hashCode() {
        return typeId;
    }

    @Override
    public String toString() {
        return "ContainerType{" +
                "typeId=" + typeId +
                '}';
    }
}
