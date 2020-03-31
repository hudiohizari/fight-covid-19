package id.rumahawan.fightcovid19.repomanager.remote

import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>): T{
        val response = call.invoke()

        if (response.isSuccessful){
//            val body = Gson().toJson(response.body())
//            val status = JSONObject(body).getString("status")
//            if (status != KConstant.RESPONSE_OK_STATUS_CODE &&
//                status != KConstant.RESPONSE_INVALID_DEVICE &&
//                status != KConstant.RESPONSE_MINIMAL &&
//                status != KConstant.RESPONSE_BLOCKIR_ACCOUNT &&
//                status != KConstant.RESPONSE_PROSES_REGISTER &&
//                status != KConstant.RESPONSE_PENDING_CODE){
//                val message = JSONObject(body).getString("message")
//                throw ApiException(message)
//            }
            return response.body()!!
        } else {
            throw Exception("Error: (${response.code()})${response.message()}")
        }
    }

}