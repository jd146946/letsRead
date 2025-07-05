package com.gun2.letsread.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.gun2.letsread.model.Book
import com.gun2.letsread.R

class MainViewModel : ViewModel() {
    private val _books = MutableStateFlow(
        listOf(
            Book(1, "Compose Magic", "Ada Lovelace", R.drawable.books),
            Book(2, "Jetpack Rising", "Alan Turing", R.drawable.books)
        )
    )
    val books: StateFlow<List<Book>> = _books
}