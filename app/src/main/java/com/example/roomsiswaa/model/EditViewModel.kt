package com.example.roomsiswaa.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsiswaa.repositori.RepositoriSiswa
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
) : ViewModel() {

    var siswaUiState by mutableStateOf(UIStateSiswa())
        private set

    private val itemId: Int = 0
    //checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            siswaUiState = repositoriSiswa.getSiswaStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateSiswa(true)
        }
    }

    suspend fun updateSiswa() {
        if (validasiInput(siswaUiState.detailSiswa)) {
            repositoriSiswa.updateSiswa(siswaUiState.detailSiswa.toSiswa())
        }
        else {
            println("Data tidak valid")
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        siswaUiState =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }
    private fun validasiInput(uiState: DetailSiswa = siswaUiState.detailSiswa ): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }
}