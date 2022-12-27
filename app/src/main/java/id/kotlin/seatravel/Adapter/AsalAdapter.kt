package id.kotlin.seatravel.Adapter

import android.content.ClipData.Item
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import id.kotlin.seatravel.Data.Asal
import id.kotlin.seatravel.R

class AsalAdapter (var ctx: Context, var resource: Int, var item: ArrayList<Asal>): ArrayAdapter<Asal>(ctx, resource, item) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(ctx)
        val view = layoutInflater.inflate(resource, null)

        val spinners =view.findViewById(R.id.asal) as Spinner

//        ArrayAdapter.createFromResource(
//            this,
//            R.array.asal,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // specify the layout to use when the list of choice appears
//            adapter. setDropDownViewResource(android.R.layout.simple_spinner_item)
//            // Applay the adapter to the spinner
//            spinners.adapter = adapter
//        }

//        item[position].asal.also { adapter ->
//            adapter.get(android.R.layout.simple_spinner_item)
//            spinners.adapter = adapter


        return view
    }
}



