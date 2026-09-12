package com.example.ehailing.data

data class Place(
    val name: String,
    val address: String,
    val lat: Double,
    val lng: Double,
)

object SamplePlaces {
    val recent = listOf(
        Place("Sandton City",        "Sandton, Johannesburg", -26.1076, 28.0567),
        Place("O.R. Tambo Airport",  "Kempton Park",          -26.1392, 28.2460),
        Place("Rosebank Mall",       "Rosebank, Johannesburg",-26.1460, 28.0423),
        Place("V&A Waterfront",      "Cape Town",             -33.9025, 18.4207),
    )

    val suggestions = listOf(
        Place("Melrose Arch",        "Birnam, Johannesburg",  -26.1348, 28.0680),
        Place("Mall of Africa",      "Waterfall City",        -25.9940, 28.1140),
        Place("Nelson Mandela Square","Sandton",              -26.1084, 28.0562),
        Place("Menlyn Park",         "Pretoria",              -25.7843, 28.2779),
        Place("Gateway Theatre",     "Umhlanga, Durban",      -29.7296, 31.0663),
    )

    val pickup = Place("Current location", "Pinelands, Cape Town", -33.9249, 18.4241)
}