package com.example.data.seed

import com.example.data.model.LucknowPlace
import com.example.data.model.PlaceCategory
import java.net.URLEncoder

object LucknowCatalogSeed {

    val HERO_BARA_IMAMBARA_URL: String = wikimediaPhoto("Bara_Imambara_Lucknow.jpg", 1200)
    val HERO_RUMI_DARWAZA_URL: String = wikimediaPhoto("Rumi_Darwaza_-_DSC2797-01.jpg", 1200)

    val allPlaces: List<LucknowPlace> by lazy {
        lucknowSeedPart1() + lucknowSeedPart2() + lucknowSeedPart3()
    }

    internal fun wikimediaPhoto(filename: String, width: Int = 900): String {
        val normalized = filename.trim().replace(" ", "_")
        val encoded = try {
            URLEncoder.encode(normalized, "UTF-8")
                .replace("+", "%20")
                .replace("%2C", ",")
                .replace("%28", "(")
                .replace("%29", ")")
        } catch (_: Exception) {
            normalized
        }
        return "https://commons.wikimedia.org/wiki/Special:FilePath/$encoded?width=$width"
    }

    internal fun place(
        id: String,
        name: String,
        category: PlaceCategory,
        locality: String,
        address: String,
        timings: String,
        closedOn: String = "Open all days",
        ticketPrice: String,
        bestTimeToVisit: String,
        description: String,
        highlights: List<String>,
        photoUrls: List<String>,
        reviewSummary: String,
        rating: Double,
        reviewCount: Long,
        lat: Double,
        lng: Double,
        isFeatured: Boolean = false,
        videoUrl: String? = null,
        videoTitle: String? = null
    ): LucknowPlace {
        val encodedQuery = try {
            URLEncoder.encode("$name Lucknow tour review", "UTF-8")
        } catch (_: Exception) {
            name.replace(" ", "+") + "+Lucknow+tour+review"
        }
        return LucknowPlace(
            id = id,
            name = name,
            category = category.label,
            locality = locality,
            address = address,
            timings = timings,
            closedOn = closedOn,
            ticketPrice = ticketPrice,
            bestTimeToVisit = bestTimeToVisit,
            description = description,
            highlights = highlights,
            photoUrls = photoUrls,
            reviewVideoUrl = videoUrl ?: "https://www.youtube.com/results?search_query=$encodedQuery",
            reviewVideoTitle = videoTitle ?: "$name — Lucknow Visitor Walkthrough & Review",
            reviewSummary = reviewSummary,
            rating = rating,
            reviewCount = reviewCount,
            latitude = lat,
            longitude = lng,
            isPublished = true,
            isFeatured = isFeatured,
            sourceLabel = "Curated Guide"
        )
    }
}
