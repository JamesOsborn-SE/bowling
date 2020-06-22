package net.josborn.bowling

import org.junit.Test

import org.junit.Assert.*

/**
 * Test for different bowling games partial and complete
 * mostly to verify we are dealing with the 10th frame properly.
 */
class GameUnitTest {
    @Test
    fun partialGame1() {
        val scoreSheet = ScoreSheet()
        scoreSheet.mTenFrames.elementAt(0).mFirstShot.mNumberOfPinsDowned = 1
        scoreSheet.mTenFrames.elementAt(0).mSecondShot.mNumberOfPinsDowned = 9
        scoreSheet.mTenFrames.elementAt(1).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(2).mFirstShot.mNumberOfPinsDowned = 0
        scoreSheet.mTenFrames.elementAt(3).mFirstShot.mNumberOfPinsDowned = 0
        assertEquals(30,scoreSheet.getScore())
    }
    @Test
    fun partialGame2() {
        val scoreSheet = ScoreSheet()
        scoreSheet.mTenFrames.elementAt(0).mFirstShot.mNumberOfPinsDowned = 1
        scoreSheet.mTenFrames.elementAt(1).mSecondShot.mNumberOfPinsDowned = 8
        scoreSheet.mTenFrames.elementAt(2).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(3).mFirstShot.mNumberOfPinsDowned = 8
        scoreSheet.mTenFrames.elementAt(4).mFirstShot.mNumberOfPinsDowned = 1
        assertEquals(37,scoreSheet.getScore())
    }
    @Test
    fun partialGameSpareMe() {
        val scoreSheet = ScoreSheet()
        scoreSheet.mTenFrames.elementAt(0).mFirstShot.mNumberOfPinsDowned = 0
        scoreSheet.mTenFrames.elementAt(1).mSecondShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(2).mSecondShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(3).mSecondShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(4).mSecondShot.mNumberOfPinsDowned = 10
        assertEquals(70,scoreSheet.getScore())
    }

    @Test
    fun perfectGame() {
        val scoreSheet = ScoreSheet()
        scoreSheet.mTenFrames.elementAt(0).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(1).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(2).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(3).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(4).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(5).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(6).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(7).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(8).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(9).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(9).mSecondShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(9).mThirdShot?.mNumberOfPinsDowned = 10
        assertEquals(300,scoreSheet.getScore())
    }
    @Test
    fun nighPerfectGame() {
        val scoreSheet = ScoreSheet()
        scoreSheet.mTenFrames.elementAt(0).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(1).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(2).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(3).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(4).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(5).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(6).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(7).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(8).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(9).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(9).mSecondShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(9).mThirdShot?.mNumberOfPinsDowned = 9
        assertEquals(299,scoreSheet.getScore())
    }
    @Test
    fun tenthFrameMiddleMissingStrike() {
        val scoreSheet = ScoreSheet()
        /**
         * for the index
         * frame - 1
         */
        val index = 10 - 1
        scoreSheet.mTenFrames.elementAt(index).mFirstShot.mNumberOfPinsDowned = 10
        scoreSheet.mTenFrames.elementAt(index).mSecondShot.mNumberOfPinsDowned = 1
        scoreSheet.mTenFrames.elementAt(index).mThirdShot?.mNumberOfPinsDowned = 10
        assertEquals(10,scoreSheet.mTenFrames.elementAt(index).howManyExtraPins())
    }
    @Test
    fun tenthFrameOpen() {
        val scoreSheet = ScoreSheet()
        /**
         * for the index
         * frame - 1
         */
        val index = 10 - 1
        scoreSheet.mTenFrames.elementAt(index).mFirstShot.mNumberOfPinsDowned = 1
        scoreSheet.mTenFrames.elementAt(index).mSecondShot.mNumberOfPinsDowned = 3
        scoreSheet.mTenFrames.elementAt(index).mThirdShot?.mNumberOfPinsDowned = 0
        assertEquals(0,scoreSheet.mTenFrames.elementAt(index).howManyExtraPins())
    }
    @Test
    fun tenthFrameSpare() {
        val scoreSheet = ScoreSheet()
        /**
         * for the index
         * frame - 1
         */
        val index = 10 - 1
        scoreSheet.mTenFrames.elementAt(index).mFirstShot.mNumberOfPinsDowned = 1
        scoreSheet.mTenFrames.elementAt(index).mSecondShot.mNumberOfPinsDowned = 9
        scoreSheet.mTenFrames.elementAt(index).mThirdShot?.mNumberOfPinsDowned = 10
        assertEquals(0,scoreSheet.mTenFrames.elementAt(index).howManyExtraPins())
    }
    @Test
    fun tenthFrameMiss() {
        val scoreSheet = ScoreSheet()
        /**
         * for the index
         * frame - 1
         */
        val index = 10 - 1
        scoreSheet.mTenFrames.elementAt(index).mFirstShot.mNumberOfPinsDowned = 1
        scoreSheet.mTenFrames.elementAt(index).mSecondShot.mNumberOfPinsDowned = 8
        scoreSheet.mTenFrames.elementAt(index).mThirdShot?.mNumberOfPinsDowned = 10
        assertEquals(10,scoreSheet.mTenFrames.elementAt(index).howManyExtraPins())
    }
    @Test
    fun tooManyFrameShot1() {
        val scoreSheet = ScoreSheet()
        /**
         * for the index
         * frame - 1
         */
        val index = 1 - 1
        scoreSheet.mTenFrames.elementAt(index).mFirstShot.mNumberOfPinsDowned = 11
        scoreSheet.mTenFrames.elementAt(index).mSecondShot.mNumberOfPinsDowned = 8
        assertEquals(9,scoreSheet.mTenFrames.elementAt(index).howManyExtraPins())
    }
    @Test
    fun tooManyFrameShot2() {
        val scoreSheet = ScoreSheet()
        /**
         * for the index
         * frame - 1
         */
        val index = 1 - 1
        scoreSheet.mTenFrames.elementAt(index).mFirstShot.mNumberOfPinsDowned = 0
        scoreSheet.mTenFrames.elementAt(index).mSecondShot.mNumberOfPinsDowned = 33
        assertEquals(23,scoreSheet.mTenFrames.elementAt(index).howManyExtraPins())
    }
    @Test
    fun tooManyFrameShot3() {
        val scoreSheet = ScoreSheet()

        /**
         * for the index
         * frame - 1
         */
        val index = 10 - 1
        scoreSheet.mTenFrames.elementAt(index).mThirdShot?.mNumberOfPinsDowned = 55
        assertEquals(55,scoreSheet.mTenFrames.elementAt(index).howManyExtraPins())
    }
}