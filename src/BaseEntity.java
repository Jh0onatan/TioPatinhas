import java.util.UUID;

public class BaseEntity {
    UUID id;

    public BaseEntity() {
        this.id = UUID.randomUUID();
    }

    public BaseEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() { return this.id; }

    @Override
    public String toString() {
        return "BaseEntity{id=" + id + "}";
    }
}
