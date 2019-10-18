package com.example.datingapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
 */
class Away : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var layout= inflater.inflate(R.layout.fragment_away, container, false)


        var item = ArrayList<AwayDemoItem>()

        item.add(
            AwayDemoItem(
                "janna",
                "15m away",
                R.drawable.girl_four
            )
        )

        item.add(
            AwayDemoItem(
                "sunny",
                "1m away",
                R.drawable.girl_two
            )
        )

        item.add(
            AwayDemoItem(
                "Sophia",
                "15m away",
                R.drawable.girl_six
            )
        )


        item.add(
            AwayDemoItem(
                "Isabella",
                "15m away",
                R.drawable.girl_two
            )
        )

        item.add(
            AwayDemoItem(
                "Emma",
                "1m away",
                R.drawable.girl_three
            )
        )

        item.add(
            AwayDemoItem(
                "Olivia",
                "15m away",
                R.drawable.girl_six
            )
        )


        item.add(
            AwayDemoItem(
                "janna",
                "15m away",
                R.drawable.girl_four
            )
        )

        item.add(
            AwayDemoItem(
                "sunny",
                "1m away",
                R.drawable.girl_two
            )
        )

        item.add(
            AwayDemoItem(
                "Sophia",
                "15m away",
                R.drawable.girl_six
            )
        )


        item.add(
            AwayDemoItem(
                "Isabella",
                "15m away",
                R.drawable.girl_two
            )
        )

        item.add(
            AwayDemoItem(
                "Emma",
                "1m away",
                R.drawable.girl_three
            )
        )

        item.add(
            AwayDemoItem(
                "Olivia",
                "15m away",
                R.drawable.girl_six
            )
        )


        var away_recycler= layout.findViewById<RecyclerView>(R.id.away_recycler)

        var adaptor = AwayCustomAdaptor(item, activity!!.applicationContext)

        away_recycler.layoutManager = GridLayoutManager(activity!!.applicationContext,1)

        away_recycler.adapter = adaptor


        return layout
    }


}
