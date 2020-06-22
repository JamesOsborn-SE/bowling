package net.josborn.bowling

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.RecyclerView

class ScoreCardAdapter(
    private val frames: MutableList<Frame>,
    val mContext: Context
) :
    RecyclerView.Adapter<ScoreCardAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val linearLayout: LinearLayout) : RecyclerView.ViewHolder(linearLayout)

    private val mScoreSheet = ScoreSheet()
    val mFrames = mScoreSheet.mTenFrames

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScoreCardAdapter.MyViewHolder {
        // create a new view
        val linearLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.score, parent, false) as LinearLayout
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(linearLayout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(vh: MyViewHolder, position: Int) {
        val mFirstShot: AppCompatEditText = vh.linearLayout.findViewById(R.id.ShotOne)!!
        val mFirstShotLabel: TextView = vh.linearLayout.findViewById(R.id.ShotOneLabel)!!
        val mSecondShot: AppCompatEditText = vh.linearLayout.findViewById(R.id.ShotTwo)!!
        val mThirdShot: AppCompatEditText = vh.linearLayout.findViewById(R.id.ShotThree)!!
        val mFrameNumber: TextView = vh.linearLayout.findViewById(R.id.frameLabel)!!
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        mFirstShot.onFocusChangeListener = View.OnFocusChangeListener { v, _ ->
            val editText = v as EditText
            var newValue: Int? = null
            when {
                !editText.text.toString().isBlank() -> {
                    newValue = editText.text.toString().toInt()
                }
            }
            mFrames[position].mFirstShot.mNumberOfPinsDowned = newValue
            val tooManyPins = mFrames[position].howManyExtraPins()
            if (tooManyPins > 0) {
                showTooManyPins(tooManyPins)
                mFrames[position].mFirstShot.mNumberOfPinsDowned = 0
                editText.text = null
            }
            if (newValue == null)
                editText.text = null
        }
        mSecondShot.onFocusChangeListener = View.OnFocusChangeListener { v, _ ->

            val editText = v as EditText
            var newValue: Int? = null
            when {
                !editText.text.toString().isBlank() -> {
                    newValue = editText.text.toString().toInt()
                }
            }
            mFrames[position].mSecondShot.mNumberOfPinsDowned = newValue
            val tooManyPins = mFrames[position].howManyExtraPins()
            if (tooManyPins > 0) {
                showTooManyPins(tooManyPins)
                mFrames[position].mSecondShot.mNumberOfPinsDowned = 0
                editText.text = null
            }
            if (newValue == null)
                editText.text = null


        }
        mThirdShot.onFocusChangeListener = View.OnFocusChangeListener { v, _ ->
            val editText = v as EditText
            var newValue: Int? = null
            when {
                !editText.text.toString().isBlank() -> {
                    newValue = editText.text.toString().toInt()
                }
            }
            mFrames[position].mThirdShot?.mNumberOfPinsDowned = newValue
            val tooManyPins = mFrames[position].howManyExtraPins()
            if (tooManyPins > 0) {
                showTooManyPins(tooManyPins)
                mFrames[position].mThirdShot?.mNumberOfPinsDowned = 0
                editText.text = null
            }
            if (newValue == null)
                editText.text = null
        }

        mScoreSheet.getScore()

        mFrameNumber.text = mFrames[position].mFrameNumber.toString()

        if (mFrames[position].mFirstShot.mNumberOfPinsDowned != null) {
            mFirstShot.setText(mFrames[position].mFirstShot.mNumberOfPinsDowned.toString())
        } else {
            mFirstShot.setText("")
        }
        if (mFrames[position].mSecondShot.mNumberOfPinsDowned != null)
            mSecondShot.setText(mFrames[position].mSecondShot.mNumberOfPinsDowned.toString())
        else
            mSecondShot.setText("")
        if (mFrames[position].mThirdShot?.mNumberOfPinsDowned != null)
            mThirdShot.setText(mFrames[position].mThirdShot?.mNumberOfPinsDowned.toString())

        if (mFrames[position].mFrameNumber != 10) {
            mThirdShot.setText("")
            mThirdShot.visibility = View.GONE
        } else {
            mThirdShot.visibility = View.VISIBLE
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = frames.size


    private fun showTooManyPins(pins: Int) {
        val text = "You have $pins too many pins"
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(mContext, text, duration)
        toast.show()
    }
}

