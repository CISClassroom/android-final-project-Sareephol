package th.ac.kku.cis.mobileapp.final4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_detaillist.*
import kotlinx.android.synthetic.main.activity_listdata.*

class detaillist : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detaillist)

        mDatabase = FirebaseDatabase.getInstance().reference

        var nameevent = getIntent().getStringExtra("nameevent")
        var dateevent = getIntent().getStringExtra("dateevent")
        var email = getIntent().getStringExtra("email")
        var timebooking = getIntent().getStringExtra("timebooking")
        var id = getIntent().getStringExtra("id")
        txtuser2.text = email.toString()
        nameeventshow.text = nameevent
        dateeventshow.text = dateevent
        timebookingshow.text = timebooking
        whoarebooking.text = email


        Btndelect.setOnClickListener{
            mDatabase.child("BookingGym").child(id).removeValue()
            Toast.makeText(this,"Delect Successful",Toast.LENGTH_SHORT).show()
            val intent = Intent(this@detaillist,Listdata::class.java)
            intent.putExtra("email",email.toString())
            startActivity(intent)
            finish()
        }
    }
}
