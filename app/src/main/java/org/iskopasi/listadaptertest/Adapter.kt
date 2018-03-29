package org.iskopasi.listadaptertest

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*
import org.iskopasi.listadaptertest.databinding.ListItemBinding

class Adapter(private val clickListener: (Int, Test) -> Unit) :
        ListAdapter<Test, Adapter.ViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, getItem(position), clickListener)
    }

    class ViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, model: Test, clickListener: (Int, Test) -> Unit) {
            itemView.tv.text = model.text
            itemView.setOnClickListener { clickListener(position, model) }
            itemView.setOnLongClickListener({
                it.layoutParams.height = 1000
                true
            })
        }
    }
}

open class TaskDiffCallback : DiffUtil.ItemCallback<Test>() {
    override fun areItemsTheSame(oldItem: Test?, newItem: Test?): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: Test?, newItem: Test?): Boolean {
        return oldItem?.text.equals(newItem?.text)
    }
}

data class Test(val id: Int, var text: String)