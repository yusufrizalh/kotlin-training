package id.inixindo.kotlinapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class CourseAdapter(
    val courses: ArrayList<CourseModel.Data>,
    val listener: OnAdapterListener
): RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textCourseName = view.findViewById<TextView>(R.id.textCourseName)
        val imageDelete = view.findViewById<ImageView>(R.id.imageDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_product, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = courses[position]
        holder.textCourseName.text = data.name
        holder.itemView.setOnClickListener {
            listener.onClick(data)
        }
    }

    override fun getItemCount() = courses.size

    public fun setData(data: List<CourseModel.Data>){
        courses.clear()
        courses.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(course: CourseModel.Data)
    }

}