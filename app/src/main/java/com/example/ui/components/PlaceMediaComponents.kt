package com.example.ui.components

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.TempleHindu
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.data.model.LucknowPlace
import com.example.data.model.PlaceCategory

private const val WIKIMEDIA_USER_AGENT =
    "LucknowGuideAndroid/1.0 (https://commons.wikimedia.org; lucknowguide-app)"

@Composable
fun PlacePhoto(
    photoUrl: String?,
    categoryLabel: String = PlaceCategory.HERITAGE.label,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    val context = LocalContext.current
    val resolvedCategory = remember(categoryLabel) { PlaceCategory.fromLabel(categoryLabel) }
    val categoryFallbackUrl = resolvedCategory.fallbackPhotoUrl
    var useCategoryFallback by remember(photoUrl, categoryFallbackUrl) { mutableStateOf(false) }

    val targetUrl = remember(photoUrl, useCategoryFallback, categoryFallbackUrl) {
        if (useCategoryFallback || photoUrl.isNullOrBlank()) {
            categoryFallbackUrl
        } else {
            photoUrl
        }
    }

    val imageRequest = remember(targetUrl, context) {
        ImageRequest.Builder(context)
            .data(targetUrl)
            .addHeader("User-Agent", WIKIMEDIA_USER_AGENT)
            .crossfade(true)
            .build()
    }

    SubcomposeAsyncImage(
        model = imageRequest,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.surfaceVariant,
                                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(22.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                )
            }
        },
        error = {
            if (!useCategoryFallback && targetUrl != categoryFallbackUrl) {
                useCategoryFallback = true
            } else {
                CategoryPhotoPlaceholder(
                    category = resolvedCategory,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    )
}

@Composable
private fun CategoryPhotoPlaceholder(
    category: PlaceCategory,
    modifier: Modifier = Modifier
) {
    val icon = when (category) {
        PlaceCategory.HERITAGE -> Icons.Filled.AccountBalance
        PlaceCategory.PARKS -> Icons.Filled.Park
        PlaceCategory.SPIRITUAL -> Icons.Filled.TempleHindu
        PlaceCategory.MUSEUMS -> Icons.Filled.Museum
        PlaceCategory.FOOD -> Icons.Filled.Restaurant
        PlaceCategory.MARKETS -> Icons.Filled.ShoppingBag
        PlaceCategory.DAY_TRIPS -> Icons.Filled.DirectionsCar
    }
    Box(
        modifier = modifier.background(
            Brush.linearGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primaryContainer,
                    MaterialTheme.colorScheme.secondaryContainer
                )
            )
        ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.55f),
            modifier = Modifier.size(36.dp)
        )
    }
}

fun isDirectVideoStream(url: String): Boolean {
    val lower = url.lowercase()
    return lower.startsWith("content://") ||
        lower.endsWith(".mp4") ||
        lower.endsWith(".m3u8") ||
        lower.endsWith(".webm") ||
        lower.endsWith(".3gp") ||
        lower.contains(".mp4?")
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ReviewVideoPlayerDialog(
    place: LucknowPlace,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val videoUrl = place.reviewVideoUrl.ifBlank {
        "https://www.youtube.com/results?search_query=${Uri.encode("${place.name} Lucknow review")}"
    }
    val isDirectStream = remember(videoUrl) { isDirectVideoStream(videoUrl) }
    var isWebLoading by remember { mutableStateOf(true) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(vertical = 24.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayCircleFilled,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = place.reviewVideoTitle.ifBlank { "${place.name} Review" },
                                style = MaterialTheme.typography.titleSmall,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = place.name,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Close video player"
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    if (isDirectStream) {
                        AndroidView(
                            factory = { ctx ->
                                VideoView(ctx).apply {
                                    val controller = MediaController(ctx)
                                    controller.setAnchorView(this)
                                    setMediaController(controller)
                                    setVideoURI(Uri.parse(videoUrl))
                                    setOnPreparedListener { mp ->
                                        isWebLoading = false
                                        mp.isLooping = false
                                        start()
                                    }
                                    setOnErrorListener { _, _, _ ->
                                        isWebLoading = false
                                        false
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        AndroidView(
                            factory = { ctx ->
                                WebView(ctx).apply {
                                    settings.javaScriptEnabled = true
                                    settings.domStorageEnabled = true
                                    settings.mediaPlaybackRequiresUserGesture = false
                                    webChromeClient = WebChromeClient()
                                    webViewClient = object : WebViewClient() {
                                        override fun onPageFinished(view: WebView?, url: String?) {
                                            isWebLoading = false
                                        }

                                        override fun shouldOverrideUrlLoading(
                                            view: WebView?,
                                            request: WebResourceRequest?
                                        ): Boolean {
                                            return false
                                        }
                                    }
                                    loadUrl(videoUrl)
                                }
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    if (isWebLoading) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (place.reviewSummary.isNotBlank()) {
                        Text(
                            text = place.reviewSummary,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Button(
                        onClick = {
                            runCatching {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
                                context.startActivity(intent)
                            }
                        },
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Open Full Video in YouTube / Browser")
                    }
                }
            }
        }
    }
}
