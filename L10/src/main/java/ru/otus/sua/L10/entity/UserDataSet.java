package ru.otus.sua.L10.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDataSet extends DataSet {
    private String name;
    private int age;
}
