package com.kalashnyk.denys.windmillweather.utils.paging

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.databinding.library.baseAdapters.BR
import com.kalashnyk.denys.windmillweather.base.adapter.BaseDataBoundPagingAdapter

abstract class MultiTypeDataBoundAdapter<T, V : ViewDataBinding> (diffUtil: DiffUtil.ItemCallback<T>) : BaseDataBoundPagingAdapter<T, V>(diffUtil) {

    fun isEmpty(): Boolean {
        return itemCount == 0
    }

    override fun bindItem(holder: DataBoundViewHolder<V>, position: Int, payloads: List<Any>) {
        if (payloads.isEmpty() || payloads.contains(DiffCallbackBaseCardModel.CONTENT)) {
            //holder.binding.setVariable(BR.data, getItem(position))
        }
    }

    override fun getItemCount(): Int {
        return currentList?.size ?: 0
    }
}
