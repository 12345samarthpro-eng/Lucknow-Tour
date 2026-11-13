package com.example.data.seed

import com.example.data.model.LucknowPlace
import com.example.data.model.PlaceCategory

internal fun lucknowSeedPart2(): List<LucknowPlace> = listOf(
    // --- TEMPLES, MOSQUES & SPIRITUAL (16) ---
    LucknowCatalogSeed.place(
        id = "mankameshwar_temple",
        name = "Mankameshwar Temple",
        category = PlaceCategory.SPIRITUAL,
        locality = "Daliganj (Gomti Bank)",
        address = "Mankameshwar Mandir Marg, Daliganj, Lucknow 226020",
        timings = "5:00 AM – 10:00 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Early Morning 6:00 AM – 8:00 AM & Mondays",
        description = "Ancient Shiva temple standing along the Gomti River where Lord Lakshmana is believed to have offered prayers.",
        highlights = listOf("Ancient Shiva Lingam", "Gomti Aarti", "Monday Devotional Crowd"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("MANKAMESHVER_TEMPLE.jpg")
        ),
        reviewSummary = "One of Lucknow's most revered Shiva shrines; deeply peaceful during early morning rudrabhishek.",
        rating = 4.8,
        reviewCount = 21500,
        lat = 26.8661,
        lng = 80.9317,
        isFeatured = true
    ),
    LucknowCatalogSeed.place(
        id = "chandrika_devi_temple",
        name = "Maa Chandrika Devi Temple",
        category = PlaceCategory.SPIRITUAL,
        locality = "Kathwara, Bakshi Ka Talab",
        address = "Kathwara Village, Sitapur Road, Lucknow 226202",
        timings = "5:00 AM – 9:30 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Morning or Amavasya / Navratri",
        description = "A 300-year-old Sidh Peeth temple perched on the banks of the Gomti River amidst mango groves.",
        highlights = listOf("Gomti Riverbank Shrine", "300-Year Heritage", "Navratri Fair"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Chandrika_devi_temple_lucknow.jpg")
        ),
        reviewSummary = "Scenic 45-minute drive from central Lucknow; soothing riverside breeze and spiritual warmth.",
        rating = 4.8,
        reviewCount = 33000,
        lat = 27.0229,
        lng = 80.8692
    ),
    LucknowCatalogSeed.place(
        id = "hanumant_dham",
        name = "Hanumant Dham",
        category = PlaceCategory.SPIRITUAL,
        locality = "Parivartan Chowk, Gomti Bank",
        address = "Near Moti Mahal Lawn, Mahatma Gandhi Marg, Lucknow 226001",
        timings = "5:30 AM – 10:00 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Evening 6:00 PM – 8:30 PM & Tuesdays",
        description = "An expansive marble temple complex on the Gomti bank housing over 108 statues of Lord Hanuman amid illuminated fountains.",
        highlights = listOf("108 Hanuman Idols", "Marble Courtyard", "Evening Fountains"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Parivartan_Chowk_Lucknow.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("MANKAMESHVER_TEMPLE.jpg")
        ),
        reviewSummary = "Exceptionally clean, brightly illuminated temple right on the riverfront; Tuesday evenings feel festive.",
        rating = 4.8,
        reviewCount = 27400,
        lat = 26.8576,
        lng = 80.9416,
        isFeatured = true
    ),
    LucknowCatalogSeed.place(
        id = "aliganj_new_hanuman_temple",
        name = "Naya Hanuman Mandir (Aliganj)",
        category = PlaceCategory.SPIRITUAL,
        locality = "Aliganj",
        address = "Sector B, Kapoorthala Rd, Aliganj, Lucknow 226024",
        timings = "5:00 AM – 10:00 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Tuesdays & Saturdays (Bada Mangal)",
        description = "Famous 18th-century Hanuman temple adorned with a silver-clad sanctum and known for Lucknow's iconic Bada Mangal celebrations.",
        highlights = listOf("Bada Mangal Tradition", "Nawabi-Era Patronage", "Silver Sanctum"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Bajrangbali_Dwar,_Aliganj_Hanuman_Temple,_Lucknow.jpg")
        ),
        reviewSummary = "A living symbol of Lucknow's Ganga-Jamuni tehzeeb, originally patronized by Begum Janab-e-Alia.",
        rating = 4.8,
        reviewCount = 19200,
        lat = 26.8858,
        lng = 80.9438
    ),
    LucknowCatalogSeed.place(
        id = "aliganj_old_hanuman_temple",
        name = "Purana Hanuman Mandir (Aliganj)",
        category = PlaceCategory.SPIRITUAL,
        locality = "Aliganj",
        address = "Purania Chauraha, Aliganj, Lucknow 226024",
        timings = "5:00 AM – 9:30 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Morning & Tuesdays",
        description = "Ancient Hanuman shrine linked to the era of Nawab Shuja-ud-Daula, featuring a sacred crescent emblem atop its spire.",
        highlights = listOf("Crescent Spire Emblem", "Historic Courtyard", "Bada Mangal Bhandara"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Bajrangbali_Dwar,_Aliganj_Hanuman_Temple,_Lucknow.jpg")
        ),
        reviewSummary = "Quiet spiritual landmark showcasing Lucknow's centuries-old communal harmony.",
        rating = 4.7,
        reviewCount = 11400,
        lat = 26.8902,
        lng = 80.9401
    ),
    LucknowCatalogSeed.place(
        id = "badi_kali_ji_temple",
        name = "Badi Kali Ji Temple",
        category = PlaceCategory.SPIRITUAL,
        locality = "Chowk",
        address = "Kali Bari Marg, Chowk, Lucknow 226003",
        timings = "5:30 AM – 9:30 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Morning or Navratri",
        description = "One of Lucknow's oldest Kali temples tucked inside Chowk's heritage lanes, home to centuries-old Laxmi-Narayan idols.",
        highlights = listOf("Ancient Chowk Shrine", "Navratri Festivities", "Heritage Lane Walk"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("MANKAMESHVER_TEMPLE.jpg")
        ),
        reviewSummary = "Deeply spiritual stop during an early morning heritage walk through Chowk.",
        rating = 4.7,
        reviewCount = 9600,
        lat = 26.8669,
        lng = 80.9096
    ),
    LucknowCatalogSeed.place(
        id = "budheshwar_mahadev_temple",
        name = "Budheshwar Mahadev Temple",
        category = PlaceCategory.SPIRITUAL,
        locality = "Mohan Road, Rajajipuram",
        address = "Budheshwar Chauraha, Mohan Rd, Lucknow 226017",
        timings = "5:00 AM – 9:00 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Wednesdays & Shravan Month",
        description = "Treta-Yuga legend shrine where Lord Lakshmana rested and worshipped Lord Shiva on the banks of the ancient Kukrail-Gomti belt.",
        highlights = listOf("Wednesday Devotion", "Sacred Pond", "Shravan Mela"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Beta_river_bridge_and_Lord_Shiv_Temple,_Kakori,_Lucknow.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("MANKAMESHVER_TEMPLE.jpg")
        ),
        reviewSummary = "Vibrant Wednesday fairs and strong local devotion in West Lucknow.",
        rating = 4.7,
        reviewCount = 14800,
        lat = 26.8359,
        lng = 80.8691
    ),
    LucknowCatalogSeed.place(
        id = "teele_wali_masjid",
        name = "Teele Wali Masjid",
        category = PlaceCategory.SPIRITUAL,
        locality = "Lakshman Tila, Chowk",
        address = "Lakshman Tila, Near Bara Imambara, Lucknow 226003",
        timings = "6:00 AM – 8:00 PM (Exterior Courtyard)",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Morning",
        description = "Built on the historic Lakshman Tila mound overlooking the Gomti River, featuring three monumental domes and sweeping views of Old Lucknow.",
        highlights = listOf("Elevated Mound View", "Mughal-Era Courtyard", "Gomti Skyline"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Teele_Wali_Masjid,_Lucknow.jpg")
        ),
        reviewSummary = "Striking hillside silhouette right beside Bara Imambara and Nimbu Park.",
        rating = 4.6,
        reviewCount = 7300,
        lat = 26.8685,
        lng = 80.9163
    ),
    LucknowCatalogSeed.place(
        id = "nadwa_mosque",
        name = "Darul Uloom Nadwatul Ulama",
        category = PlaceCategory.SPIRITUAL,
        locality = "Tagore Marg, Hasanganj",
        address = "Tagore Marg, Babuganj, Lucknow 226007",
        timings = "8:00 AM – 5:00 PM (Campus courtesy at gate)",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Morning",
        description = "World-renowned Islamic seminary founded in 1894 on the Gomti bank, featuring dignified arched halls and an extensive manuscript library.",
        highlights = listOf("1894 Heritage Campus", "Grand Mosque Courtyard", "Gomti Views"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Masjid_Nadwa_Lucknow_4.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Masjid_Nadwa_Lucknow_5.jpg")
        ),
        reviewSummary = "Serene academic campus with graceful Indo-Islamic arches along the river.",
        rating = 4.7,
        reviewCount = 4900,
        lat = 26.8659,
        lng = 80.9355
    ),
    LucknowCatalogSeed.place(
        id = "st_josephs_cathedral",
        name = "St. Joseph's Cathedral",
        category = PlaceCategory.SPIRITUAL,
        locality = "Hazratganj",
        address = "31 Cathedral House, Shahnajaf Rd, Hazratganj, Lucknow 226001",
        timings = "6:30 AM – 7:00 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Evening & Christmas Season",
        description = "Striking modern-traditional Catholic cathedral in the heart of Hazratganj with stained-glass windows, tall spire, and tranquil lawns.",
        highlights = listOf("Stained-Glass Sanctuary", "Christmas Eve Carols", "Hazratganj Heritage"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("The_Cathedral_Church_and_School.jpg")
        ),
        reviewSummary = "Beautifully lit during December festivities; quiet, welcoming refuge inside bustling Hazratganj.",
        rating = 4.7,
        reviewCount = 6200,
        lat = 26.8511,
        lng = 80.9465
    ),
    LucknowCatalogSeed.place(
        id = "christ_church_hazratganj",
        name = "Christ Church Hazratganj",
        category = PlaceCategory.SPIRITUAL,
        locality = "Mahatma Gandhi Marg",
        address = "Opposite DRM Office, Hazratganj, Lucknow 226001",
        timings = "7:00 AM – 6:00 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Sunday Morning or Sunset",
        description = "Built in 1860, Lucknow's oldest Gothic-Revival church features red-brick buttresses, pointed stained arches, and historic plaques.",
        highlights = listOf("1860 Gothic Architecture", "Historic Stained Glass", "Garden Campus"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("The_Cathedral_Church_and_School.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("La_Martiniere_College,_Lucknow.jpg")
        ),
        reviewSummary = "Charming colonial-era church admired by architecture photographers.",
        rating = 4.6,
        reviewCount = 2400,
        lat = 26.8477,
        lng = 80.9412
    ),
    LucknowCatalogSeed.place(
        id = "gurudwara_naka_hindola",
        name = "Gurudwara Naka Hindola",
        category = PlaceCategory.SPIRITUAL,
        locality = "Naka Hindola",
        address = "Arya Nagar, Naka Hindola, Lucknow 226004",
        timings = "4:00 AM – 9:30 PM",
        ticketPrice = "Free Entry (Langar served daily)",
        bestTimeToVisit = "Morning Kirtan or Evening",
        description = "Lucknow's premier Sikh shrine near Charbagh, renowned for soulful Gurbani kirtan, spotless white marble halls, and welcoming community langar.",
        highlights = listOf("Community Langar", "Soulful Kirtan", "Near Charbagh Station"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Lko_charbagh_railway_station_3.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("The_Chota_Imambara_of_Lucknow_Uttar_Pradesh_DSC_00010.jpg")
        ),
        reviewSummary = "Warm hospitality and deeply peaceful atmosphere; remember to cover your head before entering.",
        rating = 4.8,
        reviewCount = 13500,
        lat = 26.8371,
        lng = 80.9228
    ),
    LucknowCatalogSeed.place(
        id = "gurudwara_yahiyaganj",
        name = "Historical Gurudwara Yahiyaganj",
        category = PlaceCategory.SPIRITUAL,
        locality = "Yahiyaganj, Old Lucknow",
        address = "Yahiyaganj Main Bazar, Lucknow 226003",
        timings = "4:30 AM – 9:00 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Morning",
        description = "Sacred historic Gurudwara blessed by the visits of Guru Tegh Bahadur Ji and Guru Gobind Singh Ji, preserving rare centuries-old relics.",
        highlights = listOf("Guru Granth Sahib Relics", "400-Year Sikh Heritage", "Daily Langar"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("The_Chota_Imambara_of_Lucknow_Uttar_Pradesh_DSC_00010.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Lucknow_Imambara.jpg")
        ),
        reviewSummary = "Must-visit spiritual site with priceless Sikh historical artifacts inside Old Lucknow.",
        rating = 4.9,
        reviewCount = 8700,
        lat = 26.8544,
        lng = 80.9091
    ),
    LucknowCatalogSeed.place(
        id = "digambar_jain_temple_chowk",
        name = "Shri Digambar Jain Temple (Chowk)",
        category = PlaceCategory.SPIRITUAL,
        locality = "Jhaoo Lal Pul / Chowk",
        address = "Nadan Mahal Rd, Chowk, Lucknow 226003",
        timings = "6:00 AM – 8:00 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Morning",
        description = "Ornate Jain sanctuary featuring gleaming marble carvings, tranquil meditation halls, and peaceful courtyards in Old Lucknow.",
        highlights = listOf("Intricate Marble Pillars", "Peaceful Sanctum", "Old City Gem"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Lucknow_Museum-Tirthankar.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Lucknow_Museum.jpg")
        ),
        reviewSummary = "Remarkably serene oasis tucked inside busy Chowk.",
        rating = 4.7,
        reviewCount = 2100,
        lat = 26.8612,
        lng = 80.9099
    ),
    LucknowCatalogSeed.place(
        id = "buddha_vihar_shanti_upvan",
        name = "Bauddha Vihar Shanti Upvan",
        category = PlaceCategory.SPIRITUAL,
        locality = "VIP Road, Alambagh",
        address = "Jail Road, Bangla Bazar, Lucknow 226005",
        timings = "9:00 AM – 7:00 PM",
        ticketPrice = "₹10 Entry",
        bestTimeToVisit = "Late afternoon",
        description = "Monumental Buddhist peace complex with four-faced white marble Buddha stupas, tranquil meditation gardens, and colonnades.",
        highlights = listOf("Marble Buddha Statues", "Meditation Gardens", "Grand White Stupa"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Ambedkar_Memorial_Park-Lucknow-Uttar_Pradesh-MA22.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Awesome_lucknow.JPG")
        ),
        reviewSummary = "Calm, photogenic white-marble sanctuary opposite Eco Garden.",
        rating = 4.5,
        reviewCount = 4600,
        lat = 26.7959,
        lng = 80.9168
    ),
    LucknowCatalogSeed.place(
        id = "sheetla_devi_temple",
        name = "Maa Sheetla Devi Temple",
        category = PlaceCategory.SPIRITUAL,
        locality = "Mehdi Ganj, Tikait Rai Talab",
        address = "Tikait Rai Talab, Rajajipuram, Lucknow 226017",
        timings = "5:30 AM – 9:00 PM",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Morning & Aathoun Mela",
        description = "Heritage lakeside temple set beside the 18th-century Tikait Rai Talab tank, famous for spring Sheetla Ashtami fairs.",
        highlights = listOf("Historic Tikait Rai Talab", "Traditional Ghat Steps", "Spring Festival"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("MANKAMESHVER_TEMPLE.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Chandrika_devi_temple_lucknow.jpg")
        ),
        reviewSummary = "Authentic local heritage pond and temple off the usual tourist trail.",
        rating = 4.6,
        reviewCount = 5200,
        lat = 26.8458,
        lng = 80.8962
    ),

    // --- MUSEUMS, SCIENCE & CULTURE (14) ---
    LucknowCatalogSeed.place(
        id = "state_museum_lucknow",
        name = "State Museum Lucknow",
        category = PlaceCategory.MUSEUMS,
        locality = "Banarsi Bagh, Narhi",
        address = "Inside Lucknow Zoo Campus, Narhi, Lucknow 226001",
        timings = "10:30 AM – 4:30 PM",
        closedOn = "Mondays & Govt Holidays",
        ticketPrice = "₹20 Indians | ₹200 Foreigners (Zoo ticket separate)",
        bestTimeToVisit = "11:00 AM – 2:00 PM",
        description = "Uttar Pradesh's oldest museum (1863), housing rare Kushan sculptures, Awadh Nawabi paintings, bronzes, coins, and an authentic Egyptian mummy.",
        highlights = listOf("Egyptian Mummy", "Kushan & Gupta Sculptures", "Nawabi Miniatures", "Awadh Textiles"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("State_Museum_Lucknow.png"),
            LucknowCatalogSeed.wikimediaPhoto("Lucknow_Museum.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Lucknow_Museum-Tirthankar.jpg")
        ),
        reviewSummary = "Rich 4-floor cultural treasure trove—don't miss the ancient Mathura sculptures and the mummy gallery.",
        rating = 4.5,
        reviewCount = 8900,
        lat = 26.8458,
        lng = 80.9554,
        isFeatured = true
    ),
    LucknowCatalogSeed.place(
        id = "regional_science_city",
        name = "Regional Science City",
        category = PlaceCategory.MUSEUMS,
        locality = "Aliganj",
        address = "Sector E, Ekta Vihar, Aliganj, Lucknow 226024",
        timings = "9:30 AM – 6:00 PM",
        closedOn = "Holi & Diwali",
        ticketPrice = "₹40 Entry | ₹50 3D / SciMax Show",
        bestTimeToVisit = "10:30 AM – 4:00 PM",
        description = "Interactive science center spread over 10 acres featuring Underwater Exploration, Prehistoric Dinosaurs Park, 3D Science Theatre, and SciMax dome.",
        highlights = listOf("Dinosaur Park", "3D & SciMax Dome", "Human Evolution Gallery", "Hands-on Physics"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("MSE_Bus_-_Regional_Science_City_-_Lucknow_-_MSE_Golden_Jubilee_Celebration_-_Science_City_-_Kolkata_2015-11-18_5306.JPG"),
            LucknowCatalogSeed.wikimediaPhoto("Indira_Gandhi_Planeratium_Lucknow.jpg")
        ),
        reviewSummary = "Top family & kids educational destination in Lucknow—plan at least 3 hours.",
        rating = 4.5,
        reviewCount = 16200,
        lat = 26.8909,
        lng = 80.9467,
        isFeatured = true
    ),
    LucknowCatalogSeed.place(
        id = "indira_gandhi_planetarium",
        name = "Indira Gandhi Planetarium (Taramandal)",
        category = PlaceCategory.MUSEUMS,
        locality = "Suraj Kund Park, Kaiserbagh",
        address = "Nabiullah Rd, Suraj Kund Park, Lucknow 226018",
        timings = "10:00 AM – 5:00 PM (Shows at 11, 12, 1, 2:30, 3:30, 4:30)",
        closedOn = "Mondays",
        ticketPrice = "₹25 Adults | ₹15 Students",
        bestTimeToVisit = "Afternoon 3:30 PM Show",
        description = "Distinctive Saturn-shaped dome auditorium featuring digital full-dome astronomy shows and space-science exhibits.",
        highlights = listOf("Saturn-Shaped Building", "360° Dome Astronomy Show", "Space Exhibits"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Indira_Gandhi_Planeratium_Lucknow.jpg")
        ),
        reviewSummary = "Kids adore the Saturn-ring architecture and immersive planetarium sky shows.",
        rating = 4.3,
        reviewCount = 9400,
        lat = 26.8632,
        lng = 80.9351
    ),
    LucknowCatalogSeed.place(
        id = "1857_memorial_museum",
        name = "1857 Memorial Museum (Residency)",
        category = PlaceCategory.MUSEUMS,
        locality = "Qaisar Bagh",
        address = "Inside British Residency Complex, Lucknow 226001",
        timings = "10:00 AM – 5:00 PM",
        closedOn = "Fridays",
        ticketPrice = "Included in Residency Ticket (₹25)",
        bestTimeToVisit = "Morning",
        description = "Curated ASI museum displaying scale dioramas of the 1857 siege, vintage muskets, lithographs, and personal letters from Lucknow's uprising.",
        highlights = listOf("1857 Siege Diorama", "Vintage Cannonballs", "Archival Maps"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Office_-_The_Residency_-_Lucknow_-_India.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("The_Residency,_Lucknow.JPG")
        ),
        reviewSummary = "Essential context before walking through the surrounding Residency ruins.",
        rating = 4.6,
        reviewCount = 4100,
        lat = 26.8602,
        lng = 80.9275
    ),
    LucknowCatalogSeed.place(
        id = "motilal_nehru_childrens_museum",
        name = "Motilal Nehru Children's Museum",
        category = PlaceCategory.MUSEUMS,
        locality = "Naka Hindola / Charbagh",
        address = "Subhash Marg, Near Bal Sangrahalaya, Lucknow 226004",
        timings = "10:30 AM – 5:30 PM",
        closedOn = "Mondays",
        ticketPrice = "₹10 Entry",
        bestTimeToVisit = "Morning",
        description = "Established in 1957, showcasing international dolls, Ramayana miniatures, stamps, science models, and historical dioramas for children.",
        highlights = listOf("International Dolls", "Ramayana Models", "Stamp & Coin Room"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Lucknow_Museum.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("State_Museum_Lucknow.png")
        ),
        reviewSummary = "Charming old-school museum especially nostalgic for schoolkids and families.",
        rating = 4.2,
        reviewCount = 2600,
        lat = 26.8366,
        lng = 80.9219
    ),
    LucknowCatalogSeed.place(
        id = "charbagh_railway_station",
        name = "Charbagh Heritage Railway Station",
        category = PlaceCategory.MUSEUMS,
        locality = "Charbagh",
        address = "Station Road, Charbagh, Lucknow 226004",
        timings = "Open 24 Hours (Exterior Promenade)",
        ticketPrice = "Free Exterior (₹10 Platform Ticket)",
        bestTimeToVisit = "Morning or Lit Evening",
        description = "Built in 1914 in grand Indo-Saracenic palace style resembling a chessboard from above, famous for red-and-cream domes and chhatri pavilions.",
        highlights = listOf("1914 Palace Facade", "Chessboard Aerial Plan", "Chhatri Towers"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Charbagh_Railway_Station,_Lucknow.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Lko_charbagh_railway_station_3.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Lucknow_Charbagh_Railway_Station_1.jpg")
        ),
        reviewSummary = "Widely rated India's most beautiful railway station—worth stepping outside just to photograph the facade.",
        rating = 4.6,
        reviewCount = 52000,
        lat = 26.8318,
        lng = 80.9212,
        isFeatured = true
    ),
    LucknowCatalogSeed.place(
        id = "up_sangeet_natak_akademi",
        name = "UP Sangeet Natak Akademi",
        category = PlaceCategory.MUSEUMS,
        locality = "Gomti Nagar",
        address = "Vipin Khand, Gomti Nagar, Lucknow 226010",
        timings = "10:00 AM – 8:00 PM (Show timings vary)",
        ticketPrice = "Free / Event Pass",
        bestTimeToVisit = "Evening Cultural Performances",
        description = "Cultural hub hosting Kathak recitals, classical Hindustani music festivals, and traditional Awadhi folk theatre.",
        highlights = listOf("Kathak Recitals", "Sant Gadge Auditorium", "Classical Concerts"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Vidhan_Sabha_Lucknow.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Vidhan_sabha_(at_night).jpg")
        ),
        reviewSummary = "Check local event listings—evening classical dance and ghazal recitals here are extraordinary.",
        rating = 4.5,
        reviewCount = 3800,
        lat = 26.8529,
        lng = 80.9756
    ),
    LucknowCatalogSeed.place(
        id = "bhartendu_natya_akademi",
        name = "Bhartendu Natya Akademi",
        category = PlaceCategory.MUSEUMS,
        locality = "Gomti Nagar",
        address = "Bhartendu Bhavan, Vipin Khand, Gomti Nagar, Lucknow 226010",
        timings = "10:00 AM – 8:30 PM",
        ticketPrice = "₹50 – ₹200 (Play Tickets)",
        bestTimeToVisit = "Weekend Evening Plays (6:30 PM)",
        description = "Premier repertory theatre institute staging acclaimed Hindi and Urdu dramas, street plays, and stage festivals.",
        highlights = listOf("Live Hindi Theatre", "Thrust Stage", "Weekend Drama Festivals"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Vidhan_sabha_(at_night).jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Vidhan_Sabha_Lucknow.jpg")
        ),
        reviewSummary = "Authentic Lucknow theatre experience with powerhouse local actors.",
        rating = 4.6,
        reviewCount = 2400,
        lat = 26.8535,
        lng = 80.9769
    ),
    LucknowCatalogSeed.place(
        id = "lalit_kala_akademi",
        name = "State Lalit Kala Akademi",
        category = PlaceCategory.MUSEUMS,
        locality = "Aliganj / Lal Baradari",
        address = "Lal Baradari Bhawan, Qaisar Bagh, Lucknow 226001",
        timings = "10:30 AM – 5:30 PM",
        closedOn = "Sundays",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Afternoon",
        description = "Housed in the historic 19th-century Lal Baradari coronation hall, showcasing contemporary UP paintings, sculptures, and art exhibitions.",
        highlights = listOf("Lal Baradari Hall", "Rotating Art Shows", "Sculpture Courtyard"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Kaiser_Bagh_-_Lucknow_01.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Kaiser_Bagh_-_Lucknow_02.jpg")
        ),
        reviewSummary = "Admire both local modern art and the historic Nawabi coronation hall itself.",
        rating = 4.4,
        reviewCount = 1900,
        lat = 26.8591,
        lng = 80.9336
    ),
    LucknowCatalogSeed.place(
        id = "ravindralaya_cultural_centre",
        name = "Ravindralaya Auditorium",
        category = PlaceCategory.MUSEUMS,
        locality = "Charbagh",
        address = "Station Rd, Charbagh, Lucknow 226004",
        timings = "10:00 AM – 9:00 PM (Per event schedule)",
        ticketPrice = "Varies by show",
        bestTimeToVisit = "Evening Shows",
        description = "Iconic cultural landmark decorated with an outdoor mural by K.G. Subramanyan, hosting mushairas, classical concerts, and stage plays.",
        highlights = listOf("Famous Ceramic Mural", "Mushairas & Concerts", "1960s Architecture"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Lucknow_Charbagh_Railway_Station_1.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Charbagh_Railway_Station,_Lucknow.jpg")
        ),
        reviewSummary = "Historic cultural stage right opposite Charbagh station.",
        rating = 4.4,
        reviewCount = 4100,
        lat = 26.8352,
        lng = 80.9225
    ),
    LucknowCatalogSeed.place(
        id = "kala_gaon_lucknow",
        name = "Avadh Shilpgram (Crafts Village)",
        category = PlaceCategory.MUSEUMS,
        locality = "Amar Shaheed Path, Gomti Nagar Ext.",
        address = "Sector 9, Vrindavan Yojna / Shaheed Path, Lucknow 226029",
        timings = "11:00 AM – 9:00 PM",
        ticketPrice = "₹20 Entry (Free on select craft fairs)",
        bestTimeToVisit = "4:30 PM – 8:30 PM",
        description = "20-acre red-sandstone artisan hub designed after Rumi Darwaza arches, hosting Chikankari, Zardozi, terracotta, and regional food stalls.",
        highlights = listOf("Chikankari Craft Stalls", "Lucknow Mahotsav Venue", "Amphitheater", "Food Court"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Chikankari_of_lucknow.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Chikan_embroidery,_Lucknow.jpg")
        ),
        reviewSummary = "Spacious artisan complex inspired by Dilli Haat; vibrant during seasonal craft fairs and Hunar Haat.",
        rating = 4.4,
        reviewCount = 7400,
        lat = 26.7878,
        lng = 80.9614
    ),
    LucknowCatalogSeed.place(
        id = "sanatkada_culture_house",
        name = "Sanatkada Heritage & Craft Space",
        category = PlaceCategory.MUSEUMS,
        locality = "Qaisar Bagh",
        address = "110 A, Wazir Hasan Rd / Qaisar Bagh, Lucknow 226001",
        timings = "11:00 AM – 7:30 PM",
        closedOn = "Sundays",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Afternoon",
        description = "Curated cultural hub famous for the annual Mahindra Sanatkada Lucknow Festival, handloom cottons, Awadhi books, and heritage walks.",
        highlights = listOf("Awadhi Books & Crafts", "Handwoven Chikankari", "Cultural Talks"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Chikan_embroidery,_Lucknow.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Kaiser_Bagh_-_Lucknow_02.jpg")
        ),
        reviewSummary = "Beloved by culture enthusiasts looking for authentic Lucknow stories, crafts, and thoughtful souvenirs.",
        rating = 4.6,
        reviewCount = 950,
        lat = 26.8548,
        lng = 80.9389
    ),
    LucknowCatalogSeed.place(
        id = "lucknow_university_heritage_campus",
        name = "Lucknow University Heritage Campus",
        category = PlaceCategory.MUSEUMS,
        locality = "Babuganj, Hasanganj",
        address = "University Road, Babuganj, Lucknow 226007",
        timings = "8:00 AM – 5:00 PM (Public campus walk)",
        closedOn = "Sundays",
        ticketPrice = "Free Entry",
        bestTimeToVisit = "Morning",
        description = "Founded in 1920 at Badshah Bagh, featuring majestic Indo-Saracenic red-brick clock towers, arched verandahs, and Tagore Library.",
        highlights = listOf("1920 Heritage Building", "Tagore Library", "Lal Baradari Pavilion"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Lucknow_University.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Lucknow_University_Red_Building.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Bird_view_of_Lucknow_University.jpg")
        ),
        reviewSummary = "Walk through the leafy quadrangles to appreciate classic early 20th-century Lucknow architecture.",
        rating = 4.6,
        reviewCount = 11200,
        lat = 26.8669,
        lng = 80.9378
    ),
    LucknowCatalogSeed.place(
        id = "ambedkar_memorial_museum",
        name = "Dr. Ambedkar Samajik Parivartan Gallery",
        category = PlaceCategory.MUSEUMS,
        locality = "Gomti Nagar",
        address = "Inside Ambedkar Memorial Park, Vipin Khand, Lucknow 226010",
        timings = "11:00 AM – 8:00 PM",
        ticketPrice = "Included in Park Ticket (₹20)",
        bestTimeToVisit = "Evening",
        description = "Grand domed rotunda inside Ambedkar Park housing bronze relief murals, sculptures, and historical galleries of social reformers.",
        highlights = listOf("Monumental Rotunda", "Bronze Relief Murals", "Echoing Dome"),
        photoUrls = listOf(
            LucknowCatalogSeed.wikimediaPhoto("Ambedkar_Park,_Lucknow,_India.jpg"),
            LucknowCatalogSeed.wikimediaPhoto("Ambedkar_Memorial_Park-Lucknow-Uttar_Pradesh-MA22.jpg")
        ),
        reviewSummary = "Impressive marble and bronze interior; the central dome echo is remarkable.",
        rating = 4.6,
        reviewCount = 6700,
        lat = 26.8476,
        lng = 80.9768
    )
)
