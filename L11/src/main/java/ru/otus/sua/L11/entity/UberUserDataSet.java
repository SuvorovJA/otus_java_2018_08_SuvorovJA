package ru.otus.sua.L11.entity;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
public class UberUserDataSet extends UserDataSet {

    @Basic
    private String armedBy;

}
