package com.example.search.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.search.adapter.BooksAdapter
import com.example.search.databinding.FragmentSearchBinding
import com.example.search.model.BookUiModel
import com.example.search.viewmodel.SearchViewModel

class SearchFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val searchRecyclerViewAdapter by lazy {
        BooksAdapter {
            moveDetailFragment(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRecyclerView()
        subscribeUi()
        initClick()
    }

    private fun initViewModel() {
        binding.lifecycleOwner = this
    }

    private fun initRecyclerView() {
        binding.searchRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchRecyclerViewAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        if (recyclerView.canScrollVertically(1).not()) {
                            moreFetchSearchBooks()
                        }
                    }
                }
            })
        }
    }

    private fun subscribeUi() {
        searchViewModel.bookListData.observe(viewLifecycleOwner) {
            searchRecyclerViewAdapter.submitList(it)
        }
    }

    private fun initClick() {
        binding.searchEditText.apply {
            requestFocus()
            setOnEditorActionListener { _, action, _ ->
                var handled = false
                if (action == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard()
                    fetchSearchBooks()
                    handled = true
                }
                handled
            }
        }
        binding.searchEditText.requestFocus()
        binding.searchFragment = this
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.searchEditText.windowToken, 0)
    }

    private fun fetchSearchBooks() {
        if (!verifySearchText().first) return
        searchViewModel.fetchSearchBooks(verifySearchText().second!!)
    }

    private fun moreFetchSearchBooks() {
        if (!verifySearchText().first) return
        searchViewModel.moreFetchSearchBooks(verifySearchText().second!!)
    }

    private fun verifySearchText(): Pair<Boolean, String?> {
        val searchKeyword = binding.searchEditText.text.toString()
        if (searchKeyword.isBlank()) return Pair(false, null)

        return Pair(true, searchKeyword)
    }

    private fun moveDetailFragment(bookUiModel: BookUiModel) {
        val fragment = DetailFragment()
        val bundle = Bundle()
        bundle.putString("isbn13", bookUiModel.isbn13)
        fragment.arguments = bundle
        (activity as? MainActivity)?.addFragment(fragment, "detail")
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.searchImageView -> {
                hideKeyboard()
                fetchSearchBooks()
            }
        }
    }
}