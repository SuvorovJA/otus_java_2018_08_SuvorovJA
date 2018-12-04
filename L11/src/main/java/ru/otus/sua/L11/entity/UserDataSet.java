package ru.otus.sua.L11.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
