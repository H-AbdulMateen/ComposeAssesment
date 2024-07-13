package com.abdulmateen.composeassesment.data.remote.dto

import kotlinx.serialization.*

@Serializable
data class Medication(
    val name: String,
    val dose: String,
    val strength: String
)

@Serializable
data class ClassName(
    val associatedDrug: List<Medication> = emptyList(),
    @SerialName("associatedDrug#2") val associatedDrug2: List<Medication> = emptyList()
)

@Serializable
data class MedicationsClass(
    val className: List<ClassName> = emptyList(),
    @SerialName("className2") val className2: List<ClassName> = emptyList()
)

@Serializable
data class Medications(
    val medicationsClasses: List<MedicationsClass> = emptyList()
)

@Serializable
data class Diabetes(
    val medications: List<Medications> = emptyList(),
    val labs: List<Map<String, String>> = emptyList()
)

@Serializable
data class Problem(
    val Diabetes: List<Diabetes> = emptyList(),
    val Asthma: List<Map<String, String>> = emptyList()
)

@Serializable
data class ProblemsWrapper(
    val problems: List<Problem>
)