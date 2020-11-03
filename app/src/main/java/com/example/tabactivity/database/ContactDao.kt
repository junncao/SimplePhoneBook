package com.example.tabactivity.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tabactivity.beanclass.Contact

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts_table")
    fun getAll(): LiveData<List<Contact>>


    @Query("SELECT * FROM contacts_table WHERE name LIKE :name")
    fun findByName(name: String): Contact

    @Insert
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)
}