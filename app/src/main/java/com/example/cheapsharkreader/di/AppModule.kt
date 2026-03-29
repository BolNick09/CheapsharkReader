package com.example.cheapsharkreader.di

import com.example.cheapsharkreader.data.remote.network.RetrofitInstance
import com.example.cheapsharkreader.data.repository.DealRepositoryImpl
import com.example.cheapsharkreader.data.repository.GameRepositoryImpl
import com.example.cheapsharkreader.domain.repository.DealRepository
import com.example.cheapsharkreader.domain.repository.GameRepository
import com.example.cheapsharkreader.presentation.viewmodel.DealsViewModel
import com.example.cheapsharkreader.presentation.viewmodel.GameViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { RetrofitInstance.api }

    single<GameRepository> {
        GameRepositoryImpl(get())
    }

    viewModel {
        GameViewModel(get())
    }

    single<DealRepository> {
        DealRepositoryImpl(get())
    }

    viewModel {
        DealsViewModel(get())
    }
}