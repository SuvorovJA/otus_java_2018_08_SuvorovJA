# ДЗ для курса "Разработчик Java" в OTUS


#### Домашнее задание

На основе ДЗ10:
- [ ] 1. Оформить решение в виде DBService (interface DBService, class DBServiceImpl, UsersDAO, UsersDataSet, Executor)
- [ ] 2. Не меняя интерфейс DBSerivice сделать DBServiceHibernateImpl на Hibernate.
- [ ] 3. Добавить в UsersDataSet поля:

адрес (OneToOne) 
```
class AddressDataSet{
    private String street;
}
```

и телефон (OneToMany)
```
class PhoneDataSet{
    private String number;
}
```

Добавить соответствущие датасеты и DAO. 


##### Решение




