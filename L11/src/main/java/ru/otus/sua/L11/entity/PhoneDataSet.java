package ru.otus.sua.L11.entity;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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


}
