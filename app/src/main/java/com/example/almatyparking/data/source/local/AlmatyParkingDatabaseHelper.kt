package com.example.almatyparking.data.source.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.almatyparking.data.models.CarData
import com.example.almatyparking.data.models.PaymentData
import com.example.almatyparking.data.models.UserData

// Database Info
private const val DATABASE_NAME = "aparkingDB"
private const val DATABASE_VERSION = 1

// Table Name
private const val TABLE_PAYMENT = "payment"
private const val TABLE_CAR = "cars"
private const val TABLE_USER = "users"

// Payment Table Columns

private const val KEY_ID = "id"
private const val KEY_COST = "cost"
private const val KEY_FROM = "fromDate"
private const val KEY_TO = "toDate"
private const val KEY_ISPAID= "isPaid"
private const val KEY_PARKINGID= "parkingID"
private const val KEY_CARID = "carID"

// Cars Table Columns
private const val CAR_ID = "id"
private const val CAR_NAME = "name"
private const val CAR_NUMBER = "carNumber"

// User Table Columns
private const val USERid = "id"
private const val USER_PNUMBER = "name"
private const val USER_BALANCE = "carNumber"
private const val USER_CARLIST = "carList"



class AlmatyParkingDatabaseHelper private constructor(context: Context?):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private var INSTANCE: AlmatyParkingDatabaseHelper? = null

        fun getInstance(context: Context?): AlmatyParkingDatabaseHelper {
            if (INSTANCE == null) {
                INSTANCE = AlmatyParkingDatabaseHelper(context)
            }
            return INSTANCE!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {

        val CREATE_PAYMENT_TABLE = "CREATE TABLE " + TABLE_PAYMENT +
                " (" +
                KEY_ID + " INTEGER," +
                KEY_COST + " INTEGER," +
                KEY_FROM + " TEXT," +
                KEY_TO + " TEXT," +
                KEY_ISPAID + " INTEGER," +
                KEY_PARKINGID + " INTEGER," +
                KEY_CARID + " INTEGER" +
                ")"

        db.execSQL(CREATE_PAYMENT_TABLE)

        val CREATE_CAR_TABLE = "CREATE TABLE " + TABLE_CAR +
                " (" +
                CAR_ID + " INTEGER," +
                CAR_NAME + " TEXT," +
                CAR_NUMBER + " TEXT" +
                ")"

        db.execSQL(CREATE_CAR_TABLE)

        val CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER +
                " (" +
                USERid + " INTEGER," +
                USER_PNUMBER + " TEXT," +
                USER_BALANCE + " INTEGER," +
                USER_CARLIST + " TEXT" +
                ")"

        db.execSQL(CREATE_USER_TABLE)

        setPayments(db)
        setCars(db)
        setUsers(db)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_PAYMENT")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_CAR")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
            onCreate(db)
        }
    }

    fun setUsers(db: SQLiteDatabase){

        db.beginTransaction()
        val userList = mutableListOf<UserData>()
        val id_list = mutableListOf<Int>()

        id_list.add(1)
        id_list.add(2)

        var user1 = UserData(
            1,
            "+7(777) 114 33 59",
            2000,
            id_list.joinToString()
        )
        userList.add(user1)

        try {
            for (user in userList) {
                val values = ContentValues()
                values.put(USERid, user.id)
                values.put(USER_PNUMBER, user.phone_number)
                values.put(USER_BALANCE, user.balance)

                db.insertOrThrow(TABLE_USER, null, values)
            }
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.d("paymentsDB", "Error while trying to add payment to database")
        } finally {
            db.endTransaction()
        }
    }

    fun setPayments(db: SQLiteDatabase){
        db.beginTransaction()

        val paymentList = mutableListOf<PaymentData>()
        var payment1 = PaymentData(
            1,
            100,
            "2020-04-23 18:25:43",
            "2020-04-23 19:25:43",
            0,
            1,
            1
        )
        paymentList.add(payment1)
        var payment2 = PaymentData(
            2,
            200,
            "2020-04-23 18:25:43",
            "2020-04-23 20:25:43",
            0,
            3,
            1
        )
        paymentList.add(payment2)
        var payment3 = PaymentData(
            3,
            100,
            "2020-04-23T18:25:43",
            "2020-04-23T18:25:43",
            0,
            2,
            1
        )
        paymentList.add(payment3)

        var payment4 = PaymentData(
            4,
            100,
            "2020-04-23T18:25:43",
            "2020-04-23T18:25:43",
            0,
            2,
            1
        )
        paymentList.add(payment4)

        var payment5 = PaymentData(
            5,
            200,
            "2020-04-23 18:25:43",
            "2020-04-23 20:25:43",
            0,
            2,
            1
        )
        paymentList.add(payment5)

        var payment6 = PaymentData(
            6,
            200,
            "2020-04-23 18:25:43",
            "2020-04-23 20:25:43",
            0,
            2,
            1
        )
        paymentList.add(payment6)
        try {
            for (payment in paymentList) {
                val values = ContentValues()
                values.put(KEY_ID, payment.id)
                values.put(KEY_COST, payment.cost)
                values.put(KEY_FROM, payment.fromDate)
                values.put(KEY_TO, payment.toDate)
                values.put(KEY_ISPAID, payment.isPaid)
                values.put(KEY_CARID, payment.carID)
                values.put(KEY_PARKINGID, payment.parkingID)

                db.insertOrThrow(TABLE_PAYMENT, null, values)
            }
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.d("paymentsDB", "Error while trying to add payment to database")
        } finally {
            db.endTransaction()
        }
    }

    fun getUser(id: Int): UserData? {

        var user: UserData? = null

        val USER_SELECT_QUERY = "SELECT * FROM $TABLE_USER WHERE $KEY_ID = $id"

        val db = readableDatabase
        val cursor = db.rawQuery(USER_SELECT_QUERY, null)
        try {
            if (cursor!!.moveToFirst()) {
                do {
                    user = createUser(cursor)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.d("PaymentsDB", "Error while trying to get payment from database")
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }
        return user
    }

    fun setCars(db: SQLiteDatabase){
        db.beginTransaction()

        val carList = mutableListOf<CarData>()
        var car1 = CarData(
            1,
            "Toyota Land Cruiser",
            "777AAA01"
        )
        carList.add(car1)
        var car2 = CarData(
            2,
            "Lambo",
            "123BBB02"
        )
        carList.add(car2)
        try {
            for (car in carList) {
                val values = ContentValues()
                values.put(CAR_ID, car.id)
                values.put(CAR_NAME, car.name)
                values.put(CAR_NUMBER, car.carNumber)

                db.insertOrThrow(TABLE_CAR, null, values)
            }
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.d("aparkingDB", "Error while trying to add payment to database")
        } finally {
            db.endTransaction()
        }
    }

    fun getPaymentsToPay(user_id: Int): List<PaymentData>{


        val paymentList = mutableListOf<PaymentData>()

        var user: UserData? = getUser(user_id)

        val searchBY = user!!.cars

        val PAYMENTS_SELECT_QUERY = "SELECT * FROM $TABLE_PAYMENT WHERE isPaid = 0"

        val db = readableDatabase
        val cursor = db.rawQuery(PAYMENTS_SELECT_QUERY, null)
        try {
            if (cursor!!.moveToFirst()) {
                do {
                    paymentList.add(createPayment(cursor))
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.d("PaymentsDB", "Error while trying to get payment from database")
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }
        return paymentList
    }

    fun getPayment(id: Int): PaymentData? {
        var payment: PaymentData? = null

        val PAYMENT_SELECT_QUERY = "SELECT * FROM $TABLE_PAYMENT WHERE $KEY_ID = $id"

        val db = readableDatabase
        val cursor = db.rawQuery(PAYMENT_SELECT_QUERY, null)
        try {
            if (cursor!!.moveToFirst()) {
                do {
                    payment = createPayment(cursor)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.d("PaymentsDB", "Error while trying to get payment from database")
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }
        return payment
    }


    fun getCars(user_id: Int): List<CarData>{

        var user: UserData? = getUser(user_id)

        val searchBY = user!!.cars

        val carList = mutableListOf<CarData>()

        val CARS_SELECT_QUERY = "SELECT * FROM $TABLE_CAR"

        //where id in($searchBY)

        val db = readableDatabase
        val cursor = db.rawQuery(CARS_SELECT_QUERY, null)
        try {
            if (cursor!!.moveToFirst()) {
                do {
                    carList.add(createCar(cursor))
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.d("PaymentsDB", "Error while trying to get car from database")
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }
        return carList
    }

    fun getCar(id: Int): CarData? {
        var car: CarData? = null

        val CAR_SELECT_QUERY = "SELECT * FROM $TABLE_CAR WHERE $KEY_ID = $id"

        val db = readableDatabase
        val cursor = db.rawQuery(CAR_SELECT_QUERY, null)
        try {
            if (cursor!!.moveToFirst()) {
                do {
                    car = createCar(cursor)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.d("aparkingDB", "Error while trying to get car from database")
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }
        return car
    }

    fun updatePayment(id: Int, balance: Int){

        val db = writableDatabase

        var payment: PaymentData? = getPayment(id)
        try {
            val cv2 = ContentValues()
            cv2.put(KEY_ID, payment!!.id)
            cv2.put(KEY_COST, payment.cost )
            cv2.put(KEY_FROM, payment.fromDate)
            cv2.put(KEY_TO, payment.toDate)
            cv2.put(KEY_ISPAID, 1)
            cv2.put(KEY_CARID, payment.carID)
            cv2.put(KEY_PARKINGID, payment.parkingID)
            val whereclause2 = "$KEY_ID=?"
            val whereargs2 = arrayOf(id.toString())
            db.update(TABLE_PAYMENT, cv2, whereclause2, whereargs2)
        } catch (e: Exception) {
            Log.d("PaymentsDB", "Error while trying to delete all payments")
        }


        var user: UserData? = getUser(1)

        try {
            val cv1 = ContentValues()
            cv1.put(USERid, user!!.id)
            cv1.put(USER_PNUMBER, user!!.phone_number)
            cv1.put(USER_BALANCE, balance)
            cv1.put(USER_CARLIST, user.cars)
            val whereclause1 = "$USERid=?"
            val whereargs1 = arrayOf(id.toString())
            db.update(TABLE_USER, cv1, whereclause1, whereargs1)
        } catch (e: Exception) {
            Log.d("PaymentsDB", "Error while trying to delete all payments")
        }
    }

    fun insertCar(name: String, carNumber: String){
        val db = writableDatabase
        val new_id = (5..100).random()
        try {
            val cv = ContentValues()
            cv.put(CAR_ID, new_id)
            cv.put(CAR_NAME, name)
            cv.put(CAR_NUMBER, carNumber)

            db.insert(TABLE_CAR, null, cv)
        } catch (e: Exception) {
            Log.d("PaymentsDB", "Error while trying to delete all payments")
        }

    }

    fun deleteAllPayments() {
        val db = writableDatabase
        try {
            db.delete(TABLE_PAYMENT, null, null)
        } catch (e: Exception) {
            Log.d("PaymentsDB", "Error while trying to delete all payments")
        }

    }



    private fun createPayment(cursor: Cursor): PaymentData {
        return PaymentData(
            cursor.getInt(cursor.getColumnIndex(KEY_ID)),
            cursor.getInt(cursor.getColumnIndex(KEY_COST)),
            cursor.getString(cursor.getColumnIndex(KEY_FROM)),
            cursor.getString(cursor.getColumnIndex(KEY_TO)),
            cursor.getInt(cursor.getColumnIndex(KEY_ISPAID)),
            cursor.getInt(cursor.getColumnIndex(KEY_CARID)),
            cursor.getInt(cursor.getColumnIndex(KEY_PARKINGID)))
    }

    private fun createCar(cursor: Cursor): CarData {
        return CarData(
            cursor.getInt(cursor.getColumnIndex(CAR_ID)),
            cursor.getString(cursor.getColumnIndex(CAR_NAME)),
            cursor.getString(cursor.getColumnIndex(CAR_NUMBER)))
    }

    private fun createUser(cursor: Cursor): UserData {

        return UserData(
            cursor.getInt(cursor.getColumnIndex(USERid)),
            cursor.getString(cursor.getColumnIndex(USER_PNUMBER)),
            cursor.getInt(cursor.getColumnIndex(USER_BALANCE)),
            cursor.getString(cursor.getColumnIndex(USER_CARLIST)))

    }
}


