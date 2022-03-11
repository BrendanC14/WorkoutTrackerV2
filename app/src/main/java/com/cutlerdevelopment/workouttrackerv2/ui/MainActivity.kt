package com.cutlerdevelopment.workouttrackerv2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cutlerdevelopment.workouttrackerv2.models.AbstractSaveable
import com.cutlerdevelopment.workouttrackerv2.models.ExerciseTemplate
import com.cutlerdevelopment.workouttrackerv2.models.ExerciseType
import com.cutlerdevelopment.workouttrackerv2.R
import com.cutlerdevelopment.workouttrackerv2.integration.firestore.ExerciseTemplateFirestoreHandler
import com.cutlerdevelopment.workouttrackerv2.integration.firestore.IFirestoreListener
import java.lang.Exception

class MainActivity : AppCompatActivity(), IFirestoreListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val template = ExerciseTemplate(1, "Test", ExerciseType.REPS)
        ExerciseTemplateFirestoreHandler(this).save(template)
    }

    override fun itemSaved() {

    }

    override fun itemFailedToSave(exception: Exception) {

    }

    override fun itemRetrieved(item: AbstractSaveable) {

    }

    override fun itemsRetrieved(items: MutableList<AbstractSaveable>) {

    }


}