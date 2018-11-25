package ru.otus.sua.L10.entity;

import lombok.Data;

//@EqualsAndHashCode(callSuper = true)
@Data
public class UberUserDataSet extends UserDataSet {

    private String armedBy;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "(id=" + this.getId() +
                ";name=" + this.getName() +
                ";age=" + this.getAge() +
                ";armedBy=" + this.armedBy +
                ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UberUserDataSet) {
            UberUserDataSet u = (UberUserDataSet) o;
            return (this.getId() == u.getId()) &&
                    (this.getName().equals(u.getName())) &&
                    (this.getAge() == u.getAge()) &&
                    (this.armedBy.equals(u.getArmedBy()));
        }
        return false;
    }

}
