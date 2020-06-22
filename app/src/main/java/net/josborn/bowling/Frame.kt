package net.josborn.bowling

/**
 * Frame for Score card
 * @param frameNumber is the human readable frame number 1-10
 */
class Frame(frameNumber: Int) {
    val mFrameNumber = frameNumber
    var mFirstShot: Shot = Shot(frameNumber, 1)
    var mSecondShot: Shot = Shot(frameNumber, 2)
    var mThirdShot: Shot? = when (frameNumber) {
        10 -> Shot(frameNumber, 3)
        else -> null
    }
    /**
     * provides validation and number of pins that should not be in the frame.
     * TODO split up validation and overage
     */
    fun howManyExtraPins(): Int {
        var tooMany = 0
        val mFirstDowned = (mFirstShot.mNumberOfPinsDowned ?: 0)
        val mSecondDowned = (mSecondShot.mNumberOfPinsDowned ?: 0)
        val mThirdDowned = (mThirdShot?.mNumberOfPinsDowned ?: 0)
        val shotsOneAndTwo = mFirstDowned + mSecondDowned
        val total = shotsOneAndTwo + mThirdDowned

        if(mFirstDowned>10){
            tooMany = mFirstDowned - 10
        }

        if(mSecondDowned>10){
            tooMany = mSecondDowned - 10
        }

        if(mThirdDowned>10){
            tooMany = mThirdDowned - 10
        }

        if (mFrameNumber == 10) {
            if (total > 30)
                tooMany = total - 30
            if (!(shotsOneAndTwo == 10 || shotsOneAndTwo == 20) && mThirdDowned > 0)
                tooMany = mThirdDowned
        } else {
            if (shotsOneAndTwo > 10)
                tooMany = shotsOneAndTwo - 10
        }
        return tooMany
    }

    /**
     * Returns the Shots for the frame and determines if the shot is a strike or whatnot
     * based on logic from http://www.fryes4fun.com/Bowling/scoring.htm
     */
    fun getShots(): MutableList<Shot> {
        val shots = mutableListOf<Shot>()
        if (mFirstShot.mNumberOfPinsDowned != null) {
            if (mFirstShot.mNumberOfPinsDowned == 10)
                mFirstShot.mStatus = ShotStatus.Strike
            else {
                mFirstShot.mStatus = ShotStatus.Unset
            }
            shots.add(mFirstShot)
        }
        if (mSecondShot.mNumberOfPinsDowned != null) {
            when {
                (mFirstShot.mNumberOfPinsDowned ?: 0).plus(
                    mSecondShot.mNumberOfPinsDowned ?: 0
                ) == 10 -> mSecondShot.mStatus =
                    ShotStatus.Spare
                (mFirstShot.mNumberOfPinsDowned ?: 0).plus(
                    mSecondShot.mNumberOfPinsDowned ?: 0
                ) < 10 -> mSecondShot.mStatus =
                    ShotStatus.OpenFrame
                else -> {
                    mSecondShot.mStatus = ShotStatus.Unset
                }
            }
            shots.add(mSecondShot)
        }
        if (mThirdShot != null && mThirdShot?.mNumberOfPinsDowned != null) {
            if (mThirdShot?.mNumberOfPinsDowned ?: 0 == 10)
                mThirdShot?.mStatus = ShotStatus.Strike
            else {
                mThirdShot!!.mStatus = ShotStatus.Unset
            }
            shots.add(mThirdShot!!)
        }

        return shots
    }
}