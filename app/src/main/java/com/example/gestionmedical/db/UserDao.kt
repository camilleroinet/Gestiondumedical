package com.example.gestionmedical.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: DataUser) : Long

    @Update
    suspend fun updateUser(user: DataUser) : Int

    @Delete
    suspend fun deleteUser(user: DataUser) : Int

    @Query("DELETE FROM CIS_bdpm")
    suspend fun deleteAll() : Int

    @Query("SELECT * from CIS_bdpm")
    fun getAllUser(): Flow<List<DataUser>>

    @Query("SELECT * from CIS_bdpm")
    fun getUser(): List<DataUser>

}