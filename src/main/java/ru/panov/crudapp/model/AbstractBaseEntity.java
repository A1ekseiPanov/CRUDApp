package ru.panov.crudapp.model;

import java.util.Objects;

import static java.util.Objects.hash;

public abstract class AbstractBaseEntity {
    protected Integer id;
    protected Status status;

    protected AbstractBaseEntity() {
        this.status = Status.ACTIVE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return Objects.equals(id, that.id) && status == that.status;
    }

    @Override
    public int hashCode() {
        return hash(id, status);
    }
}