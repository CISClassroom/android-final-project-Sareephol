package th.ac.kku.cis.mobileapp.final4

class ToDo {
    companion object Factory {
        fun create(): ToDo = ToDo()
    }

    var timebooking: String? = null
    var nameevent: String? = null
    var dateevent : String? = null
    var email : String? = null
    var id : String? = null
}
