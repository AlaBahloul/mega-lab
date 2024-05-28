package com.example.IDOR.Repository;

import com.example.IDOR.Entity.IDORUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<IDORUser, Integer> {


    IDORUser findById(int id);
    IDORUser findByUsername(String username);
}
