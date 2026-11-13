package com.example.data.seed

import com.example.data.model.LucknowPlace
import com.example.data.model.PlaceCategory

internal fun lucknowSeedPart3(): List<LucknowPlace> = listOf(
    // --- FOOD & CULINARY LANDMARKS (18) ---
    LucknowCatalogSeed.place(
        id = "tunday_kababi_chowk",
        name = "Tunday Kababi (Original Chowk)",
        category = PlaceCategory.FOOD,
        locality = "Chowk, Old Lucknow",
        address = "168/6, Old Nazirabad Rd / Akbari Gate, Chowk, Lucknow 226003",
        timings = "12:00 PM – 11:30 PM",
        ticketPrice = "₹120 – ₹350 per person",
        bestTimeToVisit = "Evening 6:00 PM – 9:30 PM",
        description = "Legendary 1905 establishment famous for melt-in-the-mouth Galouti Kebabs seasoned with 160 secret spices and served with flaky Ulte Tawe ka Paratha.",
        highlights = listOf("1905 Galouti Kebab", "Ulte Tawe Ka Paratha", "160-Spice Recipe"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Galouti_Kabab_from_Tunday_Kababi_Lucknow_2009-01-06.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Tunday_Kababi,_Lucknow.jpg")
        ),
        reviewSummary = "The undisputed pilgrimage for Lucknow foodies. Note: Original Chowk serves buffalo galouti; Aminabad branch also serves mutton & chicken.",
        rating = 4.6,
        reviewCount = 46800,
        lat = 26.8639,
        lng = 80.9082,
        isFeatured = true
    ),
    LucknowCatalogSeed.place(
        id = "tunday_kababi_aminabad",
        name = "Tunday Kababi (Aminabad)",
        category = PlaceCategory.FOOD,
        locality = "Aminabad",
        address = "168/6, Khayali Ganj, Aminabad, Lucknow 226018",
        timings = "12:30 PM – 11:30 PM",
        ticketPrice = "₹250 – ₹500 for two",
        bestTimeToVisit = "Lunch or Dinner",
        description = "Spacious Aminabad branch offering signature Mutton & Chicken Galouti Kebabs, Awadhi Biryani, and Roasted Chicken alongside family seating.",
        highlights = listOf("Mutton Galouti Kebab", "Awadhi Mutton Biryani", "Family AC Seating"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Tunday_Galwati_Mutton_Kebab-MB31.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Tunday_Kebabs.jpg")
        ),
        reviewSummary = "Best choice for travelers wanting mutton galouti kebabs and comfortable seating right in Aminabad market.",
        rating = 4.5,
        reviewCount = 54200,
        lat = 26.8475,
        lng = 80.9248,
        isFeatured = true
    ),
    LucknowCatalogSeed.place(
        id = "idris_hotel_biryani",
        name = "Idris Hotel (Awadhi Dum Biryani)",
        category = PlaceCategory.FOOD,
        locality = "Patkaapur, Chowk",
        address = "Opposite Pata Nala Police Chowki, Chowk, Lucknow 226003",
        timings = "12:00 PM – 3:30 PM & 6:30 PM – 10:30 PM",
        ticketPrice = "₹200 – ₹350 per plate",
        bestTimeToVisit = "12:30 PM or 7:00 PM (Before handi sells out)",
        description = "Since 1968, Idris cooks Lucknow's purest mild-spiced, saffron-and-kewra-infused Awadhi mutton biryani slow-steamed in copper degs.",
        highlights = listOf("Copper-Deg Dum Biryani", "Mild Kewra & Saffron Aroma", "Melt-in-Mouth Mutton"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Lucknowi's_Famous_Dum_Biryani.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Awadhi_mutton_biryani.jpg")
        ),
        reviewSummary = "True Lucknowi biryani—subtle, fragrant, and never overly spicy. Arrive early as fresh degs finish fast!",
        rating = 4.5,
        reviewCount = 19400,
        lat = 26.8632,
        lng = 80.9117
    ),
    LucknowCatalogSeed.place(
        id = "rahim_kulcha_nahari",
        name = "Rahim's Kulcha Nahari",
        category = PlaceCategory.FOOD,
        locality = "Akbari Gate, Chowk",
        address = "Phool Wali Gali, Akbari Gate, Chowk, Lucknow 226003",
        timings = "7:30 AM – 11:30 PM",
        ticketPrice = "₹250 – ₹450 for two",
        bestTimeToVisit = "Morning 8:30 AM – 10:30 AM (Traditional Breakfast)",
        description = "Operating since 1890, famous for slow-simmered overnight bone-marrow Nahari paired with crisp, flaky tandoori kulchas.",
        highlights = listOf("1890 Heritage Nahari", "Flaky Tandoori Kulcha", "Paya Curry"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Tunday_Kebabs.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Tunday_Galwati_Mutton_Kebab-MB31.jpg")
        ),
        reviewSummary = "An authentic Old Lucknow breakfast ritual! Rich, velvety gravy and crisp freshly baked kulchas.",
        rating = 4.4,
        reviewCount = 14800,
        lat = 26.8635,
        lng = 80.9078
    ),
    LucknowCatalogSeed.place(
        id = "dastarkhwan_lalbagh",
        name = "Dastarkhwan (Lalbagh)",
        category = PlaceCategory.FOOD,
        locality = "Lalbagh / Hazratganj",
        address = "29, BN Road, Lalbagh, Lucknow 226001",
        timings = "1:00 PM – 11:00 PM",
        ticketPrice = "₹600 – ₹900 for two",
        bestTimeToVisit = "Dinner 7:30 PM – 9:30 PM",
        description = "Beloved Lucknow institution famed for Chicken Masala, Mutton Boti Kebab, Mughlai Paratha, and Awadhi Korma.",
        highlights = listOf("Signature Chicken Masala", "Mutton Galouti", "Mughlai Paratha"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Tunday_Kababi,_Lucknow.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Awadhi_mutton_biryani.jpg")
        ),
        reviewSummary = "Expect a 15-minute wait at peak dinner—their Chicken Masala with Mughlai Paratha is iconic.",
        rating = 4.5,
        reviewCount = 26300,
        lat = 26.8442,
        lng = 80.9389
    ),
    LucknowCatalogSeed.place(
        id = "wahid_biryani_aminabad",
        name = "Wahid Biryani (Aminabad)",
        category = PlaceCategory.FOOD,
        locality = "Aminabad",
        address = "Nazirabad Rd, Near Tunday Kababi, Aminabad, Lucknow 226018",
        timings = "11:30 AM – 11:30 PM",
        ticketPrice = "₹250 – ₹500 for two",
        bestTimeToVisit = "Lunch or Dinner",
        description = "Established in 1955, renowned for 55-spice Awadhi Chicken & Mutton Biryani and tender Kakori kebabs.",
        highlights = listOf("1955 Heritage Recipe", "Mutton & Chicken Biryani", "Kakori Kebab"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Awadhi_mutton_biryani.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Awadhi_Vegetable_Biryani.jpg")
        ),
        reviewSummary = "Fragrant long-grain basmati and consistent quality right in the heart of Aminabad.",
        rating = 4.3,
        reviewCount = 17900,
        lat = 26.8473,
        lng = 80.9255
    ),
    LucknowCatalogSeed.place(
        id = "royal_cafe_basket_chaat",
        name = "Royal Cafe (Basket Chaat)",
        category = PlaceCategory.FOOD,
        locality = "Hazratganj",
        address = "51, Mahatma Gandhi Marg, Saproo Marg Crossing, Hazratganj, Lucknow 226001",
        timings = "11:00 AM – 11:00 PM",
        ticketPrice = "₹300 – ₹600 for two",
        bestTimeToVisit = "Evening 5:00 PM – 8:00 PM",
        description = "Home of Lucknow's famous crispy potato-basket Tokri Chaat bursting with dahi, tamarind chutney, pomegranate, and spiced bhalla.",
        highlights = listOf("Signature Basket Chaat", "Aloo Tikki", "Kesar Jalebi", "Vegetarian Menu"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Basket_Chat.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Assortment_of_Chaat.jpg")
        ),
        reviewSummary = "Must-visit for vegetarians and chaat lovers—the Tokri Chaat is a crunchy, sweet-tangy masterpiece.",
        rating = 4.4,
        reviewCount = 34500,
        lat = 26.8513,
        lng = 80.9472,
        isFeatured = true
    ),
    LucknowCatalogSeed.place(
        id = "prakash_ki_kulfi",
        name = "Prakash Ki Mashhoor Kulfi",
        category = PlaceCategory.FOOD,
        locality = "Aminabad",
        address = "12, Fruit Market Lane, Aminabad, Lucknow 226018",
        timings = "11:00 AM – 11:00 PM",
        ticketPrice = "₹70 – ₹120 per bowl",
        bestTimeToVisit = "After Dinner / Evening",
        description = "Since 1956, serving rich saffron-and-cardamom malai kulfi topped with chilled vermicelli falooda and sugar syrup.",
        highlights = listOf("1956 Falooda Kulfi", "Saffron Rabri Base", "Sugar-Free Option"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Kulfi_malai_ice-cream.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Malai_kulfi_rabri_falooda.PNG")
        ),
        reviewSummary = "Perfect sweet finale after Aminabad shopping and kebabs—creamy, fragrant, and generous.",
        rating = 4.5,
        reviewCount = 21200,
        lat = 26.8469,
        lng = 80.9278,
        isFeatured = true
    ),
    LucknowCatalogSeed.place(
        id = "sharma_ji_ki_chai",
        name = "Sharma Ji Ki Chai",
        category = PlaceCategory.FOOD,
        locality = "Lalbagh",
        address = "34, Trilok Nath Marg, Sushanpura, Lalbagh, Lucknow 226001",
        timings = "6:30 AM – 8:30 PM",
        ticketPrice = "₹50 – ₹100 per person",
        bestTimeToVisit = "Morning 7:30 AM – 10:30 AM",
        description = "Iconic 1949 corner tea stall famous for milky elaichi chai, fresh bun-makkhan loaded with white butter, and round samosas.",
        highlights = listOf("1949 Heritage Chai", "Bun-Makkhan", "Gol Samosa"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Kulhad_Chai.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Kulhad_lassi.jpg")
        ),
        reviewSummary = "Nothing beats a crisp morning bun-makkhan and hot kulhad chai at Sharma's!",
        rating = 4.4,
        reviewCount = 29400,
        lat = 26.8467,
        lng = 80.9399
    ),
    LucknowCatalogSeed.place(
        id = "shukla_chaat_house",
        name = "Shukla Chaat House",
        category = PlaceCategory.FOOD,
        locality = "Hazratganj",
        address = "Shahnajaf Rd, Opposite St. Francis College, Hazratganj, Lucknow 226001",
        timings = "3:30 PM – 10:00 PM",
        ticketPrice = "₹80 – ₹150 per person",
        bestTimeToVisit = "5:30 PM – 8:30 PM",
        description = "Decades-old roadside chaat legend serving piping hot Matar Tikki, Palak Patta Chaat, and Pani Batashe.",
        highlights = listOf("Matar Aloo Tikki", "Palak Patta Chaat", "5-Flavor Pani Puri"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Assortment_of_Chaat.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Basket_Chat.jpg")
        ),
        reviewSummary = "Pure Lucknow street chaat perfection—fast service, crispy tikkis, and zesty chutneys.",
        rating = 4.5,
        reviewCount = 11200,
        lat = 26.8517,
        lng = 80.9455
    ),
    LucknowCatalogSeed.place(
        id = "moti_mahal_hazratganj",
        name = "Moti Mahal Restaurant (Hazratganj)",
        category = PlaceCategory.FOOD,
        locality = "Hazratganj",
        address = "75, Mahatma Gandhi Marg, Hazratganj, Lucknow 226001",
        timings = "11:00 AM – 11:00 PM",
        ticketPrice = "₹600 – ₹1,000 for two",
        bestTimeToVisit = "Lunch or Evening Snacks",
        description = "Historic Hazratganj institution since 1962 serving classic North Indian thalis, dosas, chaat, and heirloom sweets.",
        highlights = listOf("1962 Vintage Dining", "Heritage Hazratganj Vibe", "Sweets Counter"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("SHAHI_TUKDA.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Hazratganj_Market,_Lucknow_(8716416563).jpg")
        ),
        reviewSummary = "Generations of Lucknow families swear by its nostalgic ambiance and consistent vegetarian menu.",
        rating = 4.3,
        reviewCount = 15400,
        lat = 26.8499,
        lng = 80.9445
    ),
    LucknowCatalogSeed.place(
        id = "ram_asrey_sweets",
        name = "Ram Asrey Sweets (1805)",
        category = PlaceCategory.FOOD,
        locality = "Hazratganj / Chowk",
        address = "43/48, Nawal Kishore Rd, Hazratganj, Lucknow 226001",
        timings = "8:30 AM – 10:00 PM",
        ticketPrice = "₹100 – ₹400",
        bestTimeToVisit = "Winter Mornings (for Malai Gilori & Makhan Malai)",
        description = "Over 220 years old! Invented the delicate betel-leaf-shaped Malai Gilori stuffed with pistachio and rose-petal misri.",
        highlights = listOf("Malai Gilori", "220-Year Heritage", "Winter Makhan Malai"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Shahi_Tukda_-_Dessert_of_India.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("SHAHI_TUKDA.jpg")
        ),
        reviewSummary = "A box of Malai Gilori from Ram Asrey is the most authentic edible souvenir from Lucknow.",
        rating = 4.5,
        reviewCount = 8600,
        lat = 26.8495,
        lng = 80.9457
    ),
    LucknowCatalogSeed.place(
        id = "chhappan_bhog_sadar",
        name = "Chhappan Bhog",
        category = PlaceCategory.FOOD,
        locality = "Sadar Bazaar, Cantonment",
        address = "Apna Bazaar, Nehru Rd, Sadar Bazaar, Lucknow 226002",
        timings = "8:00 AM – 10:00 PM",
        ticketPrice = "₹150 – ₹500",
        bestTimeToVisit = "Evening",
        description = "Award-winning gourmet sweet house offering 56+ traditional Indian mithais, dry-fruit barfis, live jalebi, and savory snacks.",
        highlights = listOf("Gourmet Mithai Boxes", "Live Kesar Jalebi", "Clean Chaat Counter"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Samosa_with_Jalebi.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Shahi_Tukda_-_Dessert_of_India.jpg")
        ),
        reviewSummary = "Unmatched hygiene and premium gift-box sweets in Lucknow Cantonment.",
        rating = 4.6,
        reviewCount = 24600,
        lat = 26.8271,
        lng = 80.9403
    ),
    LucknowCatalogSeed.place(
        id = "bajpayee_kachori_bhandar",
        name = "Bajpayee Kachori Bhandar",
        category = PlaceCategory.FOOD,
        locality = "Hazratganj",
        address = "Nawal Kishore Rd, Behind Bank of India, Hazratganj, Lucknow 226001",
        timings = "7:30 AM – 3:00 PM",
        ticketPrice = "₹60 – ₹100 per plate",
        bestTimeToVisit = "Breakfast 8:00 AM – 11:00 AM",
        description = "Iconic morning breakfast counter serving puffed khasta kachoris with spicy aloo sabzi, chana, and green chili pickle.",
        highlights = listOf("Khasta Kachori Sabzi", "Morning Crowd", "Classic Hazratganj Breakfast"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Aloo_Pyaaz_Kachori.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Samosa_with_Jalebi.jpg")
        ),
        reviewSummary = "Always buzzing by 8 AM—fiery, comforting, and unforgettable for breakfast.",
        rating = 4.5,
        reviewCount = 9200,
        lat = 26.8491,
        lng = 80.9452
    ),
    LucknowCatalogSeed.place(
        id = "rattilal_khasta_aminabad",
        name = "Ratti Lal's Khasta Corner",
        category = PlaceCategory.FOOD,
        locality = "Shivaji Marg, Aminabad",
        address = "31, Shivaji Marg, Near Hewett Rd, Lucknow 226018",
        timings = "7:30 AM – 1:30 PM & 4:30 PM – 8:30 PM",
        ticketPrice = "₹50 – ₹100",
        bestTimeToVisit = "Morning 8:30 AM",
        description = "Legendary 1938 spot for crackling Matar Khasta and Chole-Aloo served in eco-friendly donas (leaf bowls).",
        highlights = listOf("1938 Khasta Recipe", "Leaf-Bowl Serving", "Spiced Aloo Curry"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Aloo_Pyaaz_Kachori.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Assortment_of_Chaat.jpg")
        ),
        reviewSummary = "Old-world simplicity at its best—crisp khastas that locals line up for every morning.",
        rating = 4.5,
        reviewCount = 7400,
        lat = 26.8435,
        lng = 80.9301
    ),
    LucknowCatalogSeed.place(
        id = "netram_ajay_kumar",
        name = "Netram Ajay Kumar Sweets",
        category = PlaceCategory.FOOD,
        locality = "Aminabad",
        address = "Sri Ram Rd, Aminabad, Lucknow 226018",
        timings = "7:00 AM – 10:00 PM",
        ticketPrice = "₹100 – ₹250",
        bestTimeToVisit = "Breakfast (Poori-Sabzi) or Evening (Jalebi)",
        description = "Historic 19th-century sweet & snack shop celebrated for crisp desi-ghee poori-sabzi breakfasts, rabri, and hot jalebis.",
        highlights = listOf("Desi Ghee Poori-Sabzi", "Hot Kesar Jalebi", "Traditional Lassi"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Samosa_with_Jalebi.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Kulhad_lassi.jpg")
        ),
        reviewSummary = "Authentic pure-ghee heritage breakfast and desserts in the heart of Aminabad.",
        rating = 4.4,
        reviewCount = 8900,
        lat = 26.8482,
        lng = 80.9279
    ),
    LucknowCatalogSeed.place(
        id = "lalla_biryani_chowk",
        name = "Lalla Biryani",
        category = PlaceCategory.FOOD,
        locality = "Chaupatiya, Chowk",
        address = "Balmukund Bajpai Marg, Chaupatiya, Chowk, Lucknow 226003",
        timings = "12:00 PM – 4:00 PM & 6:30 PM – 10:30 PM",
        ticketPrice = "₹200 – ₹350",
        bestTimeToVisit = "Lunch 1:00 PM",
        description = "Cult-favorite neighborhood biryani stall famous for velvety saffron-scented rice and deeply tender mutton pieces.",
        highlights = listOf("Melt-in-Mouth Mutton Biryani", "Pocket-Friendly", "Local Foodie Secret"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Lallaji_ki_Biryani,_Lucknow.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Lucknowi's_Famous_Dum_Biryani.jpg")
        ),
        reviewSummary = "Many Lucknow insiders quietly rate Lalla's biryani right beside Idris—don't miss it!",
        rating = 4.5,
        reviewCount = 13100,
        lat = 26.8593,
        lng = 80.9036
    ),
    LucknowCatalogSeed.place(
        id = "makhan_malai_chowk_winter",
        name = "Chowk Gol Darwaza Makhan Malai Trail",
        category = PlaceCategory.FOOD,
        locality = "Gol Darwaza, Chowk",
        address = "Gol Darwaza Lane, Chowk, Lucknow 226003",
        timings = "6:00 AM – 11:00 AM (Peak Nov–Feb)",
        ticketPrice = "₹60 – ₹100 per kulhad",
        bestTimeToVisit = "Winter Mornings 7:00 AM – 9:30 AM",
        description = "Lucknow's magical winter delicacy—airy saffron-whipped milk foam churned under morning dew, crowned with silver vark and khoya.",
        highlights = listOf("Winter Dew Foam Dessert", "Saffron & Pistachio", "Nimish / Makhan Malai"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Malai_kulfi_rabri_falooda.PNG"),
            LucknowCatalogSeed.wikimediaPhoto("Kulfi_malai_ice-cream.jpg")
        ),
        reviewSummary = "Dissolves like clouds on your tongue! Available every winter morning near Gol Darwaza.",
        rating = 4.8,
        reviewCount = 6500,
        lat = 26.8648,
        lng = 80.9089
    ),

    // --- MARKETS, BAZAARS & SHOPPING (12) ---
    LucknowCatalogSeed.place(
        id = "hazratganj_market",
        name = "Hazratganj Market (Ganjing)",
        category = PlaceCategory.MARKETS,
        locality = "Central Lucknow",
        address = "Mahatma Gandhi Marg, Hazratganj, Lucknow 226001",
        timings = "10:00 AM – 10:00 PM",
        closedOn = "Some traditional shops closed Sundays (Malls/Cafes open)",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "5:30 PM – 9:00 PM",
        description = "Lucknow's graceful Victorian-era shopping boulevard lined with uniform cream-and-terracotta facades, heritage bookstores, Chikankari emporiums, and cafes.",
        highlights = listOf("Ganjing Evening Stroll", "SEWA Chikankari Store", "Heritage Arcades", "Lovers Lane Books"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Hazratganj_Lucknow.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Hazratganj_Market,_Lucknow_(8716416563).jpg")
        ),
        reviewSummary = "The soul of modern-heritage Lucknow—go for an evening walk ('Ganjing'), shop Chikankari, and enjoy street chaat.",
        rating = 4.7,
        reviewCount = 68500,
        lat = 26.8498,
        lng = 80.9452,
        isFeatured = true
    ),
    LucknowCatalogSeed.place(
        id = "aminabad_bazaar",
        name = "Aminabad Heritage Bazaar",
        category = PlaceCategory.MARKETS,
        locality = "Aminabad",
        address = "Aminabad Rd, Near Jhandewala Park, Lucknow 226018",
        timings = "11:00 AM – 10:00 PM",
        closedOn = "Thursdays (Main Market)",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "4:00 PM – 8:30 PM (Closed Thu)",
        description = "Bustling 19th-century market labyrinth famed for wholesale Chikankari kurtas, bridal lehengas, attar perfumes, footwear, and iconic eateries.",
        highlights = listOf("Chikankari Kurta Bargains", "Mohan Market", "Tunday & Prakash Kulfi"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Aminabad-market-lucknow.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Aminabad.jpg")
        ),
        reviewSummary = "Unbeatable variety and prices for Lucknowi Chikankari. Note: Main bazaar shuts on Thursdays!",
        rating = 4.6,
        reviewCount = 47200,
        lat = 26.8477,
        lng = 80.9272,
        isFeatured = true
    ),
    LucknowCatalogSeed.place(
        id = "chowk_chikankari_bazaar",
        name = "Chowk Bazaar & Gol Darwaza",
        category = PlaceCategory.MARKETS,
        locality = "Chowk, Old Lucknow",
        address = "Gol Darwaza, Chowk, Lucknow 226003",
        timings = "11:00 AM – 9:30 PM",
        closedOn = "Thursdays (Partial)",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "11:30 AM – 5:00 PM",
        description = "Lucknow's oldest bazaar dating back to the Nawabs, renowned for heirloom hand-embroidered Chikankari, silver vark, and traditional Ittar (perfumes).",
        highlights = listOf("Authentic Hand Chikankari", "Sugandhco Attar", "Gol Darwaza Arch", "Silver Work"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Chikankari_of_lucknow.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Chikan_embroidery,_Lucknow.jpg")
        ),
        reviewSummary = "Go straight to Chowk for museum-grade hand-stitched Chikankari and traditional kewra & rose attars.",
        rating = 4.6,
        reviewCount = 29800,
        lat = 26.8644,
        lng = 80.9086
    ),
    LucknowCatalogSeed.place(
        id = "janpath_market",
        name = "Janpath Market",
        category = PlaceCategory.MARKETS,
        locality = "Hazratganj",
        address = "Off Mahatma Gandhi Marg, Hazratganj, Lucknow 226001",
        timings = "11:00 AM – 9:30 PM",
        closedOn = "Sundays",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Evening 4:30 PM – 8:00 PM",
        description = "Famous covered shopping arcade inside Hazratganj packed with boutique Chikankari showrooms, footwear, leather goods, and fashion jewelry.",
        highlights = listOf("Designer Chikankari", "Footwear & Bags", "Central Location"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Hazratganj_Market,_Lucknow_(8716416563).jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Chikan_embroidery,_Lucknow.jpg")
        ),
        reviewSummary = "Cleaner and easier to browse than Aminabad while still offering authentic Lucknowi embroidery.",
        rating = 4.4,
        reviewCount = 15200,
        lat = 26.8505,
        lng = 80.9462
    ),
    LucknowCatalogSeed.place(
        id = "nakhas_market",
        name = "Nakhas Sunday & Flea Market",
        category = PlaceCategory.MARKETS,
        locality = "Nakhas, Old Lucknow",
        address = "Tulsidas Marg, Nakhas, Lucknow 226003",
        timings = "10:00 AM – 8:30 PM (Sunday Flea from 8:00 AM)",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Sunday Morning 9:00 AM – 12:30 PM",
        description = "200-year-old market famous for its lively Sunday bazaar selling vintage brassware, antique lamps, bird cages, books, and Zardozi trims.",
        highlights = listOf("Sunday Antique Flea Market", "Brass Lamps & Curios", "Vintage Books"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Aminabad.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Chikankari_of_lucknow.jpg")
        ),
        reviewSummary = "Treasure hunter's paradise on Sunday mornings—bargain kindly and explore Old Lucknow's craft lanes.",
        rating = 4.3,
        reviewCount = 9100,
        lat = 26.8559,
        lng = 80.9041
    ),
    LucknowCatalogSeed.place(
        id = "bhootnath_market",
        name = "Bhootnath Market",
        category = PlaceCategory.MARKETS,
        locality = "Indira Nagar",
        address = "Sector 5, Indira Nagar, Lucknow 226016",
        timings = "10:30 AM – 9:30 PM",
        closedOn = "Wednesdays",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Evening",
        description = "Bustling trans-Gomti shopping hub for ethnic wear, silver jewelry, electronics, street momos, and family dining.",
        highlights = listOf("Metro Connected", "Budget Shopping", "Street Food Corners"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Hazratganj_Lucknow.jpg")
        ),
        reviewSummary = "Lively neighborhood bazaar right outside Bhootnath Metro Station.",
        rating = 4.4,
        reviewCount = 18600,
        lat = 26.8781,
        lng = 80.9854
    ),
    LucknowCatalogSeed.place(
        id = "yahiyaganj_market",
        name = "Yahiyaganj Wholesale Bazaar",
        category = PlaceCategory.MARKETS,
        locality = "Yahiyaganj",
        address = "Yahiyaganj Main Rd, Near Rakabganj, Lucknow 226003",
        timings = "10:30 AM – 8:30 PM",
        closedOn = "Thursdays",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Afternoon",
        description = "Lucknow's famous wholesale market for brass utensils, copperware, festive decor, and household crafts.",
        highlights = listOf("Brass & Copperware", "Wholesale Prices", "Festive Shopping"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Aminabad-market-lucknow.jpg")
        ),
        reviewSummary = "Best place in town for traditional brass pooja items and kitchenware.",
        rating = 4.3,
        reviewCount = 7800,
        lat = 26.8531,
        lng = 80.9098
    ),
    LucknowCatalogSeed.place(
        id = "kapoorthala_market",
        name = "Kapoorthala Market",
        category = PlaceCategory.MARKETS,
        locality = "Aliganj",
        address = "Kapoorthala Crossing, Aliganj, Lucknow 226024",
        timings = "10:30 AM – 9:30 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Evening",
        description = "Popular shopping plaza known for stationery, books, footwear, boutique clothing, and popular college-crowd cafes.",
        highlights = listOf("Bookstores & Stationery", "Youth Hangout", "Tailors & Boutiques"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Hazratganj_Market,_Lucknow_(8716416563).jpg")
        ),
        reviewSummary = "Classic Aliganj hub for casual shopping and evening snacks.",
        rating = 4.3,
        reviewCount = 14200,
        lat = 26.8819,
        lng = 80.9462
    ),
    LucknowCatalogSeed.place(
        id = "gadbadjhala_market",
        name = "Gadbadjhala Bangle & Bridal Market",
        category = PlaceCategory.MARKETS,
        locality = "Aminabad",
        address = "Inside Aminabad Market, Lucknow 226018",
        timings = "11:30 AM – 9:00 PM",
        closedOn = "Thursdays",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Afternoon",
        description = "Colorful narrow alley inside Aminabad overflowing with glass bangles, bridal jewelry, gota-patti trims, and cosmetics.",
        highlights = listOf("Bridal Bangles", "Imitation Jewelry", "Gota-Patti Trims"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Aminabad.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Aminabad-market-lucknow.jpg")
        ),
        reviewSummary = "Sensory burst of color and sparkle—unbeatable for wedding accessories.",
        rating = 4.4,
        reviewCount = 6700,
        lat = 26.8471,
        lng = 80.9261
    ),
    LucknowCatalogSeed.place(
        id = "patrakarpuram_market",
        name = "Patrakarpuram Market & Food Street",
        category = PlaceCategory.MARKETS,
        locality = "Gomti Nagar",
        address = "Patrakarpuram Crossing, Vivek Khand, Gomti Nagar, Lucknow 226010",
        timings = "10:00 AM – 11:00 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Evening 6:30 PM – 10:00 PM",
        description = "Gomti Nagar's energetic shopping & street-food square packed with fashion boutiques, tea lounges, kebab corners, and ice-cream parlors.",
        highlights = listOf("Late-Evening Food Scene", "Boutiques & Cafes", "Youth Hub"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Awesome_lucknow.JPG"),
            LucknowCatalogSeed.wikimediaPhoto("Gomti_Riverfront,_Lucknow,_India.jpg")
        ),
        reviewSummary = "Bustling evening vibe with endless snacking choices.",
        rating = 4.4,
        reviewCount = 21900,
        lat = 26.8544,
        lng = 80.9964
    ),
    LucknowCatalogSeed.place(
        id = "phoenix_palassio",
        name = "Phoenix Palassio Mall",
        category = PlaceCategory.MARKETS,
        locality = "Amar Shaheed Path, Gomti Nagar Ext.",
        address = "Sector 7, Amar Shaheed Path, Lucknow 226010",
        timings = "11:00 AM – 10:30 PM",
        ticketPrice = "Free Entry (Parking paid)",
        bestTimeToVisit = "Evening & Weekends",
        description = "Awadhi-palace-inspired luxury mall featuring grand marble chandeliers, 300+ global brands, IMAX cinema, and fine dining.",
        highlights = listOf("Awadhi Palace Interior", "IMAX Multiplex", "300+ Global Brands", "Musical Fountain"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Lulu_Mall_-_Lucknow_20220717_172748.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Awesome_lucknow.JPG")
        ),
        reviewSummary = "Even if you don't shop, the palatial indoor ceiling domes and chandeliers are breathtaking.",
        rating = 4.7,
        reviewCount = 42000,
        lat = 26.8141,
        lng = 81.0079,
        isFeatured = true
    ),
    LucknowCatalogSeed.place(
        id = "lulu_mall_lucknow",
        name = "Lulu Mall Lucknow",
        category = PlaceCategory.MARKETS,
        locality = "Golf City, Shaheed Path",
        address = "Sushant Golf City, Amar Shaheed Path, Lucknow 226030",
        timings = "10:00 AM – 11:00 PM",
        ticketPrice = "Free Entry (Parking paid)",
        bestTimeToVisit = "Afternoon or Evening",
        description = "One of India's largest shopping malls spanning 2.2 million sq ft with Funtura indoor theme park, 11-screen PVR, and mega food court.",
        highlights = listOf("Funtura Indoor Rides", "11-Screen PVR Superplex", "1,600-Seat Food Court"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Lulu_Mall_-_Lucknow_20220717_172748.jpg")
        ),
        reviewSummary = "Massive modern shopping and entertainment complex ideal for families and rainy/hot afternoons.",
        rating = 4.6,
        reviewCount = 58400,
        lat = 26.7879,
        lng = 80.9854
    ),

    // --- DAY TRIPS & LEISURE (10) ---
    LucknowCatalogSeed.place(
        id = "nawabganj_bird_sanctuary",
        name = "Nawabganj Bird Sanctuary",
        category = PlaceCategory.DAY_TRIPS,
        locality = "Lucknow–Kanpur Highway (43 km)",
        address = "NH 27, Nawabganj, Unnao District, Uttar Pradesh 209859",
        timings = "6:00 AM – 5:30 PM",
        ticketPrice = "₹30 Indians | ₹350 Foreigners",
        bestTimeToVisit = "Nov–Feb, 6:30 AM – 10:30 AM",
        description = "Ramsar wetland sanctuary hosting 250+ resident and migratory bird species including Sarus Cranes, Pintails, Purple Moorhens, plus a deer park.",
        highlights = listOf("250+ Bird Species", "Watchtowers", "Deer Enclosure", "Wetland Lake"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Nawabganj_Bird_Sanctuary,_Unnao_02.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Nawabganj_Bird_Sanctuary,_Unnao_04.JPG")
        ),
        reviewSummary = "Carry binoculars in winter—the morning wetland chorus of migratory birds is unforgettable.",
        rating = 4.4,
        reviewCount = 5400,
        lat = 26.6203,
        lng = 80.6542,
        isFeatured = true
    ),
    LucknowCatalogSeed.place(
        id = "kakori_shaheed_smarak",
        name = "Kakori Shaheed Smarak",
        category = PlaceCategory.DAY_TRIPS,
        locality = "Kakori (16 km)",
        address = "Bajid Nagar, Kakori, Lucknow 226101",
        timings = "8:00 AM – 6:00 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Morning",
        description = "Sacred freedom-fighter memorial commemorating the 1925 Kakori Train Action led by Ram Prasad Bismil, Ashfaqulla Khan, and Chandrashekhar Azad.",
        highlights = listOf("1925 Freedom Memorial", "Bismil & Ashfaqulla Tribute", "Mango Belt Drive"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Kakori_Shaheed.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Hauda_Talab_Kakori,_Lucknow.jpg")
        ),
        reviewSummary = "Moving historical site surrounded by Kakori's famous fragrant mango orchards.",
        rating = 4.6,
        reviewCount = 3900,
        lat = 26.8768,
        lng = 80.7939
    ),
    LucknowCatalogSeed.place(
        id = "dewa_sharif_shrine",
        name = "Dewa Sharif (Haji Waris Ali Shah Dargah)",
        category = PlaceCategory.DAY_TRIPS,
        locality = "Dewa, Barabanki Rd (26 km)",
        address = "Dewa Sharif, Barabanki, Uttar Pradesh 225301",
        timings = "5:00 AM – 10:00 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Thursday Evenings or Oct–Nov Urs Fair",
        description = "Revered white-marble Sufi shrine of Haji Waris Ali Shah, celebrated across faiths for qawwali nights and universal brotherhood.",
        highlights = listOf("White Marble Dargah", "Soulful Qawwali", "Dewa Mela"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Asafi_Masjid,_Bara_Imambara,_Lucknow.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Jama_Masjid_near_Hussainabad,_Chowk_Lucknow.jpg")
        ),
        reviewSummary = "Deeply uplifting Sufi ambiance just a 45-minute drive northeast of Lucknow.",
        rating = 4.8,
        reviewCount = 24000,
        lat = 27.0378,
        lng = 81.1672
    ),
    LucknowCatalogSeed.place(
        id = "malihabad_mango_orchards",
        name = "Malihabad Dasheri Mango Orchards",
        category = PlaceCategory.DAY_TRIPS,
        locality = "Malihabad (25 km)",
        address = "Hardoi Rd, Malihabad, Lucknow 226102",
        timings = "7:00 AM – 6:00 PM",
        ticketPrice = "Free / Farm Tour ₹200",
        bestTimeToVisit = "May–July (Mango Harvest Season)",
        description = "India's legendary mango capital home to GI-tagged Dasheri mangoes and Padma Shri Kalimullah Khan's 300-variety single mango tree.",
        highlights = listOf("Dasheri Mango Farms", "300-Variety Tree", "Orchard Tasting"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Beta_bridge_and_temple_view_from_the_river_bank.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Hauda_Talab_Kakori,_Lucknow.jpg")
        ),
        reviewSummary = "Visit in June for sweet farm-fresh Dasheri mangoes straight off centuries-old trees.",
        rating = 4.6,
        reviewCount = 2900,
        lat = 26.9188,
        lng = 80.7131
    ),
    LucknowCatalogSeed.place(
        id = "naimisharanya_excursion",
        name = "Naimisharanya (Neemsar) Sacred Complex",
        category = PlaceCategory.DAY_TRIPS,
        locality = "Sitapur Rd (90 km)",
        address = "Naimisharanya, Sitapur District, Uttar Pradesh 261402",
        timings = "5:00 AM – 9:00 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Full-Day Excursion (Leave 6:30 AM)",
        description = "Ancient Puranic pilgrimage along the Gomti featuring Chakra Tirth, Lalita Devi Shakti Peeth, and Vyas Gaddi.",
        highlights = listOf("Chakra Tirth Kund", "Lalita Devi Temple", "Vyas Gaddi", "Gomti Ghat"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Chandrika_devi_temple_lucknow.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("MANKAMESHVER_TEMPLE.jpg")
        ),
        reviewSummary = "Most popular spiritual day trip from Lucknow—peaceful, ancient, and easily reachable in 2 hours.",
        rating = 4.7,
        reviewCount = 31500,
        lat = 27.3502,
        lng = 80.4836
    ),
    LucknowCatalogSeed.place(
        id = "anandi_water_park",
        name = "Anandi Water Park & Resort",
        category = PlaceCategory.DAY_TRIPS,
        locality = "Faizabad Road (16 km)",
        address = "Canal Road, Near Indira Canal, Faizabad Rd, Lucknow 226010",
        timings = "10:00 AM – 6:00 PM",
        ticketPrice = "₹800 – ₹1,000 Adults | ₹600 Children",
        bestTimeToVisit = "Mar–Oct",
        description = "North India's popular family water park with 32+ slides, wave pool, lazy river, rain dance, and cyclone funnel rides.",
        highlights = listOf("Wave Pool", "32 Water Slides", "Lazy River", "Kids Splash Zone"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Janeshwar_Mishra_Park.jpg")
        ),
        reviewSummary = "Top summer beat-the-heat destination in Lucknow for families and groups.",
        rating = 4.2,
        reviewCount = 18400,
        lat = 26.8895,
        lng = 81.0847
    ),
    LucknowCatalogSeed.place(
        id = "dream_world_amusement_park",
        name = "Dream World Amusement Park",
        category = PlaceCategory.DAY_TRIPS,
        locality = "Kanpur Road (12 km)",
        address = "Sector H, Kanpur Rd, Near Ashiana, Lucknow 226012",
        timings = "10:30 AM – 7:00 PM",
        ticketPrice = "₹700 – ₹900 Combo Pass",
        bestTimeToVisit = "Weekend Afternoons",
        description = "Lucknow's indoor-outdoor amusement and water park boasting wave pools, indoor snow/aqua rides, and family roller coasters.",
        highlights = listOf("Indoor Wave Pool", "Amusement Rides", "Kids Play Zone"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Lohia-park-joggers-track.jpg")
        ),
        reviewSummary = "Convenient family fun park right on Kanpur Road.",
        rating = 4.1,
        reviewCount = 11200,
        lat = 26.7848,
        lng = 80.8926
    ),
    LucknowCatalogSeed.place(
        id = "scorpio_club_leisure",
        name = "Four Seasons / Kursi Road Eco Farms",
        category = PlaceCategory.DAY_TRIPS,
        locality = "Kursi Road (14 km)",
        address = "Kursi Road, Gudamba, Lucknow 226026",
        timings = "9:00 AM – 8:00 PM",
        ticketPrice = "₹300 – ₹600 Day Pass",
        bestTimeToVisit = "Winter Weekends",
        description = "Green suburban leisure belt along Kursi Road known for horse-riding clubs, weekend picnic lawns, and botanical nurseries.",
        highlights = listOf("Open Picnic Lawns", "Plant Nurseries", "Weekend Brunch"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Lohia-park-joggers-track.jpg")
        ),
        reviewSummary = "Relaxed weekend outing away from city bustle.",
        rating = 4.2,
        reviewCount = 2100,
        lat = 26.9422,
        lng = 80.9685
    ),
    LucknowCatalogSeed.place(
        id = "bithoor_day_trip",
        name = "Bithoor Ganga Ghats (Day Excursion)",
        category = PlaceCategory.DAY_TRIPS,
        locality = "Via Kanpur Rd (95 km)",
        address = "Bithoor, Kanpur Nagar, Uttar Pradesh 209201",
        timings = "5:30 AM – 8:00 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Early Morning or Evening Ganga Aarti",
        description = "Serene riverside town associated with Valmiki Ashram, Nana Saheb Peshwa Fort, and tranquil Ganga ghats.",
        highlights = listOf("Valmiki Ashram", "Patthar Ghat", "Evening Ganga Aarti"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Beta_bridge_and_temple_view_from_the_river_bank.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Gomti_Riverfront,_Lucknow,_India.jpg")
        ),
        reviewSummary = "Rewarding heritage and riverfront half-day drive from Lucknow.",
        rating = 4.6,
        reviewCount = 14800,
        lat = 26.6126,
        lng = 80.2719
    ),
    LucknowCatalogSeed.place(
        id = "ekana_cricket_stadium",
        name = "Bharat Ratna Shri Atal Bihari Vajpayee Ekana Cricket Stadium",
        category = PlaceCategory.DAY_TRIPS,
        locality = "Gomti Nagar Extension",
        address = "Sector 7, Amar Shaheed Path, Gomti Nagar Ext, Lucknow 226010",
        timings = "Match Days & Scheduled Tours (9:00 AM – 6:00 PM)",
        ticketPrice = "Free Exterior | Match Tickets Vary",
        bestTimeToVisit = "IPL / International Match Evenings",
        description = "World-class 50,000-seat international cricket stadium hosting IPL (Lucknow Super Giants) and ICC fixtures.",
        highlights = listOf("50,000-Seat Arena", "Home of LSG", "Modern Floodlit Bowl"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Ekana_Cricket_Stadium,_Lucknow.jpg")
        ),
        reviewSummary = "Electric match-day atmosphere and sleek modern stadium architecture.",
        rating = 4.7,
        reviewCount = 39500,
        lat = 26.8114,
        lng = 81.0161,
        isFeatured = true
    )
)
