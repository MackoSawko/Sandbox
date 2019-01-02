package com.inhome.SandBox.domain.repository;

import com.inhome.SandBox.domain.Knight;
import com.inhome.SandBox.domain.Quest;
import com.inhome.SandBox.utils.Ids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.Collection;
import java.util.Optional;

@Repository
public class KnightRepository {

    @PersistenceContext()
    private EntityManager em;


    @Transactional
    public void createKnight(String name, int age) {

        Knight knight = new Knight(name,age);

        em.persist(knight);

    }


    public Collection<Knight> getAllKnights() {

        return  em.createQuery("from Knight", Knight.class).getResultList();
    }


    public Optional<Knight> getKnight(String name) {

        Knight knightByName = em.createQuery("from Knight k where k.name=:name", Knight.class)
                .setParameter("name", name).getSingleResult();

        return Optional.ofNullable(knightByName);
    }

    @Transactional
    public void deleteKnight(Integer id) {
        em.remove(getKnightById(id));
    }


    public void build() {

    }


    @Transactional
    public void createKnight(Knight knight) {
        em.persist(knight);
    }

    public Knight getKnightById(Integer id) {
        return em.find(Knight.class, id);
    }



    @Transactional
    public void updateKnight(int id, Knight knight) {
        em.merge(knight);
    }

}
