package com.farshidabz.kindnesswall.view.addproduct

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.farshidabz.kindnesswall.R
import com.farshidabz.kindnesswall.databinding.ItemSelectedImagesBinding

class SelectedImagesAdapter : RecyclerView.Adapter<SelectedImagesViewHolder>() {
    private var items = ArrayList<String>()

    public var onClickCallback: ((Int, String) -> Unit)? = null

    override fun getItemId(position: Int): Long {
        return items[position].hashCode().toLong()
    }

    fun setItems(items: List<String>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedImagesViewHolder {
        return SelectedImagesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_selected_images,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: SelectedImagesViewHolder, position: Int) {
        val item = items[position]
        holder.binding.item = item
        holder.binding.position = position

        holder.binding.root.setOnClickListener {
            onClickCallback?.invoke(holder.adapterPosition, item)
        }
    }
}

class SelectedImagesViewHolder(val binding: ItemSelectedImagesBinding) :
    RecyclerView.ViewHolder(binding.root)



