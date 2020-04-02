package th.ac.kku.cis.mobileapp.final4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_listdata.*

class Listdata : AppCompatActivity() {


    lateinit var mDatabase: DatabaseReference
    var toDoItemList: MutableList<ToDo>? = null
    lateinit var adapter: ToDoItemAdapter
    private var listViewItems: ListView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listdata)

        var email = getIntent().getStringExtra("email")
        txtuser.text = email.toString()

        mDatabase = FirebaseDatabase.getInstance().reference
        listViewItems = findViewById<View>(R.id.listdata) as ListView

        toDoItemList = mutableListOf<ToDo>()
        adapter = ToDoItemAdapter(this, toDoItemList!!)
        listViewItems!!.setAdapter(adapter)
        mDatabase.orderByKey().addListenerForSingleValueEvent(itemListener)

        listdata.setOnItemClickListener{ parent, view, position, id ->
            val intent = Intent(this,detaillist::class.java)
            val selectedItem = parent.getItemAtPosition(position) as ToDo
            intent.putExtra("nameevent",selectedItem.nameevent.toString())
            intent.putExtra("dateevent",selectedItem.dateevent.toString())
            intent.putExtra("timebooking",selectedItem.timebooking.toString())
            intent.putExtra("email",selectedItem.email.toString())
            intent.putExtra("id",selectedItem.id.toString())
            startActivity(intent)



        }
        Btnback.setOnClickListener{
            val intent = Intent(this@Listdata,Home::class.java)
            intent.putExtra("email",email.toString())
            startActivity(intent)
            finish()
        }
    }


    var itemListener: ValueEventListener = object : ValueEventListener {

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            addDataToList(dataSnapshot.child("BookingGym"))
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
        }


        private fun addDataToList(dataSnapshot: DataSnapshot) {
            val items = dataSnapshot.children.iterator()
            var email = getIntent().getStringExtra("email")
            // Check if current database contains any collection
            if (items.hasNext()) {

                // check if the collection has any to do items or not
                while (items.hasNext()) {
                    // get current item
                    val currentItem = items.next()
                    val map = currentItem.getValue() as HashMap<String,Any>
                    // add data to object
                    if (map.get("email") == email){
                        val todoItem = ToDo.create()
                        todoItem.nameevent = map.get("nameevent") as String
                        todoItem.dateevent = map.get("dateevent") as String
                        todoItem.email = map.get("email") as String
                        todoItem.timebooking = map.get("timebooking") as String
                        todoItem.id = map.get("id") as String
                        toDoItemList!!.add(todoItem);
                    }
                }

                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this@Listdata,"ไม่มีข้อมูลการจองโรงยิม",Toast.LENGTH_SHORT).show()
            }
        }
    }
}

