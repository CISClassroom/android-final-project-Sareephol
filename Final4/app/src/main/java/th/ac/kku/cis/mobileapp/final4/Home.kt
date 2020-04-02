package th.ac.kku.cis.mobileapp.final4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var email = getIntent().getStringExtra("email")
        name.text = email.toString()

        Btnadddata.setOnClickListener {
            val intent = Intent(this@Home, AddActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }

        Btnlist.setOnClickListener{
            val intent = Intent(this@Home,Listdata::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }
    }
}
