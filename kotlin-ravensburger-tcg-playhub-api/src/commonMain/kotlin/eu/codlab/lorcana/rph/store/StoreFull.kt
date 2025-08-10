package eu.codlab.lorcana.rph.store

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreFull(
    val id: Long,
    val name: String,
    @SerialName("full_address")
    val fullAddress: String? = null,
    @SerialName("administrative_area_level_1_short")
    val administrativeAreaLevel1Short: String? = null,
    /**
     * Store's country. Unavailable in the list of all events
     */
    val country: String? = null,
    /**
     * Store's website. Unavailable in the list of all events
     */
    val website: String? = null,
    /**
     * Store's latitude. Unavailable in the list of all events
     */
    val latitude: Double? = null,
    /**
     * Store's longitude. Unavailable in the list of all events
     */
    val longitude: Double? = null,
    /**
     * Duplicated information from above two. Only available in the full list of stores
     */
    val coordinates: LatLng? = null,
    /**
     * Only available in the full list of stores
     */
    val email: String? = null,
    /**
     * Only available in the full list of stores
     */
    @SerialName("street_address")
    val streetAddress: String? = null,
    /**
     * Only available in the full list of stores
     */
    val zipcode: String? = null,
    /**
     * Only available in the full list of stores
     */
    @SerialName("phone_number")
    val phoneNumber: String? = null,
    /**
     * Only available in the full list of stores
     */
    @SerialName("store_types")
    val storeTypes: List<String>? = null,
    /**
     * Only available in the full list of stores
     */
    @SerialName("store_types_pretty")
    val storeTypesPretty: List<String>? = null,

    @SerialName("seat_count")
    val seatCount: Int? = null,

    @SerialName("discord_url")
    val discordUrl: String? = null,

    @SerialName("facebook_url")
    val facebookUrl: String? = null,

    @SerialName("twitter_handle")
    val twitterHandle: String? = null,

    @SerialName("instagram_handle")
    val instagramHandle: String? = null,

    @SerialName("bluesky_handle")
    val blueskyHandle: String? = null,

    val bio: String? = null,

    @SerialName("is_premium")
    val isPremium: Boolean? = null,

    @SerialName("total_player_interactions")
    val totalPlayerInteractions: Int? = null,

    val address: Address? = null,
)

@Serializable
data class LatLng(
    @SerialName("lat")
    val latitude: Double? = null,
    @SerialName("lng")
    val longitude: Double? = null
)

@Serializable
data class Address(
    val id: String,

    @SerialName("address_type")
    val addressType: String,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String,

    @SerialName("legacy_name")
    val legacyName: String? = null,

    @SerialName("legacy_street_address")
    val legacyStreetAddress: String? = null,

    @SerialName("legacy_full_address")
    val legacyFullAddress: String? = null,

    @SerialName("legacy_administrative_area_level_1_short")
    val legacyAdministrativeAreaLevel1Short: String? = null,

    @SerialName("legacy_country")
    val legacyCountry: String? = null,

    @SerialName("legacy_zipcode")
    val legacyZipcode: String? = null,

    @SerialName("legacy_latitude")
    val legacyLatitude: Double? = null,

    @SerialName("legacy_longitude")
    val legacyLongitude: Double? = null,

    @SerialName("legacy_coordinates")
    val legacyCoordinates: Coordinates? = null,

    @SerialName("free_form_country_code")
    val freeFormCountryCode: String? = null,

    @SerialName("free_form_name")
    val freeFormName: String? = null,

    @SerialName("free_form_company_name")
    val freeFormCompanyName: String? = null,

    @SerialName("free_form_street_address")
    val freeFormStreetAddress: String? = null,

    @SerialName("free_form_city_area")
    val freeFormCityArea: String? = null,

    @SerialName("free_form_city")
    val freeFormCity: String? = null,

    @SerialName("free_form_country_area")
    val freeFormCountryArea: String? = null,

    @SerialName("free_form_postal_code")
    val freeFormPostalCode: String? = null,

    @SerialName("free_form_sorting_code")
    val freeFormSortingCode: String? = null,

    @SerialName("free_form_coordinates")
    val freeFormCoordinates: String? = null,

    @SerialName("google_places_id")
    val googlePlacesId: String? = null,

    @SerialName("google_places_name")
    val googlePlacesName: String? = null,

    @SerialName("google_places_formatted_address")
    val googlePlacesFormattedAddress: String? = null,

    @SerialName("google_places_address_components")
    val googlePlacesAddressComponents: String? = null,

    @SerialName("google_places_location")
    val googlePlacesLocation: String? = null,

    @SerialName("formatted_address")
    val formattedAddress: String? = null,

    val coordinates: Coordinates? = null,
)

@Serializable
data class Coordinates(
    val type: String,
    val coordinates: List<Double>,
)