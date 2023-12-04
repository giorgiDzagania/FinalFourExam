package com.exercise.finalfourexam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercise.finalfourexam.model.PersonDetails
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val _userFlow = MutableStateFlow<List<PersonDetails>>(emptyList())
    val user: StateFlow<List<PersonDetails>> = _userFlow.asStateFlow()

    init {
        addValues()
    }

    fun addValues() {
        viewModelScope.launch {
            val parsedDataList = parseDialogJson(dialog)
            _userFlow.value = _userFlow.value.toMutableList().also {
                it.addAll(parsedDataList)
            }
        }
    }
        private val dialog = "[\n" +
                "   {\n" +
                "      \"id\":1,\n" +
                "      \"image\":\"https://www.alia.ge/wp-content/uploads/2022/09/grisha.jpg\",\n" +
                "      \"owner\":\"გრიშა ონიანი\",\n" +
                "      \"last_message\":\"თავის ტერიტორიას ბომბავდა\",\n" +
                "      \"last_active\":\"4:20 PM\",\n" +
                "      \"unread_messages\":3,\n" +
                "      \"is_typing\":false,\n" +
                "      \"laste_message_type\":\"text\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":2,\n" +
                "      \"image\":null,\n" +
                "      \"owner\":\"ჯემალ კაკაურიძე\",\n" +
                "      \"last_message\":\"შემოგევლე\",\n" +
                "      \"last_active\":\"3:00 AM\",\n" +
                "      \"unread_messages\":0,\n" +
                "      \"is_typing\":true,\n" +
                "      \"laste_message_type\":\"voice\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":3,\n" +
                "      \"image\":\"https://i.ytimg.com/vi/KYY0TBqTfQg/hqdefault.jpg\",\n" +
                "      \"owner\":\"გურამ ჯინორია\",\n" +
                "      \"last_message\":\"ცოცხალი ვარ მა რა ვარ შე.. როდის იყო კვტარი ტელეფონზე ლაპარაკობდა\",\n" +
                "      \"last_active\":\"1:00 \",\n" +
                "      \"unread_messages\":0,\n" +
                "      \"is_typing\":false,\n" +
                "      \"laste_message_type\":\"file\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":4,\n" +
                "      \"image\":\"\",\n" +
                "      \"owner\":\"კაკო წენგუაშვილი\",\n" +
                "      \"last_message\":\"ადამიანი რო მოსაკლავად გაგიმეტებს თანაც ქალი ის დასანდობი არ არი\",\n" +
                "      \"last_active\":\"1:00 PM\",\n" +
                "      \"unread_messages\":0,\n" +
                "      \"is_typing\":false,\n" +
                "      \"laste_message_type\":\"text\"\n" +
                "   }\n" +
                "]\n"

        private fun parseDialogJson(jsonString: String): List<PersonDetails> {
            val moshi = Moshi.Builder().build()

            val listType = Types.newParameterizedType(List::class.java, PersonDetails::class.java)
            val adapter: JsonAdapter<List<PersonDetails>> = moshi.adapter(listType)

            return try {
                adapter.fromJson(jsonString) ?: emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
}