package com.example.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.data.model.LucknowPlace
import com.example.data.model.PlaceCategory
import com.example.ui.components.PlacePhoto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceEditorScreen(
    existingPlace: LucknowPlace?,
    isOwnerAdmin: Boolean,
    isSaving: Boolean,
    onBack: () -> Unit,
    onSave: (LucknowPlace, Boolean) -> Unit,
    onDelete: (LucknowPlace) -> Unit
) {
    val isEditing = existingPlace != null

    var name by remember(existingPlace?.id) { mutableStateOf(existingPlace?.name ?: "") }
    var category by remember(existingPlace?.id) {
        mutableStateOf(existingPlace?.category ?: PlaceCategory.HERITAGE.label)
    }
    var categoryExpanded by remember { mutableStateOf(false) }

    var locality by remember(existingPlace?.id) { mutableStateOf(existingPlace?.locality ?: "") }
    var address by remember(existingPlace?.id) { mutableStateOf(existingPlace?.address ?: "") }
    var timings by remember(existingPlace?.id) { mutableStateOf(existingPlace?.timings ?: "6:00 AM – 6:00 PM") }
    var closedOn by remember(existingPlace?.id) { mutableStateOf(existingPlace?.closedOn ?: "Open all days") }
    var ticketPrice by remember(existingPlace?.id) { mutableStateOf(existingPlace?.ticketPrice ?: "Free Entry") }
    var bestTime by remember(existingPlace?.id) { mutableStateOf(existingPlace?.bestTimeToVisit ?: "Morning & Evening") }
    var description by remember(existingPlace?.id) { mutableStateOf(existingPlace?.description ?: "") }
    var highlightsText by remember(existingPlace?.id) {
        mutableStateOf(existingPlace?.highlights?.joinToString(", ") ?: "")
    }
    var ratingText by remember(existingPlace?.id) {
        mutableStateOf((existingPlace?.rating ?: 4.5).toString())
    }
    var reviewSummary by remember(existingPlace?.id) { mutableStateOf(existingPlace?.reviewSummary ?: "") }
    var reviewVideoTitle by remember(existingPlace?.id) { mutableStateOf(existingPlace?.reviewVideoTitle ?: "") }
    var reviewVideoUrl by remember(existingPlace?.id) { mutableStateOf(existingPlace?.reviewVideoUrl ?: "") }
    var latitudeText by remember(existingPlace?.id) {
        mutableStateOf((existingPlace?.latitude ?: 26.8467).toString())
    }
    var longitudeText by remember(existingPlace?.id) {
        mutableStateOf((existingPlace?.longitude ?: 80.9462).toString())
    }
    var isFeatured by remember(existingPlace?.id) { mutableStateOf(existingPlace?.isFeatured ?: false) }

    val photoUrls = remember(existingPlace?.id) {
        mutableStateListOf<String>().apply {
            addAll(existingPlace?.photoUrls ?: emptyList())
        }
    }
    var newPhotoUrlInput by remember { mutableStateOf("") }
    var validationError by remember { mutableStateOf<String?>(null) }

    // Zero-permission Android Photo Picker for images
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null && photoUrls.size < 12) {
                photoUrls.add(uri.toString())
            }
        }
    )

    // Zero-permission Android Photo Picker for review video
    val videoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                reviewVideoUrl = uri.toString()
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(if (isEditing) "Update Place" else "Add Lucknow Place")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    if (existingPlace != null && (isOwnerAdmin || existingPlace.sourceLabel == "My Update")) {
                        IconButton(onClick = { onDelete(existingPlace) }) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Delete place",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 720.dp)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (isOwnerAdmin) {
                            "Publishing as Owner Admin — updates sync live to every Lucknow Guide user."
                        } else {
                            "Saving to your personal Lucknow Guide collection in the cloud."
                        },
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(12.dp)
                    )
                }

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it.take(120) },
                    label = { Text("Place Name *") },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("editor_name_input")
                )

                ExposedDropdownMenuBox(
                    expanded = categoryExpanded,
                    onExpandedChange = { categoryExpanded = !categoryExpanded }
                ) {
                    OutlinedTextField(
                        value = category,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Category") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded)
                        },
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false }
                    ) {
                        PlaceCategory.allLabels.forEach { cat ->
                            DropdownMenuItem(
                                text = { Text(cat) },
                                onClick = {
                                    category = cat
                                    categoryExpanded = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = locality,
                    onValueChange = { locality = it.take(120) },
                    label = { Text("Locality / Area * (e.g., Gomti Nagar, Chowk)") },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it.take(250) },
                    label = { Text("Full Address *") },
                    maxLines = 2,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedTextField(
                        value = timings,
                        onValueChange = { timings = it.take(150) },
                        label = { Text("Timings *") },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("editor_timings_input")
                    )
                    OutlinedTextField(
                        value = closedOn,
                        onValueChange = { closedOn = it.take(80) },
                        label = { Text("Closed On") },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedTextField(
                        value = ticketPrice,
                        onValueChange = { ticketPrice = it.take(150) },
                        label = { Text("Ticket Price *") },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("editor_ticket_input")
                    )
                    OutlinedTextField(
                        value = bestTime,
                        onValueChange = { bestTime = it.take(120) },
                        label = { Text("Best Time") },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedTextField(
                        value = latitudeText,
                        onValueChange = { latitudeText = it },
                        label = { Text("Latitude") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = longitudeText,
                        onValueChange = { longitudeText = it },
                        label = { Text("Longitude") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.weight(1f)
                    )
                }

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it.take(1500) },
                    label = { Text("Description & Visitor Guide *") },
                    minLines = 3,
                    maxLines = 5,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = highlightsText,
                    onValueChange = { highlightsText = it },
                    label = { Text("Highlights (comma-separated, max 10)") },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                // Review Video Section
                Text(
                    text = "Review Video & Ratings",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                OutlinedTextField(
                    value = reviewVideoTitle,
                    onValueChange = { reviewVideoTitle = it.take(180) },
                    label = { Text("Review Video Title") },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = reviewVideoUrl,
                    onValueChange = { reviewVideoUrl = it.take(500) },
                    label = { Text("Review Video URL (YouTube / MP4 link)") },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("editor_video_url_input")
                )

                OutlinedButton(
                    onClick = {
                        videoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly)
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.VideoLibrary,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Pick Local Review Video (Device Preview)")
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = ratingText,
                        onValueChange = { ratingText = it },
                        label = { Text("Rating (0–5)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.weight(1f)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Featured", style = MaterialTheme.typography.bodyMedium)
                        Switch(
                            checked = isFeatured,
                            onCheckedChange = { isFeatured = it }
                        )
                    }
                }

                OutlinedTextField(
                    value = reviewSummary,
                    onValueChange = { reviewSummary = it.take(800) },
                    label = { Text("Visitor Review Summary & Tips") },
                    minLines = 2,
                    maxLines = 4,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                // Photos Gallery Section
                Text(
                    text = "Photos Gallery (${photoUrls.size}/12)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                if (photoUrls.isNotEmpty()) {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        itemsIndexed(photoUrls) { index, url ->
                            Box(modifier = Modifier.size(104.dp)) {
                                PlacePhoto(
                                    photoUrl = url,
                                    categoryLabel = category,
                                    contentDescription = "Place photo ${index + 1}",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(14.dp))
                                )
                                IconButton(
                                    onClick = { photoUrls.removeAt(index) },
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .size(32.dp)
                                ) {
                                    Surface(
                                        shape = CircleShape,
                                        color = MaterialTheme.colorScheme.errorContainer
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Close,
                                            contentDescription = "Remove photo",
                                            tint = MaterialTheme.colorScheme.onErrorContainer,
                                            modifier = Modifier
                                                .padding(3.dp)
                                                .size(14.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = newPhotoUrlInput,
                        onValueChange = { newPhotoUrlInput = it },
                        label = { Text("Paste HTTPS Photo URL") },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedButton(
                        onClick = {
                            val trimmed = newPhotoUrlInput.trim()
                            if (trimmed.isNotEmpty() && photoUrls.size < 12) {
                                photoUrls.add(trimmed)
                                newPhotoUrlInput = ""
                            }
                        },
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Add")
                    }
                }

                OutlinedButton(
                    onClick = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.AddPhotoAlternate,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Attach Photo from Device (Photo Picker)")
                }

                if (validationError != null) {
                    Surface(
                        color = MaterialTheme.colorScheme.errorContainer,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = validationError ?: "",
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }

                Button(
                    onClick = {
                        if (name.isBlank() || locality.isBlank() || address.isBlank() || timings.isBlank() || ticketPrice.isBlank() || description.isBlank()) {
                            validationError = "Please fill in Name, Locality, Address, Timings, Ticket Price, and Description."
                            return@Button
                        }
                        validationError = null
                        val parsedRating = ratingText.toDoubleOrNull()?.coerceIn(0.0, 5.0) ?: 4.5
                        val parsedLat = latitudeText.toDoubleOrNull()?.coerceIn(-90.0, 90.0) ?: 26.8467
                        val parsedLng = longitudeText.toDoubleOrNull()?.coerceIn(-180.0, 180.0) ?: 80.9462
                        val highlights = highlightsText.split(",")
                            .map { it.trim() }
                            .filter { it.isNotEmpty() }
                            .take(10)

                        val updatedPlace = (existingPlace ?: LucknowPlace()).copy(
                            name = name.trim(),
                            category = category,
                            locality = locality.trim(),
                            address = address.trim(),
                            timings = timings.trim(),
                            closedOn = closedOn.trim().ifBlank { "Open all days" },
                            ticketPrice = ticketPrice.trim(),
                            bestTimeToVisit = bestTime.trim(),
                            description = description.trim(),
                            highlights = highlights,
                            photoUrls = photoUrls.toList(),
                            reviewVideoTitle = reviewVideoTitle.trim(),
                            reviewVideoUrl = reviewVideoUrl.trim(),
                            reviewSummary = reviewSummary.trim(),
                            rating = parsedRating,
                            latitude = parsedLat,
                            longitude = parsedLng,
                            isPublished = true,
                            isFeatured = isFeatured
                        )
                        onSave(updatedPlace, isOwnerAdmin)
                    },
                    enabled = !isSaving,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .testTag("save_place_button")
                ) {
                    if (isSaving) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Saving…")
                    } else {
                        Icon(
                            imageVector = Icons.Filled.Save,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            if (isOwnerAdmin) "Publish Live Update" else "Save Place to My Guide"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
