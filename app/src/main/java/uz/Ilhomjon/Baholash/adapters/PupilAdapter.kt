package uz.Ilhomjon.Baholash.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import uz.Ilhomjon.Baholash.databinding.ItemRvBinding
import uz.Ilhomjon.Baholash.models.Pupil

class PupilAdapter(val list: List<Pupil>, var context: Context, var rvAction: RvAction) : RecyclerView.Adapter<PupilAdapter.Vh>() {

    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(pupil: Pupil, position: Int) {
            itemRv.itemNumber.text = (position+1).toString()
            if (pupil.list[0]) {
                itemRv.imageLike.alpha = 1.0F
            } else {
                itemRv.imageLike.alpha = 0.3f
            }
            if (pupil.list[1])  {
                itemRv.imageNotLike.alpha = 1.0F
            } else {
                itemRv.imageNotLike.alpha = 0.3f
            }
            if (pupil.list[2]) {
                itemRv.imageLike2.alpha = 1.0F
            } else {
                itemRv.imageLike2.alpha = 0.3f
            }
            if (pupil.list[3]) {
                itemRv.imageNotLike2.alpha = 1.0F
            } else {
                itemRv.imageNotLike2.alpha = 0.3f
            }

            itemRv.itemEdt.setText(pupil.name)
            itemRv.tvGrade.text = pupil.grade.toString()

            itemRv.imageLike.setOnClickListener { rvAction.like1(pupil, position) }
            itemRv.imageNotLike.setOnClickListener { rvAction.like2(pupil, position) }
            itemRv.imageLike2.setOnClickListener { rvAction.like3(pupil, position) }
            itemRv.imageNotLike2.setOnClickListener { rvAction.like4(pupil, position) }

            itemRv.root.setOnClickListener { rvAction.onClick(pupil) }

            itemRv.itemEdt.addTextChangedListener {
                pupil.name = it.toString()
                rvAction.edtName(pupil, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface RvAction{
        fun onClick(pupil: Pupil)
        fun like1(pupil: Pupil, position: Int)
        fun like2(pupil: Pupil, position: Int)
        fun like3(pupil: Pupil, position: Int)
        fun like4(pupil: Pupil, position: Int)
        fun edtName(pupil: Pupil, position: Int)
    }
}