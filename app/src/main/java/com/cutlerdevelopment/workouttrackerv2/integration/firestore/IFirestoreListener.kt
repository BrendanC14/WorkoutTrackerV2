package com.cutlerdevelopment.workouttrackerv2.integration.firestore

import com.cutlerdevelopment.workouttrackerv2.models.AbstractSaveable
import java.lang.Exception

interface IFirestoreListener {
    fun itemSaved()
    fun itemFailedToSave(exception : Exception)
    fun itemRetrieved(item : AbstractSaveable)
    fun itemsRetrieved(items : MutableList<AbstractSaveable>)
}