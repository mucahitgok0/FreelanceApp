package com.androiddevelopers.freelanceapp.di

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.androiddevelopers.freelanceapp.R
import com.androiddevelopers.freelanceapp.repo.FirebaseRepoImpl
import com.androiddevelopers.freelanceapp.repo.FirebaseRepoInterFace
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGlide(@ApplicationContext context: Context) : RequestManager  {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        return Glide.with(context)
            .setDefaultRequestOptions(
                RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.error)
                    .placeholder(circularProgressDrawable)
            )
    }

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideStorage() = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFireStore() = FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun provideFirebaseRepo(auth: FirebaseAuth,firestore: FirebaseFirestore): FirebaseRepoInterFace {
        return FirebaseRepoImpl(auth,firestore)
    }
}
