package th.ac.kku.cis.mobileapp.final4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

        lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDatabase = FirebaseDatabase.getInstance().reference

        this.Btnlogin.setOnClickListener{
            val email = txtemail.text.toString()
            val password = txtpassword.text.toString()
            mDatabase.child("user").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val user = dataSnapshot.children.iterator()
                    if(user.hasNext()){
                        while (user.hasNext()){
                            val user = user.next().getValue() as HashMap<String, Any>

                            if (user.get("email")as String == email && user.get("password")as String == password ){
                                val intent = Intent(this@MainActivity,Home::class.java)
                                intent.putExtra("email",email.toString())
                                startActivity(intent)
                            }
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
        }
    }
}
