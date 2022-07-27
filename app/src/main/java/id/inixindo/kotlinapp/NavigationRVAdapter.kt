package id.inixindo.kotlinapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_nav_drawer.view.*
import java.util.ArrayList

class NavigationRVAdapter(
    private var items: ArrayList<NavigationItemModel>,
    private var currentPos: Int
) : RecyclerView.Adapter<NavigationRVAdapter.NavigationItemViewHolder>() {

    private lateinit var context: Context
    // constructor
    class NavigationItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem = LayoutInflater.from(parent.context).inflate(R.layout.row_nav_drawer, parent, false)
        return NavigationItemViewHolder(navItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {
        if (position == currentPos){
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        }

        holder.itemView.navigation_icon.setColorFilter(R.color.white, PorterDuff.Mode.SRC_ATOP)
        holder.itemView.navigation_title.setTextColor(R.color.white)

        holder.itemView.navigation_icon.setImageResource(items[position].icon)
        holder.itemView.navigation_title.text = items[position].title
    }

    override fun getItemCount(): Int {
        return items.count()
    }

}