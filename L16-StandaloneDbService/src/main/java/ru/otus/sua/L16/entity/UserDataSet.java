package ru.otus.sua.L16.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "users")
public class UserDataSet extends DataSet {

    @Basic
    private String name;

    @Basic
    private int age;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<PhoneDataSet> phones;

    @OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy = "user")
    @JoinColumn(name = "address_id")
    private AddressDataSet address;


    public UserDataSet(String name, int age, List<PhoneDataSet> phones, AddressDataSet address) {
        this.name = name;
        if (age < 0) {
            this.age = 0;
        } else {
            this.age = age;
        }
        this.phones = phones;
        if (phones != null)
            this.getPhones().forEach(phoneDataSet -> phoneDataSet.setUser(this));
        this.address = address;
        if (address != null)
            this.getAddress().setUser(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDataSet that = (UserDataSet) o;
        return getAge() == that.getAge() &&
                Objects.equals(getName(), that.getName()) &&
                CollectionUtils.isEqualCollection(getPhones(), that.getPhones()) &&
                Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getAge(), getPhones(), getAddress());
    }

}
