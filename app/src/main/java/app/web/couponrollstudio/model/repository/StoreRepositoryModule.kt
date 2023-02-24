package app.web.couponrollstudio.model.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class StoreRepositoryModule {
    @Provides
    fun provideStoreRepository(storeRepositoryImpl: StoreRepositoryImpl): StoreRepository {
        return storeRepositoryImpl
    }
}