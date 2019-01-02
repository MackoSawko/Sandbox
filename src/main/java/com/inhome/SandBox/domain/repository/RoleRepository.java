package com.inhome.SandBox.domain.repository;

import com.inhome.SandBox.utils.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleRepository {

    @PersistenceContext
    private EntityManager em;

    public void persistRole(Role role){
        em.persist(role);
    }

    public List<Role> getAll() {
        return em.createQuery("from Role", Role.class).getResultList();
    }
}
