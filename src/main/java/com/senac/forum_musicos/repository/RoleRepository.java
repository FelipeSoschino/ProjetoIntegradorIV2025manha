package com.senac.forum_musicos.repository;

import com.senac.forum_musicos.entity.Role;
import com.senac.forum_musicos.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleName name);
}
