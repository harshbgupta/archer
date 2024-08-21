package corp.hell.kernel.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
/**
 * Convert Json String to  any object
 */
inline fun <reified T> fromJsonString(json: String?): T? {
    return if (json != null) Gson().fromJson(json, object : TypeToken<T>() {}.type) else null
}


/**
 * Convert any object to Json String
 */
fun Any?.toJsonString(): String {
    return if (this != null) Gson().toJson(this) else "<<NULL>>"
}
