package com.book.mcsv.user.repository;

import com.book.mcsv.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByCpfAndKey(String cpf, String key);
    List<User> queryByNomeLike(String name);
}
