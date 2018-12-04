package ru.otus.sua.L11.entity;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

}
