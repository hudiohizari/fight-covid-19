package id.rumahawan.ifightco.repomanager.remote

import id.rumahawan.ifightco.BuildConfig
import id.rumahawan.ifightco.features.main.model.response.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface RemoteRequestManager {

    @GET("app/version")
    suspend fun getVersion(): Response<ResponseVersion>

    @GET("ratio")
    suspend fun getRatio(): Response<ResponseRatio>

    @GET("provincess")
    suspend fun getProvinces(): Response<ResponseProvince>

    @GET("fightcovid19/notification")
    suspend fun getInbox(): Response<ResponseInbox>

    @GET("hospital/provincess/{provence_id}/{lat}/{lng}")
    suspend fun getHospitals(
        @Path("provence_id") provenceId: String,
        @Path("lat") lat: Double,
        @Path("lng") lng: Double
    ): Response<ResponseHospital>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : RemoteRequestManager {

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(networkConnectionInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .build()
                .create(RemoteRequestManager::class.java)
        }
    }
}