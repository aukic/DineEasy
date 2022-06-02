package ukic.ante.dineeasy.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ukic.ante.dineeasy.data.ReservationRepository
import ukic.ante.dineeasy.data.ReservationRepositoryImpl
import ukic.ante.dineeasy.presentation.ReservationListViewModel

val viewModelModule = module {
    viewModel { ReservationListViewModel(get()) }
}

val repositoryModule = module {
    single<ReservationRepository>{ReservationRepositoryImpl()}
}