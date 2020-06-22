package net.josborn.bowling

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.RecyclerView

class ScoreCardAdapter(
    private val mContext: Context
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
            .inflate(R.layout.frame, parent, false) as LinearLayout
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(linearLayout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(vh: MyViewHolder, position: Int) {
        val firstShot: AppCompatEditText = vh.linearLayout.findViewById(R.id.first_shot)!!
        val secondShot: AppCompatEditText = vh.linearLayout.findViewById(R.id.second_shot)!!
        val thirdShot: AppCompatEditText = vh.linearLayout.findViewById(R.id.third_shot)!!
        val frameNumber: TextView = vh.linearLayout.findViewById(R.id.frame_number_label)!!
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        firstShot.onFocusChangeListener = View.OnFocusChangeListener { v, _ ->
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
        secondShot.onFocusChangeListener = View.OnFocusChangeListener { v, _ ->

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
        thirdShot.onFocusChangeListener = View.OnFocusChangeListener { v, _ ->
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

        frameNumber.text = mFrames[position].mFrameNumber.toString()

        if (mFrames[position].mFirstShot.mNumberOfPinsDowned != null) {
            firstShot.setText(mFrames[position].mFirstShot.mNumberOfPinsDowned.toString())
        } else {
            firstShot.setText("")
        }
        if (mFrames[position].mSecondShot.mNumberOfPinsDowned != null)
            secondShot.setText(mFrames[position].mSecondShot.mNumberOfPinsDowned.toString())
        else
            secondShot.setText("")
        if (mFrames[position].mThirdShot?.mNumberOfPinsDowned != null)
            thirdShot.setText(mFrames[position].mThirdShot?.mNumberOfPinsDowned.toString())

        if (mFrames[position].mFrameNumber != 10) {
            thirdShot.setText("")
            thirdShot.visibility = View.GONE
        } else {
            thirdShot.visibility = View.VISIBLE
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = mFrames.size


    private fun showTooManyPins(pins: Int) {
        val text = "You have $pins too many pins"
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(mContext, text, duration)
        toast.show()
    }
}

