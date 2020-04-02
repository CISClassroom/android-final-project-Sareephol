package th.ac.kku.cis.mobileapp.final4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        var email = getIntent().getStringExtra("email")
        user.text = email.toString()

        mDatabase = FirebaseDatabase.getInstance().reference



        val spinner: Spinner = findViewById(R.id.spinner)
        val arrayList: ArrayList<String> = ArrayList()
        arrayList.add("ช่วงเช้า 8:00 น. - 12:00 น.")
        arrayList.add("ช่วงบ่าย 13:00 น. - 18:00 น.")
        arrayList.add("ช่วงกลางคืน 19:00 น. - 23:00 น.")
        val arrayAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayList)
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        Btnadddata.setOnClickListener {
            savedata()
        }
    }

    private fun savedata() {
        var nameevent = event.text.toString().trim()
        var dateevent = date.text.toString().trim()
        var timeBooking =  spinner.selectedItem.toString().trim()

        if (nameevent.isEmpty()){
            event.error = "Please Enter a name event"
            return
        }
        else if (dateevent.isEmpty()) {
            date.error = "Please Enter a Date Booking"
            return
        }

        var  todoItem = ToDo.create()
        var email = getIntent().getStringExtra("email")
        val newItem = mDatabase.child("BookingGym").push()
        todoItem.id = newItem.key
        todoItem.nameevent = nameevent
        todoItem.dateevent = dateevent
        todoItem.email = email
        todoItem.timebooking = timeBooking
        newItem.setValue(todoItem)
        finish()
    }
}
