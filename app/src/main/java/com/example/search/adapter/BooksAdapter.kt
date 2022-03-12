package com.example.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.search.R
import com.example.search.data.ShimmerDrawable.makeShimmerDrawable
import com.example.search.databinding.ItemBookBinding
import com.example.search.model.BookUiModel
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

class BooksAdapter(
    private val onClick: (BookUiModel) -> Unit
) :
    ListAdapter<BookUiModel, BooksAdapter.BookViewHolder>(diffUtil) {

    init {
        setHasStableIds(true)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItem(position: Int): BookUiModel {
        return currentList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class BookViewHolder(var binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(item: BookUiModel, onClick: (BookUiModel) -> Unit) {
            binding.apply {
                bookTitle.text = item.title
                bookSub.text = item.subTitle
                bookPrice.text = item.price
                bookIsbn13.text = item.isbn13
                Glide.with(itemView.context)
                    .load(item.image)
                    .placeholder(makeShimmerDrawable())
                    .into(bookImage)
                root.setOnClickListener { onClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            ItemBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.onBind(currentList[position], onClick)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<BookUiModel>() {
            override fun areItemsTheSame(oldItem: BookUiModel, newItem: BookUiModel): Boolean {
                return newItem.isbn13 == oldItem.isbn13
            }

            override fun areContentsTheSame(oldItem: BookUiModel, newItem: BookUiModel): Boolean {
                return newItem == oldItem
            }
        }
    }
}