package com.balamut.yejournal.school;

import com.balamut.yejournal.authentication.entity.User;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private School school;
}
