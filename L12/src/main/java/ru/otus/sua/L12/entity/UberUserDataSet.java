package ru.otus.sua.L12.entity;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
public class UberUserDataSet extends UserDataSet {

    @Basic
    private String armedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UberUserDataSet that = (UberUserDataSet) o;
        return Objects.equals(getArmedBy(), that.getArmedBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getArmedBy());
    }
}
