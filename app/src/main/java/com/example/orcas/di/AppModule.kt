package com.example.orcas.di

import com.example.orcas.data.remote.api.ApiInterface
import com.example.pokedexfromscratch.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn()
class AppModule {

    @Singleton
    @Provides
    fun instantiateRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun instantiateApiInterface(retro: Retrofit): ApiInterface {
        return retro
            .create(ApiInterface::class.java)
    }


}