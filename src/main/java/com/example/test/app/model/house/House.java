package com.example.test.app.model.house;

import com.example.test.app.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "houses")
@NoArgsConstructor
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner", referencedColumnName = "id") //Подразумеваю, что один пользователь может быть владельцем нескольких домов
    private User owner;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "users_houses", joinColumns = @JoinColumn(name = "house_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;
}
