package com.inhome.SandBox.domain;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class KnightTest {

    @Test
    public void testIfQuestMarkedAssStarted() {
        Knight knight = new Knight("Maciej", 25);
        Quest quest = new Quest("gowno");
        knight.setQuest(quest);
        assertTrue(knight.getQuest().isStarted());
    }


}
