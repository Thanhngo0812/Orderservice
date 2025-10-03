package entity;

public abstract class AggregateRoot<ID> extends BaseEntity<ID> {
    // Có thể thêm các domain events ở đây nếu cần
    // private List<DomainEvent> domainEvents;
}