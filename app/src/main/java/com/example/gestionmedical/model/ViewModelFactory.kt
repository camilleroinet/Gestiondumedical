package com.example.gestionmedical.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gestionmedical.db.DataUserRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repo: DataUserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) :T{
        if (modelClass.isAssignableFrom(ViewModelDao::class.java)){
            return ViewModelDao(repo) as T
        }else{
            throw IllegalArgumentException("View Model inconnu")
        }
    }


}