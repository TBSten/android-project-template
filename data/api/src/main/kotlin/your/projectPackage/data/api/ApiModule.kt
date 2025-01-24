package your.projectPackage.data.api

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.create
import your.projectPackage.data.api.generated.api.PostsApi

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
    @Binds
    @Singleton
    fun provideApi(
        retrofit: Retrofit,
    ): PostsApi = retrofit.create<PostsApi>()
}
