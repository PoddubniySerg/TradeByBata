package com.test.feature_page1.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import com.test.feature_page1.databinding.SearchAutoCompleteItemBinding

class HomeSearchAutoCompleteAdapter(
    private val context: Context,
    private val getResults: (String) -> List<String>
) : BaseAdapter(), Filterable {

    private var _results = emptyList<String>()
    private var filter: Filter? = null

    override fun getCount(): Int {
        return _results.size
    }

    override fun getItem(position: Int): String {
        return _results[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding =
            if (convertView == null)
                SearchAutoCompleteItemBinding.inflate(
                    LayoutInflater.from(context),
                    parent,
                    false
                )
            else SearchAutoCompleteItemBinding.bind(convertView)
        binding.searchResult.text = getItem(position)
        return binding.root
    }

    override fun getFilter(): Filter {
        if (filter == null) filter = object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                val results = getResults.invoke(constraint?.toString() ?: return filterResults)
                filterResults.count = results.size
                filterResults.values = results
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null && results.count > 0) {
                    _results = results.values as List<String>
                    notifyDataSetChanged()
                } else notifyDataSetInvalidated()
            }
        }
        return filter!!
    }
}