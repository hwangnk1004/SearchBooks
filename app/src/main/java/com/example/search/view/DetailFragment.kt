package com.example.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.search.databinding.FragmentDetailBinding
import com.example.search.model.DetailBookUiModel
import com.example.search.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isbn13 = arguments?.getString("isbn13") ?: ""
        detailViewModel.fetchDetailBook(isbn13)
        initViewModel()
        subscribeUi()
    }

    private fun initViewModel() {
        binding.lifecycleOwner = this
    }

    private fun subscribeUi() {
        detailViewModel.detailBook.observe(viewLifecycleOwner) {
            initUi(it)
        }
    }

    private fun initUi(data: DetailBookUiModel) {
        binding.apply {
            bookDetailInputTitle.text = data.title
            bookDetailInputSubTitle.text = data.subtitle
            Glide.with(requireContext())
                .load(data.image)
                .into(bookDetailImage)
            bookDetailInputAuthors.text = data.authors
            bookDetailInputPublisher.text = data.publisher
            bookDetailInputLanguage.text = data.language
            bookDetailInputIsbn10.text = data.isbn10
            bookDetailInputIsbn13.text = data.isbn13
            bookDetailInputPages.text = data.pages
            bookDetailInputYears.text = data.year
            bookDetailInputRating.text = data.rating
            bookDetailInputPrice.text = data.price
            bookDetailInputDescription.text = data.description
        }
    }
}