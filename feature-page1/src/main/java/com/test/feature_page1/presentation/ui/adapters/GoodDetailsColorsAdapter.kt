package com.test.feature_page1.presentation.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.feature_page1.R
import com.test.feature_page1.databinding.GoodDetailsColorSelectorItemBinding
import com.test.feature_page1.presentation.model.GoodDetailsColor

class GoodDetailsColorsAdapter(
    private val onColorSelect: (Int) -> Unit
) :
    ListAdapter<GoodDetailsColor, GoodDetailsColorsViewHolder>(GoodDetailsColorsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodDetailsColorsViewHolder {
        return GoodDetailsColorsViewHolder(
            GoodDetailsColorSelectorItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: GoodDetailsColorsViewHolder, position: Int) {
        val color = getItem(position)
        holder.binding.colorValue.setBackgroundColor(Color.parseColor(color.colorHex))

        if (color.isSelected)
            holder.binding.colorValue.setImageDrawable(
                AppCompatResources.getDrawable(
                    holder.binding.root.context,
                    R.drawable.good_details_color_item_selected_drawable
                )
            )
        else
            holder.binding.colorValue.setImageDrawable(
                AppCompatResources.getDrawable(
                    holder.binding.root.context,
                    R.drawable.good_details_color_item_selector
                )
            )

        holder.binding.root.setOnClickListener { onColorSelect(position) }
    }
}

class GoodDetailsColorsViewHolder(val binding: GoodDetailsColorSelectorItemBinding) :
    RecyclerView.ViewHolder(binding.root)

class GoodDetailsColorsDiffUtil : DiffUtil.ItemCallback<GoodDetailsColor>() {
    override fun areItemsTheSame(oldItem: GoodDetailsColor, newItem: GoodDetailsColor): Boolean {
        return newItem.colorHex == oldItem.colorHex
    }

    override fun areContentsTheSame(oldItem: GoodDetailsColor, newItem: GoodDetailsColor): Boolean {
        return newItem.colorHex == oldItem.colorHex && newItem.isSelected == oldItem.isSelected
    }
}