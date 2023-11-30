package id.inixindo.simpleapplication.models

import java.io.Serializable

data class CourseModel(
    val courses: List<Data>
) {
    data class Data(
        val id: String?,
        val name: String?,
        val price: String?,
        val duration: String?,
        val description: String?
    ) : Serializable
}
