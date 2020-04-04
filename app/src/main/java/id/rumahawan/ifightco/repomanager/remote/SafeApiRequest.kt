package id.rumahawan.ifightco.repomanager.remote

import id.rumahawan.ifightco.utils.ApiException
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>): T{
        val response = call.invoke()

        if (response.isSuccessful){
//            val body = Gson().toJson(response.body())
//            try {
//                val status = JSONObject(body).getInt("status")
//                if (status <= 200){
//                    val message = JSONObject(body).getString("error")
//                    throw ApiException(message)
//                }
//            } catch (e: JSONException) {
//                Log.d("API Execption", "$e in $body")
//            }
            return response.body()!!
        } else {
            throw ApiException("Error: (${response.code()})${response.message()}")
        }
    }

}