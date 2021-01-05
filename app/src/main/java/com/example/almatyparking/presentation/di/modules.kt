package com.example.almatyparking.presentation.di

import android.util.Log
import com.example.almatyparking.data.ParkingPlaceDatabase.ParkingPlaceRoomDatabase
import com.example.almatyparking.data.repository.ParkingPlacesRepositoryImpl
import com.example.almatyparking.data.repository.UserRepositoryImpl
import com.example.almatyparking.data.source.remote.AlmatyParkingApi
import com.example.almatyparking.domain.repository.ParkingPlacesRepository
import com.example.almatyparking.domain.repository.UserRepository
import com.example.almatyparking.extensions.ParkingPlaceViewModel
import com.example.almatyparking.presentation.parking_place.ParkingPlacesViewModel
import com.example.almatyparking.utils.AppConstants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
//    single(named("api_key")) { provideApiKey() }
    single(named("base_url")) { provideBaseUrl() }
    //single { provideHttpLoggingInterceptor() }
    //single { provideStethoInterceptor() }
    //single { provideAuthInterceptor(apiKey = get(named("api_key"))) }
//    single { provideOkHttp(
//        loggingInterceptor = get(),
//        stethoInterceptor = get(),
//        authInterceptor = get()
//    )}

    single { provideCallAdapterFactory() }
    single { provideConverterFactory() }
    single { provideRetrofit(
        baseUrl = get(named("base_url")),
        okHttpClient = get(),
        gsonConverterFactory = get(),
        callAdapterFactory = get()
    )}
    single { provideAlmatyParkingApi(retrofit = get()) }
}


val roomModule = module {
    single { ParkingPlaceRoomDatabase.getDatabase(
        context = androidApplication(),
        scope = get()
    ) }

    single(createdAtStart = false) { get<ParkingPlaceRoomDatabase>().parkingPlaceDao() }

    factory { SupervisorJob() }
    factory { CoroutineScope(Dispatchers.IO) }
}

val repositoryModule = module {
    single { provideParkingPlacesRepository(almatyParkingApi = get()) }
}

val viewModelModule = module {
    viewModel { ParkingPlaceViewModel(parkingPlaceDao = get()) }
    viewModel { ParkingPlacesViewModel(parkingPlacesRepository = get ()) }
}

val appModule = listOf(networkModule, roomModule, repositoryModule, viewModelModule)

//--------------------------------------Repository--------------------------------------------------

fun provideParkingPlacesRepository(almatyParkingApi: AlmatyParkingApi): ParkingPlacesRepository = ParkingPlacesRepositoryImpl(almatyParkingApi)

//--------------------------------------Network-----------------------------------------------------


fun provideBaseUrl(): String = AppConstants.BASE_URL

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor(
        HttpLoggingInterceptor.Logger { message -> Log.d("OkHttp", message)}
    ).apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

//fun provideStethoInterceptor(): StethoInterceptor = StethoInterceptor()
//
//fun provideAuthInterceptor(apiKey: String): Interceptor {
//    return Interceptor { chain ->
//        val newUrl = chain.request().url()
//            .newBuilder()
//            .addQueryParameter("api_key", apiKey)
//            .build()
//        val newRequest = chain.request()
//            .newBuilder()
//            .url(newUrl)
//            .build()
//        chain.proceed(newRequest)
//    }
//}

//fun provideOkHttp(
//    loggingInterceptor: HttpLoggingInterceptor,
//    stethoInterceptor: StethoInterceptor,
//    authInterceptor: Interceptor
//): OkHttpClient {
//    return OkHttpClient.Builder()
//        .addNetworkInterceptor(stethoInterceptor)
//        .addInterceptor(loggingInterceptor)
//        .addInterceptor(authInterceptor)
//        .build()
//}

fun provideCallAdapterFactory(): CallAdapter.Factory = CoroutineCallAdapterFactory()

fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create()

fun provideRetrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient,
    gsonConverterFactory: Converter.Factory,
    callAdapterFactory: CallAdapter.Factory
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(callAdapterFactory)
        .build()
}

fun provideAlmatyParkingApi(retrofit: Retrofit): AlmatyParkingApi = retrofit.create(
    AlmatyParkingApi::class.java)


