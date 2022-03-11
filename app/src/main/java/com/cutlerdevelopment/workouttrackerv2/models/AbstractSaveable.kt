package com.cutlerdevelopment.workouttrackerv2.models

abstract class AbstractSaveable(open val id : Int) {

    abstract fun convertToSaveableMap(): HashMap<String, Any>

}