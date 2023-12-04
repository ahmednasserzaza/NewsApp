package com.fighter.newsapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fighter.newsapp.BR
import com.fighter.newsapp.R
import com.fighter.newsapp.ui.base.BaseAdapter
import com.fighter.newsapp.ui.base.BaseInteractionListener
import com.fighter.newsapp.ui.home.HomeItem

class HomeAdapter(
    private var homeItems: MutableList<HomeItem>,
    private val listener: BaseInteractionListener,
) : BaseAdapter<HomeItem>(homeItems, listener) {
    override val layoutID: Int = 0

    fun setItem(item: HomeItem) {
        val newItems = homeItems.apply {
            removeAt(item.priority)
            add(item.priority, item)
        }
        setItems(newItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), viewType, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (homeItems.isNotEmpty())
            bind(holder as ItemViewHolder, position)
    }

    override fun bind(holder: ItemViewHolder, position: Int) {
        if (position != -1) {
            when (val currentItem = homeItems[position]) {
                is HomeItem.TopSlider -> {
                    holder.binding.setVariable(
                        BR.adapterRecycler,
                        EgyptNewsAdapter(
                            currentItem.egyptNews,
                            listener as NewsInteractionListener
                        )
                    )
                }

                is HomeItem.LatestNews -> {
                    holder.binding.setVariable(
                        BR.adapterRecycler,
                        LatestNewsAdapter(
                            currentItem.latestNews,
                            listener as NewsInteractionListener
                        )
                    )
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (homeItems.isNotEmpty()) {
            return when (homeItems[position]) {
                is HomeItem.TopSlider -> R.layout.list_top_slider
                is HomeItem.LatestNews -> R.layout.list_latest_news
            }
        }
        return -1
    }

    override fun setItems(newItems: List<HomeItem>) {
        homeItems = newItems.sortedBy { it.priority }.toMutableList()
        super.setItems(homeItems)
    }

    override fun areItemsSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
        return oldItem.priority == newItem.priority
    }

    override fun areContentSame(
        oldPosition: HomeItem,
        newPosition: HomeItem,
    ): Boolean {
        return oldPosition == newPosition
    }
}