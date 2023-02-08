package com.example.gestionmedical.db

class DataUserRepository(private val dao:UserDao) {
    val users = dao.getAllUser()

    suspend fun insert(userdao: DataUser): Long{
        return dao.insertUser(userdao)
    }

    suspend fun update(userdao:DataUser): Int {
        return dao.updateUser(userdao)
    }

    suspend fun delete(userdao: DataUser): Int{
        return dao.deleteUser(userdao)
    }

    suspend fun deleteAll() : Int{
        return dao.deleteAll()
    }

}