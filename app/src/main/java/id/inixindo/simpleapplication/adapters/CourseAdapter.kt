package id.inixindo.simpleapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.inixindo.simpleapplication.R
import id.inixindo.simpleapplication.models.CourseModel

class CourseAdapter(
    val courses: ArrayList<CourseModel.Data>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // komponen pada layout course_item.xml dikenali terlebih dahulu
        val txtCourseName = view.findViewById<TextView>(R.id.txtCourseName)
        val txtCoursePrice = view.findViewById<TextView>(R.id.txtCoursePrice)
        val imgDelete = view.findViewById<ImageView>(R.id.imgDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.course_item, parent, false)
    )

    override fun getItemCount() = courses.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val courseData = courses[position]
        holder.txtCourseName.text = courseData.name
        holder.txtCoursePrice.text = courseData.price
        holder.itemView.setOnClickListener { listener.onClickAdapterListener(courseData) }
        holder.imgDelete.setOnClickListener { listener.onDeleteAdapterListener(courseData) }
    }

    interface OnAdapterListener {
        fun onClickAdapterListener(course: CourseModel.Data)
        fun onDeleteAdapterListener(course: CourseModel.Data)
    }

    fun setCourseData(courseData: List<CourseModel.Data>) {
        courses.clear()
        courses.addAll(courseData)
        notifyDataSetChanged()
    }

}