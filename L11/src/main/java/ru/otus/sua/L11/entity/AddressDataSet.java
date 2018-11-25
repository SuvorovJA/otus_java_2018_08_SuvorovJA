package ru.otus.sua.L11.entity;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "address")
public class AddressDataSet extends ru.otus.sua.L11.entity.DataSet {

    @Basic
    private String street;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne(optional = false)
    private UserDataSet user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AddressDataSet that = (AddressDataSet) o;
        return Objects.equals(getStreet(), that.getStreet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStreet());
    }
}
