package com.inhome.SandBox.domain;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class QuestTest {


    @Test
    public void settingStartedFlagToFalseShouldSetStartDate(){

        Quest quest = new Quest("blablabla");
        quest.setStarted(true);

        assertNotNull(quest.startDate);

    }


    @Test
    public void questShouldBeCompleted(){
        Quest quest = new Quest("blablabla");
        quest.setStarted(true);
        quest.lenghtInSeconds = -60;
        quest.isFinished();
        assertTrue(quest.isFinished());

    }


    @Test
    public void questShouldNotBeCompleted(){
        Quest quest = new Quest("blablabla");
        quest.setStarted(true);
        quest.lenghtInSeconds = 200000;
        quest.isFinished();
        assertFalse(quest.isFinished());
    }


}
