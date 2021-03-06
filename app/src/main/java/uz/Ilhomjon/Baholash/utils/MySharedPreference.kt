package uz.Ilhomjon.Baholash.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.Ilhomjon.Baholash.models.Pupil

object MySharedPreference {
    private const val NAME = "my_catch_file"
    private const val MODE = Context.MODE_PRIVATE

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context){
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation : (SharedPreferences.Editor)-> Unit){
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var obektString: ArrayList<Pupil>
        get() = gsonStringToArray(sharedPreferences.getString("obekt", "[]")!!)
        set(value) = sharedPreferences.edit {
            if (value != null) {
                it.putString("obekt", arrayToGsonString(value))
            }
        }

    fun arrayToGsonString(arrayList: ArrayList<Pupil>): String {
        return Gson().toJson(arrayList)
    }

    fun gsonStringToArray(gsonString: String): ArrayList<Pupil> {
        val typeToken = object : TypeToken<ArrayList<Pupil>>() {}.type
        return Gson().fromJson(gsonString, typeToken)
    }
}