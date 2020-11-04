package com.example.tabactivity.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tabactivity.beanclass.Contact

class ViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var database: AppDatabase
    lateinit var contactsList :LiveData<List<Contact>>
    init {
        database = AppDatabase.getInstance(application);
        contactsList = getContacts()
    }
    fun getContacts():LiveData<List<Contact>>{
        return database.contactDao.getAll()
    }
    fun insertContact(contact: Contact){
        database.contactDao.insert(contact)
    }
    fun deleteContact(contact: Contact){
        database.contactDao.delete(contact)
    }



}