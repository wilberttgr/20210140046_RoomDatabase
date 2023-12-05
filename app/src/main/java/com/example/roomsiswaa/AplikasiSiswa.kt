package com.example.roomsiswaa

import android.app.Application
import com.example.roomsiswaa.repositori.ContainerApp
import com.example.roomsiswaa.repositori.ContainerDataApp

class AplikasiSiswa : Application() {
    /**
     * AppContainer instance digunakan oleh kelas-kelas lainnya untuk mendapatkan dependensi
     */
    lateinit var container: ContainerApp
    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}