package com.inhome.SandBox;

import com.inhome.SandBox.domain.repository.KnightRepository;
import com.inhome.SandBox.domain.PlayerInformation;
import com.inhome.SandBox.domain.repository.PlayerInformationRepository;
import com.inhome.SandBox.domain.repository.QuestRepository;
import com.inhome.SandBox.domain.repository.RoleRepository;
import com.inhome.SandBox.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.inhome.SandBox.services.QuestService;

import javax.transaction.Transactional;

@Component
@Scope("singleton")
public class Starter implements CommandLineRunner {

    @Autowired
    KnightRepository knightRepository;

    @Autowired
    QuestRepository questRepository;

    @Autowired
    QuestService questService;

    @Autowired
    PlayerInformationRepository playerInformationRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        questRepository.createRandomQuest();
        questRepository.createRandomQuest();

        knightRepository.createKnight("Maciej",25);

        PlayerInformation playerInformation1 = new PlayerInformation("user1","user1");
        playerInformationRepository.createPlayerInformation(playerInformation1);
//        PlayerInformation playerInformation2 = new PlayerInformation("user2","user2");
//        playerInformationRepository.createPlayerInformation(playerInformation2);

        Role user1RoleUSER = new Role("user1","USER");
        Role user2RoleUSER = new Role("user2","USER");
        Role user2RoleADMIN = new Role("user2","ADMIN");

        roleRepository.persistRole(user1RoleUSER);
        roleRepository.persistRole(user2RoleUSER);
        roleRepository.persistRole(user2RoleADMIN);


        questService.assignRandomQuest("Maciej");


    }
}
