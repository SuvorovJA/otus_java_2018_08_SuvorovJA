package ru.otus.sua.L12.entity;

import lombok.*;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
@Entity
@Table(name = "users")
public class UserDataSet extends DataSet {

    @Basic
    private String name;

    @Basic
    private int age;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "user")
    private List<PhoneDataSet> phones;

    @OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy = "user")
    @JoinColumn(name = "address_id")
    private AddressDataSet address;


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
