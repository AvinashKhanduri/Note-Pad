package com.example.notepad.di

import android.content.Context
import androidx.room.Room
import com.example.notepad.data.NoteDataBaseDAo
import com.example.notepad.data.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)// It's mean this AppModule object will store inside SingletonComponent.Which is a part of
                                        //Dagger hilt component hierarchy.
object AppModule {
        @Singleton// This annotation is related to SingletonComponent
        @Provides
        fun providesNotesDao(noteDatabase: NoteDatabase):NoteDataBaseDAo{
           return noteDatabase.noteDao()
        }

        @Singleton
        @Provides
        fun provideAppDatabase(@ApplicationContext context: Context):NoteDatabase{
            return  Room.databaseBuilder(context = context,
                    klass = NoteDatabase::class.java,
                    name = "notes_db").fallbackToDestructiveMigration().build()
        }
}

//@Module
//@InstallIn(ActivityRetainedComponent::class)
//object Module2{
//}
