package net.josborn.bowling

class ScoreSheet(frames: MutableList<Frame> = getFrames()) {
    private var mShots = mutableListOf<Shot>()
    var mTenFrames = frames

    fun getScore(toFrame: Int = 10): Int {
        mShots = mTenFrames.filter { it.mFrameNumber <= toFrame }.flatMap { it.getShots() } as MutableList<Shot>
        val sortedShots = mShots.sortedWith(compareBy({ it.mFrame }, { it.mPositionInFrame }))
        var score = sortedShots.mapNotNull { it.mNumberOfPinsDowned }.sum()
        val maxIndex = sortedShots.size - 1
        sortedShots.forEachIndexed { index, shot ->
            run {
                val oneShotFromNow = index + 1
                val twoShotsFromNow = index + 2
                if(shot.mFrame!=10) {
                    if (shot.mStatus == ShotStatus.Strike) {
                        if (maxIndex >= oneShotFromNow && sortedShots[oneShotFromNow].mNumberOfPinsDowned != null)
                            score += sortedShots[oneShotFromNow].mNumberOfPinsDowned!!
                        if (maxIndex >= twoShotsFromNow && sortedShots[twoShotsFromNow].mNumberOfPinsDowned != null)
                            score += sortedShots[twoShotsFromNow].mNumberOfPinsDowned!!
                    } else if (shot.mStatus == ShotStatus.Spare && maxIndex >= oneShotFromNow && sortedShots[oneShotFromNow].mNumberOfPinsDowned != null)
                        score += sortedShots[oneShotFromNow].mNumberOfPinsDowned!!
                }
            }
        }
        return score
    }

    /**
     * gets 10 blank frames
     */
    companion object {
        fun getFrames(): MutableList<Frame> {
            val frames = mutableListOf<Frame>()
            for (f in 1..10) {
                frames.add(Frame(f))
            }
            return frames
        }
    }
}
