package uz.Ilhomjon.Baholash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import uz.Ilhomjon.Baholash.adapters.DialogAdapter
import uz.Ilhomjon.Baholash.adapters.PupilAdapter
import uz.Ilhomjon.Baholash.databinding.ActivityMainBinding
import uz.Ilhomjon.Baholash.databinding.ItemDialogHelpBinding
import uz.Ilhomjon.Baholash.databinding.ItemDialogResponseBinding
import uz.Ilhomjon.Baholash.models.Pupil
import uz.Ilhomjon.Baholash.utils.MySharedPreference

class MainActivity : AppCompatActivity(), PupilAdapter.RvAction {

    lateinit var binding: ActivityMainBinding
    lateinit var pupilAdapter: PupilAdapter
    lateinit var list: ArrayList<Pupil>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MySharedPreference.init(this)
        list = ArrayList()
        list.addAll(MySharedPreference.obektString)
        loadData()

        pupilAdapter = PupilAdapter(list, this, this)
        binding.rv.adapter = pupilAdapter
    }

    override fun onClick(pupil: Pupil) {
        Toast.makeText(this, "$pupil", Toast.LENGTH_SHORT).show()
        pupilAdapter.notifyDataSetChanged()
    }

    override fun like1(pupil: Pupil, position: Int) {
        pupil.list[0] = !pupil.list[0]
        if (pupil.list[0]){
            pupil.grade +=10
        }else{
            pupil.grade -=10
        }
        liked(0, pupil, position)
        Toast.makeText(this, "${pupil.name} ga +10 ball", Toast.LENGTH_SHORT).show()
    }

    override fun like2(pupil: Pupil, position: Int) {
        pupil.list[1] = !pupil.list[1]
        if (pupil.list[1]){
            pupil.grade -=10
        }else{
            pupil.grade +=10
        }
        liked(1, pupil, position)
        Toast.makeText(this, "${pupil.name} ga -10 ball", Toast.LENGTH_SHORT).show()
    }

    override fun like3(pupil: Pupil, position: Int) {
        pupil.list[2] = !pupil.list[2]
        if (pupil.list[2]){
            pupil.grade +=5
        }else{
            pupil.grade -=5
        }
        liked(2, pupil, position)
        Toast.makeText(this, "${pupil.name} ga +5 ball", Toast.LENGTH_SHORT).show()
    }

    override fun like4(pupil: Pupil, position: Int) {
        pupil.list[3] = !pupil.list[3]
        if (pupil.list[3]){
            pupil.grade -=5
        }else{
            pupil.grade +=5
        }
        liked(3, pupil, position)
        Toast.makeText(this, "${pupil.name} ga -5 ball", Toast.LENGTH_SHORT).show()
    }

    override fun edtName(pupil: Pupil, position: Int) {
        list[position] = pupil
        MySharedPreference.obektString = list
    }

    fun liked(p:Int, pupil: Pupil, position: Int){

        list[position] = pupil
        pupilAdapter.notifyItemChanged(position)
        MySharedPreference.obektString = list
    }

    private fun loadData(){
        if (list.isEmpty()){
            for (i in 0..39){
                list.add(Pupil())
            }
            MySharedPreference.obektString = list
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_natija ->{
                val alertDialog = AlertDialog.Builder(this).create()
                val itemDialogResponseBinding = ItemDialogResponseBinding.inflate(layoutInflater)
                val lBall = ArrayList<Int>()
                for (p in list) {
                    lBall.add(p.grade)
                }
                lBall.sort()
                val lp = java.util.ArrayList<Pupil>()
                for (i in lBall) {
                    for (pupil in list) {
                        if (pupil.grade == i){
                            lp.add(pupil)
                            break
                        }
                    }
                }
                val dialogAdapter = DialogAdapter(lp.reversed())
                itemDialogResponseBinding.rvDialog.adapter = dialogAdapter
                alertDialog.setView(itemDialogResponseBinding.root)
                alertDialog.show()
            }
            R.id.menu_yordam ->{
                val alertDialog = AlertDialog.Builder(this).create()
                alertDialog.setView(ItemDialogHelpBinding.inflate(layoutInflater).root)
                alertDialog.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}