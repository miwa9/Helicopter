package com.example.helicopter.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.helicopter.Model.SpotModel
import com.example.helicopter.Model.UserModel

class DBHelper(context: Context):SQLiteOpenHelper(context, "database.db", null, 1) {
    val sql = arrayOf(
        "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT UNIQUE, password TEXT)",
        "INSERT INTO users (username, password) VALUES('admin','password')",
        "CREATE TABLE spot(id INTEGER PRIMARY KEY AUTOINCREMENT, client TEXT, dateFlight TEXT, flight TEXT, travel TEXT, departure TEXT, landing TEXT, steps INT)",
        "INSERT INTO spot (client,dateFlight,flight,travel,departure, landing, steps) VALUES('test','dia','num','tempo','SBME','SS70',2)",
        "INSERT INTO spot (client,dateFlight,flight,travel,departure, landing, steps) VALUES('test','20/04/2023','1007073','01:50','SBME','NS29',2)"
    )


    override fun onCreate(db: SQLiteDatabase) {
        sql.forEach {
            db.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    /*-----------------------------------------------------------------------
                                CRUD USER
    ---------------------------------------------------------------------- */

    fun insertUser(username: String, password: String): Long {
        val db = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put("username", username)
        contentValue.put("password", password)
        val res = db.insert("users", null, contentValue)
        db.close()
        return res


    }

    fun updateUser(id: Int, username: String, password: String): Int {
        val db = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put("username", username)
        contentValue.put("password", password)
        val res = db.update("users", contentValue, "id=?", arrayOf(id.toString()))
        db.close()
        return res

    }

    fun deleteUser(id: Int): Int {
        val db = this.writableDatabase
        val contentValue = ContentValues()
        val res = db.delete("users", "id=?", arrayOf(id.toString()))
        db.close()
        return res


    }

    fun getUser(username: String, password: String): UserModel {
        val db = this.readableDatabase
        val c = db.rawQuery(
            "SELECT * FROM users WHERE username=? AND password =?", arrayOf(username, password)
        )
        var userModel = UserModel()
        if (c.count == 1) {
            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")
            val usernameIndex = c.getColumnIndex("username")
            val passwordIndex = c.getColumnIndex("password")
            userModel = UserModel(
                id = c.getInt(idIndex),
                username = c.getString(usernameIndex),
                password = c.getString(passwordIndex)
            )

        }
        db.close()
       c.close()
        return userModel

    }

    fun login(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val c = db.rawQuery(
            "SELECT * FROM users WHERE username=? AND password =?",
            arrayOf(username, password)
        )
        var userModel = UserModel()
       return if (c.count == 1) {
           db.close()
             true
        } else {
            db.close()
           c.close()
            false

        }
    }

    /*-----------------------------------------------------------------------
                                CRUD SPOT
    ---------------------------------------------------------------------- */
    fun insertSpot(client: String, dateFlight: String, flight: String, travel: String,departure:String,landing:String, steps:Int): Long {
        val db = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put("client", client)
        contentValue.put("dateFlight", dateFlight)
        contentValue.put("flight", flight)
        contentValue.put("travel", travel)
        contentValue.put("departure", departure)
        contentValue.put("landing", landing)
        contentValue.put("steps", steps)
        val res = db.insert("spot", null, contentValue)
        db.close()
        return res


    }

    fun updateSpot(id: Int, client: String, dateFlight: String, flight: String, travel: String,departure:String,landing:String, steps:Int): Int {
        val db = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put("client", client)
        contentValue.put("dateFlight", dateFlight)
        contentValue.put("flight", flight)
        contentValue.put("travel", travel)
        contentValue.put("departure", departure)
        contentValue.put("landing", landing)
        contentValue.put("steps", steps)
        val res = db.update("spot", contentValue, "id=?", arrayOf(id.toString()))
        db.close()
        return res

    }

    fun deleteSpot(id: Int): Int {
        val db = this.writableDatabase
        val contentValue = ContentValues()
        val res = db.delete("spot", "id=?", arrayOf(id.toString()))
        db.close()
        return res


    }

    fun getSpot(id:Int): SpotModel {
        val db = this.readableDatabase
        val c = db.rawQuery(
            "SELECT * FROM spot WHERE id=?", arrayOf(id.toString())
        )
        var spotModel = SpotModel()
        if (c.count == 1) {
            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")
            val clientIndex = c.getColumnIndex("client")
            val dateFlightIndex = c.getColumnIndex("dateFlight")
            val flightIndex = c.getColumnIndex("flight")
            val travelIndex = c.getColumnIndex("travel")
            val departureIndex = c.getColumnIndex("departure")
            val landingIndex = c.getColumnIndex("landing")
            val stepsIndex = c.getColumnIndex("steps")

            spotModel = SpotModel(
                id = c.getInt(idIndex),
                client = c.getString(clientIndex),
                dateFlight = c.getString(dateFlightIndex),
                flight = c.getString(flightIndex),
                travel = c.getString(travelIndex),
                departure = c.getString(departureIndex),
                landing = c.getString(landingIndex),
                steps = c.getInt(stepsIndex)
            )

        }
        db.close()
        c.close()
        return spotModel

    }
    fun getAllSpot(): ArrayList<SpotModel> {
        val db = this.readableDatabase
        val c = db.rawQuery(
            "SELECT * FROM spot ",null)


        val listSpotModel = ArrayList<SpotModel>()
        if (c.count > 0) {
            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")
            val clientIndex = c.getColumnIndex("client")
            val dateFlightIndex = c.getColumnIndex("dateFlight")
            val flightIndex = c.getColumnIndex("flight")
            val travelIndex = c.getColumnIndex("travel")
            val departureIndex = c.getColumnIndex("departure")
            val landingIndex = c.getColumnIndex("landing")
            val stepsIndex = c.getColumnIndex("steps")
            do{
                val spotModel = SpotModel(
                    id = c.getInt(idIndex),
                    client = c.getString(clientIndex),
                    dateFlight = c.getString(dateFlightIndex),
                    flight = c.getString(flightIndex),
                    travel = c.getString(travelIndex),
                    departure = c.getString(departureIndex),
                    landing = c.getString(landingIndex),
                    steps = c.getInt(stepsIndex))
                listSpotModel.add(spotModel)
            } while (c.moveToNext())

        }
        db.close()
        c.close()
        return listSpotModel

    }
}