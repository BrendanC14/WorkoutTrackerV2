package com.cutlerdevelopment.workouttrackerv2.integration.firestore

import com.cutlerdevelopment.workouttrackerv2.models.AbstractSaveable
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception

abstract class AbstractFirestoreHandler<T : AbstractSaveable> {

    protected val db = Firebase.firestore
    protected val listeners = mutableListOf<IFirestoreListener>()

    protected fun addDocument(collection : CollectionReference, map : HashMap<String, Any>) {
        collection.add(map)
            .addOnSuccessListener { documentReference -> documentSaved(documentReference)}
            .addOnFailureListener { e -> documentFailedToSave(e)}
    }
    protected fun documentSaved(documentReference: DocumentReference) {
        listeners.forEach { listener -> listener.itemSaved() }
    }

    protected fun documentFailedToSave(exception: Exception) {
        listeners.forEach { listener -> listener.itemFailedToSave(exception) }
    }

    protected fun retrieveDocument(documentReference: DocumentReference) {
        documentReference.get()
            .addOnSuccessListener { result -> documentRetrieved(result) }
            .addOnFailureListener { e -> documentFailedToRetrieve(e) }
    }
    protected fun documentRetrieved(documentSnapshot: DocumentSnapshot) {
        val template = convertDocumentToItem(documentSnapshot)
        if (template != null) {
            listeners.forEach { listener -> listener.itemRetrieved(template) }
        }
    }
    protected fun documentFailedToRetrieve(exception: Exception) {
        listeners.forEach { listener -> listener.itemFailedToSave(exception) }
    }

    protected fun retrieveCollection(collection: CollectionReference) {
        collection.get()
            .addOnSuccessListener { result -> collectionRetrieved(result) }
            .addOnFailureListener { e -> collectionFailedToRetrieve(e) }
    }
    protected fun collectionRetrieved(result: QuerySnapshot) {
        val templates = mutableListOf<AbstractSaveable>()
        for (document in result) {
            val template = convertDocumentToItem(document)
            if (template != null) templates.add(template)
        }
        listeners.forEach { listener -> listener.itemsRetrieved(templates) }
    }

    protected fun collectionFailedToRetrieve(exception: Exception) {
        listeners.forEach { listener -> listener.itemsRetrieved(mutableListOf()) }
    }

    fun addListener(listener: IFirestoreListener) {
        listeners.add(listener)
    }

    abstract fun convertDocumentToItem(documentSnapshot: DocumentSnapshot) : T?
}