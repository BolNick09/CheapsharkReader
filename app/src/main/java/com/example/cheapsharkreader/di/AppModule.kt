package com.example.cheapsharkreader.di

import androidx.room.Room
import com.example.cheapsharkreader.data.local.database.AppDatabase
import com.example.cheapsharkreader.data.remote.network.RetrofitInstance
import com.example.cheapsharkreader.data.repository.DealRepositoryImpl
import com.example.cheapsharkreader.data.repository.FavoritesRepositoryImpl
import com.example.cheapsharkreader.data.repository.GameRepositoryImpl
import com.example.cheapsharkreader.data.repository.StoreRepositoryImpl
import com.example.cheapsharkreader.domain.repository.DealRepository
import com.example.cheapsharkreader.domain.repository.FavoritesRepository
import com.example.cheapsharkreader.domain.repository.GameRepository
import com.example.cheapsharkreader.domain.repository.StoreRepository
import com.example.cheapsharkreader.presentation.viewmodel.DealsViewModel
import com.example.cheapsharkreader.presentation.viewmodel.FavoritesViewModel
import com.example.cheapsharkreader.presentation.viewmodel.GameViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { RetrofitInstance.api }

    single<GameRepository> {
        GameRepositoryImpl(get(), get())
    }

    viewModel {
        GameViewModel(get(), get())
    }

    single<DealRepository> {
        DealRepositoryImpl(get(), get())
    }
    single<StoreRepository> {
        StoreRepositoryImpl(get())
    }

    viewModel {
        DealsViewModel(get(), get())
    }

    single<FavoritesRepository> {
        FavoritesRepositoryImpl(get())
    }
    viewModel {
        FavoritesViewModel(get())
    }


    single {
        Room.databaseBuilder(
                get(),
                AppDatabase::class.java,
                "cheapshark_db"
            ).fallbackToDestructiveMigration(true)
            .build()
    }

    single { get<AppDatabase>().gameDao() }
    single { get<AppDatabase>().dealDao() }
    single { get<AppDatabase>().favoriteDao() }
}