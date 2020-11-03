package com.example.tabactivity.beanclass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "contacts_table")
data class Contact(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "personal_number")
    val personal_number: String?,

    @ColumnInfo(name = "work_number")
    val work_number: String?,

    @ColumnInfo(name = "home_number")
    val home_number: String?
)