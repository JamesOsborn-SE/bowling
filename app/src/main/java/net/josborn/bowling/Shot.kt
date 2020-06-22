package net.josborn.bowling

/**
 * @param frame the frame the shot belongs to
 * @param positionInFrame where in the frame the shot was taken 0,1, or bonus shot in the 10 frame 2
 * @param numberOfPinsDowned its.. the number of downed pins 0-10
 */
class Shot(frame: Int, positionInFrame: Int, numberOfPinsDowned: Int? =null, shotStatus: ShotStatus = ShotStatus.Unset) {
    var mStatus: ShotStatus = shotStatus
    val mFrame = frame
    val mPositionInFrame = positionInFrame
    var mNumberOfPinsDowned = numberOfPinsDowned

}