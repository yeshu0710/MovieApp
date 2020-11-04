package Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.movieapp.R
import android.widget.LinearLayout
import pojo.ModelClass

class MovieAdaptor(context: Context, arrayListDetails:ArrayList<ModelClass>) : BaseAdapter(){

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val arrayListDetails:ArrayList<ModelClass> = arrayListDetails

    override fun getCount(): Int {
        return arrayListDetails.size
    }

    override fun getItem(position: Int): Any {
        return arrayListDetails.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val listRowHolder: ListRowHolder
        if (convertView == null) {
            view = this.layoutInflater.inflate(R.layout.adaptor_layout, parent, false)
            listRowHolder = ListRowHolder(view)
            view.tag = listRowHolder
        } else {
            view = convertView
            listRowHolder = view.tag as ListRowHolder
        }

        listRowHolder.name.text = arrayListDetails.get(position).name
        listRowHolder.poster_path.text = arrayListDetails.get(position).poster_path
        listRowHolder.id.text = arrayListDetails.get(position).id.toString()
        return view
    }
}

private class ListRowHolder(row: View?) {
    public val name: TextView
    public val poster_path: TextView
    public val id: TextView
    public val linearLayout: LinearLayout

    init {
        this.name = row?.findViewById<TextView>(R.id.name) as TextView
        this.poster_path = row?.findViewById<TextView>(R.id.poster_path) as TextView
        this.id = row?.findViewById<TextView>(R.id.id) as TextView
        this.linearLayout = row?.findViewById<LinearLayout>(R.id.linearLayout) as LinearLayout
    }
}