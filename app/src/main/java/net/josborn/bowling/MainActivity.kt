package net.josborn.bowling

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    private lateinit var scoreSheet: ScoreSheet
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        viewAdapter = ScoreCardAdapter(ScoreSheet.getFrames(), this)
        recyclerView = findViewById<RecyclerView>(R.id.frameList).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
        scoreSheet = ScoreSheet((viewAdapter as ScoreCardAdapter).mFrames)
        val tv = findViewById<TextView>(R.id.scoreTotalText)
        tv.text = getString(R.string.score, scoreSheet.getScore())
    }

    fun getScore(view: View) {
        view.isEnabled = false
        val tv = findViewById<TextView>(R.id.scoreTotalText)
        tv.text = getString(R.string.score, scoreSheet.getScore())
        view.isEnabled = true
    }
}




