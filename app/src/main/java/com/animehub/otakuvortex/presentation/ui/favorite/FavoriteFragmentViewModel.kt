package com.animehub.otakuvortex.presentation.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.animehub.otakuvortex.data.local.database.OtakuVortexDB
import com.animehub.otakuvortex.data.local.model.AnimeContent
import com.animehub.otakuvortex.data.local.model.CharacterContent
import com.animehub.otakuvortex.data.local.model.MangaContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteFragmentViewModel@Inject constructor(
    private val db: OtakuVortexDB
): ViewModel() {

    private val _favoriteAnimeListValue = MutableLiveData<List<AnimeContent>>()
    var favoriteAnimeListValue: LiveData<List<AnimeContent>> = _favoriteAnimeListValue

    private val _favoriteMangaListValue = MutableLiveData<List<MangaContent>>()
    var favoriteMangaListValue: LiveData<List<MangaContent>> = _favoriteMangaListValue

    private val _favoriteCharacterListValue = MutableLiveData<List<CharacterContent>>()
    var favoriteCharacterListValue: LiveData<List<CharacterContent>> = _favoriteCharacterListValue

    private val _insertSuccessfullResponseForAnime = MutableLiveData<Boolean>()
    var insertSuccessfullResponseForAnime: MutableLiveData<Boolean> = _insertSuccessfullResponseForAnime

    private val _deleteSuccessfullResponseForAnime = MutableLiveData<Boolean>()
    var deleteSuccessfullResponseForAnime: MutableLiveData<Boolean> = _deleteSuccessfullResponseForAnime

    private val _insertSuccessfullResponseForManga = MutableLiveData<Boolean>()
    var insertSuccessfullResponseForManga: MutableLiveData<Boolean> = _insertSuccessfullResponseForManga

    private val _deleteSuccessfullResponseForManga = MutableLiveData<Boolean>()
    var deleteSuccessfullResponseForManga: MutableLiveData<Boolean> = _deleteSuccessfullResponseForManga

    private val _insertSuccessfullResponseForCharacter = MutableLiveData<Boolean>()
    var insertSuccessfullResponseForCharacter: MutableLiveData<Boolean> = _insertSuccessfullResponseForCharacter

    private val _deleteSuccessfullResponseForCharacter = MutableLiveData<Boolean>()
    var deleteSuccessfullResponse: MutableLiveData<Boolean> = _deleteSuccessfullResponseForCharacter

    val _savedMangaLiveData = MutableLiveData<MangaContent>()
    val savedMangaLiveData: LiveData<MangaContent>
        get() = _savedMangaLiveData

    val _savedAnimeLiveData = MutableLiveData<AnimeContent>()
    val savedAnimeLiveData: LiveData<AnimeContent>
        get() = _savedAnimeLiveData

    val _savedCharacterLiveData = MutableLiveData<CharacterContent>()
    val savedCharacterLiveData: LiveData<CharacterContent>
        get() = _savedCharacterLiveData

    // for anime

    fun getFavoriteAnimeContentList(){
        viewModelScope.launch(Dispatchers.IO){
            val favoriteListState = db.animeContentRepository().getAllFavoriteCotent()
            _favoriteAnimeListValue.postValue(favoriteListState)
        }
    }

    fun insertFavoriteAnimeToDB(content: AnimeContent){
        viewModelScope.launch(Dispatchers.IO){
            db.animeContentRepository().upsertFavorite(content)
            _insertSuccessfullResponseForAnime.postValue(true)
        }
    }

    fun deleteFavoriteAnimeFromDB(content: AnimeContent){
        viewModelScope.launch(Dispatchers.IO){
            db.animeContentRepository().deleteFavorite(content)
            _deleteSuccessfullResponseForAnime.postValue(true)
        }
    }

    // for manga

    fun getFavoriteMangaContentList(){
        viewModelScope.launch(Dispatchers.IO){
            val favoriteListState = db.mangaContentRepository().getAllFavoriteCotent()
            _favoriteMangaListValue.postValue(favoriteListState)
        }
    }

    fun insertFavoriteMangaToDB(content: MangaContent){
        viewModelScope.launch(Dispatchers.IO){
            db.mangaContentRepository().upsertFavorite(content)
            _insertSuccessfullResponseForManga.postValue(true)
        }
    }

    fun deleteFavoriteMangaFromDB(content: MangaContent){
        viewModelScope.launch(Dispatchers.IO){
            db.mangaContentRepository().deleteFavorite(content)
            _deleteSuccessfullResponseForManga.postValue(true)
        }
    }

    // for Character

    fun getFavoriteMangaCharacterList(){
        viewModelScope.launch(Dispatchers.IO){
            val favoriteListState = db.characterContentRepository().getAllFavoriteCotent()
            _favoriteCharacterListValue.postValue(favoriteListState)
        }
    }

    fun insertFavoriteCharacterToDB(content: CharacterContent){
        viewModelScope.launch(Dispatchers.IO){
            db.characterContentRepository().upsertFavorite(content)
            _insertSuccessfullResponseForCharacter.postValue(true)
        }
    }

    fun deleteFavoriteCharacterFromDB(content: CharacterContent){
        viewModelScope.launch(Dispatchers.IO){
            db.characterContentRepository().deleteFavorite(content)
            _deleteSuccessfullResponseForCharacter.postValue(true)
        }
    }

}