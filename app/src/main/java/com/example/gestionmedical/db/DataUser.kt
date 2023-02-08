package com.example.gestionmedical.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CIS_bdpm")
class DataUser (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "Code_CIS")
    val  id_medoc: Int,

    @ColumnInfo(name="Designation")
    var text1: String?,

    @ColumnInfo(name="Forme_Pharmaceutique")
    val text2: String?,

    @ColumnInfo(name="Voie_Administration")
    val text3: String?
)


/*

CREATE TABLE "CIS_bdpm" (
	"Code_CIS"	INTEGER,
	"Designation"	TEXT CHECK(typeof("Désignation") = 'text'),
	"Forme_Pharmaceutique"	VARCHAR,
	"Voie_Administration"	VARCHAR
)

 */