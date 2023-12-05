package com.example.roomsiswaa.navigasi

interface DestinasiNavigasi {
    /**
     * Nama unik untuk menentukan jalur untuk composable
     */
    val route: String

    /**
     * String resource id yang berisi judul akan ditampilkan dilayar halaman.
     */
    val titleRes: Int
}