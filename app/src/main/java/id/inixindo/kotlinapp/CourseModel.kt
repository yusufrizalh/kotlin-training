package id.inixindo.kotlinapp

data class CourseModel(
    val courses: List<Data>
) {
    data class Data(
        val id: String?,
        val name: String?,
        val price: String?,
        val duration: String?,
        val description: String?,
        val created_at: String?
    )
}
