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
class Nearby : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var layout = inflater.inflate(R.layout.fragment_nearby, container, false)


        var item = ArrayList<DemoItemList>()

        item.add(
            DemoItemList(
                "janna", "15m away",
                R.drawable.girl_four
            )
        )

        item.add(DemoItemList("sunny", "1m away", R.drawable.girl_two))

        item.add(
            DemoItemList(
                "Sophia", "15m away",
                R.drawable.girl_six
            )
        )


        item.add(
            DemoItemList(
                "Isabella", "15m away",
                R.drawable.girl_two
            )
        )

        item.add(DemoItemList("Emma", "1m away", R.drawable.girl_three))

        item.add(
            DemoItemList(
                "Olivia", "15m away",
                R.drawable.girl_six
            )
        )


        item.add(
            DemoItemList(
                "janna", "15m away",
                R.drawable.girl_four
            )
        )

        item.add(DemoItemList("sunny", "1m away", R.drawable.girl_two))

        item.add(
            DemoItemList(
                "Sophia", "15m away",
                R.drawable.girl_six
            )
        )


        item.add(
            DemoItemList(
                "Isabella", "15m away",
                R.drawable.girl_two
            )
        )

        item.add(DemoItemList("Emma", "1m away", R.drawable.girl_three))

        item.add(
            DemoItemList(
                "Olivia", "15m away",
                R.drawable.girl_six
            )
        )


        var recyclerViewc= layout.findViewById<RecyclerView>(R.id.nearby_recycler_view)
        var adaptor = CustomAdaptor(item, activity!!.applicationContext)

        recyclerViewc.layoutManager = GridLayoutManager(activity!!.applicationContext,1)

        recyclerViewc.adapter = adaptor


        return layout
    }


}
