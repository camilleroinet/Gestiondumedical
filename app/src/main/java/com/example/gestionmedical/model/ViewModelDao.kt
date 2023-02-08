package com.example.gestionmedical.model

import androidx.lifecycle.*
import com.example.gestionmedical.Event

import com.example.gestionmedical.db.DataUser
import com.example.gestionmedical.db.DataUserRepository
import kotlinx.coroutines.launch

class ViewModelDao (private val repo: DataUserRepository) : ViewModel() {
    private var isUpdateOrDelete = false
    private lateinit var userToUpdateOrDelete: DataUser
    val inputName1 = MutableLiveData<String?>()
    val saveOrUpdateButtonText = MutableLiveData<String>()
    private val clearAllOrDeleteButtonText = MutableLiveData<String>()
    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
    get() = statusMessage

    init{
        saveOrUpdateButtonText.value = "rechercher"
        clearAllOrDeleteButtonText.value = "clear All"
    }

    fun initUpdateAndDelete(datauser: DataUser){
        inputName1.value = datauser.text1
        isUpdateOrDelete = true
        userToUpdateOrDelete = datauser
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    fun saveOrUpdate(){
        if(inputName1.value == null){
            statusMessage.value = Event("Please enter kekchoz")
        }else {
            if(isUpdateOrDelete){
                userToUpdateOrDelete.text1 = inputName1.value!!
                updateDatauser(userToUpdateOrDelete)
            }else{
                val name = inputName1.value!!
                val name2 = inputName1.value!!
                val name3 = inputName1.value!!

                insertDataUser(DataUser(0, name, name2, name3))
                inputName1.value = ""
            }
        }
    }

    private fun insertDataUser(datauser: DataUser) = viewModelScope.launch {
        val newRowId = repo.insert(datauser)
        if (newRowId > -1){
            statusMessage.value = Event("insertion ok $newRowId")
        } else {
            statusMessage.value = Event("aaaaarghhh!!! po pu fait")
        }
    }

    private fun updateDatauser(datauser: DataUser) = viewModelScope.launch {
        val noOfRow = repo.update(datauser)
        if (noOfRow > 0){
            inputName1.value = ""
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "save"
            clearAllOrDeleteButtonText.value = "clear all"
            statusMessage.value = Event("$noOfRow update ok")
        }else {
            statusMessage.value = Event("aaaaaarhhhh!!!!!! problemo")
        }
    }

    fun getSavedUser() = liveData {
        repo.users.collect{
            emit(it)
        }
    }

    fun clearallOrdelete(){
        if (isUpdateOrDelete){
            deleteUser(userToUpdateOrDelete)
        }else{
            clearAll()
        }
    }

    private fun deleteUser(datauser: DataUser) = viewModelScope.launch {
        val noOfRowDeleted = repo.delete(datauser)
        if (noOfRowDeleted > 0){
            inputName1.value = ""
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "save"
            clearAllOrDeleteButtonText.value = "clear all"
            statusMessage.value = Event("$noOfRowDeleted Row virer ok")
        }else{
            statusMessage.value = Event("aaaaargh!!!! problemo")
        }
    }

    private fun clearAll() = viewModelScope.launch {
        val noOfRowDeleted = repo.deleteAll()
        if (noOfRowDeleted > 0){
            statusMessage.value = Event("$noOfRowDeleted user virer ok")
        }else{
            statusMessage.value = Event("AAAAAAARG problemeuuu")
        }
    }
}