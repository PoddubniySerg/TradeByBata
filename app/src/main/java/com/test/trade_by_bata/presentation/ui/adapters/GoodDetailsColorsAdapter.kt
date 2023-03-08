package com.test.trade_by_bata.presentation.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.trade_by_bata.databinding.GoodDetailsColorSelectorItemBinding
import com.test.trade_by_bata.model.GoodDetailsColor

class GoodDetailsColorsAdapter :
    ListAdapter<GoodDetailsColor, GoodDetailsColorsViewHolder>(GoodDetailsColorsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodDetailsColorsViewHolder {
        return GoodDetailsColorsViewHolder(
            GoodDetailsColorSelectorItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: GoodDetailsColorsViewHolder, position: Int) {
        val color = getItem(position)
        holder.binding.colorValue.setColorFilter(Color.parseColor(color.colorHex))
        if (color.isSelected) holder.binding.colorLayout.setPadding(4)
        else holder.binding.colorLayout.setPadding(0)
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