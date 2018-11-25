package ru.otus.sua.L10.entity;

import lombok.Data;

//@EqualsAndHashCode(callSuper = true)
@Data
public class UserDataSet extends DataSet {
    private String name;
    private int age;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(id=" + this.getId() + ";name=" + this.name + ";age=" + this.age + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UserDataSet) {
            UserDataSet u = (UserDataSet) o;
            return (this.getId() == u.getId()) && (this.name.equals(u.name)) && (this.age == u.getAge());
        }
        return false;
    }

}
