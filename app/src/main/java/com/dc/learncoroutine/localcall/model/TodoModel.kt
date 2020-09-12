package com.dc.learncoroutine.localcall.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo")
data class TodoModel(
    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "description")
    val description: String? = null
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todoId")
    var todoId: Int = 0
}