package uz.Ilhomjon.Baholash.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.Ilhomjon.Baholash.databinding.ItemRvDialogBinding
import uz.Ilhomjon.Baholash.models.Pupil

class DialogAdapter(val list: List<Pupil>) : RecyclerView.Adapter<DialogAdapter.Vh>() {

    inner class Vh(var itemRv: ItemRvDialogBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(pupil: Pupil, position: Int) {
            itemRv.number.text = (position+1).toString()
            itemRv.title.text = pupil.name
            itemRv.tvGradeN.text = pupil.grade.toString()+" ball"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvDialogBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}