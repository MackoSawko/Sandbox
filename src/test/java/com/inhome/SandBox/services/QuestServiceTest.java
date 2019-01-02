package com.inhome.SandBox.services;

import com.inhome.SandBox.domain.Quest;
import com.inhome.SandBox.domain.repository.QuestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestServiceTest {

    @Mock
    QuestRepository repositoryMock;

    @Test
    public void returnsNotStartedQuests(){
        List<Quest> quests = new ArrayList<>();

        Quest q1 = new Quest("Quest 1");
        Quest q2 = new Quest("Quest 1");
        Quest q3 = new Quest("Quest 1");

        q2.setStarted(true);

        quests.add(q1);
        quests.add(q2);
        quests.add(q3);

        when(repositoryMock.getAllQuests()).thenReturn(quests);

        QuestService qs = new QuestService();
        qs.setQuestRepository(repositoryMock);

        List<Quest> allNotStartedQuests = qs.getAllNotStartedQuests();

        assertEquals(2,allNotStartedQuests.size());

    }


}
