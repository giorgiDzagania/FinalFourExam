package com.exercise.finalfourexam.model

//class gsonFile : ArrayList<PersonDetails>()

data class PersonDetails(
    val id: Int,
    val image: String?,
    val is_typing: Boolean,
    val last_active: String,
    val last_message: String,
    val laste_message_type: Type,
    val owner: String,
    val unread_messages: Int
){
    enum class Type{
        TEXT,
        FILE,
        VOICE,
    }
}