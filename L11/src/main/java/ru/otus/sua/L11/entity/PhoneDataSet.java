package ru.otus.sua.L11.entity;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "phones")
public class PhoneDataSet extends DataSet {

    @Basic
    private String phone;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(optional = false)
    private UserDataSet user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PhoneDataSet that = (PhoneDataSet) o;
        return Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPhone());
    }
}
