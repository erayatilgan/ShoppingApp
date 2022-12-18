package com.info.shoppingapp.presentation.screens.instruction

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.info.shoppingapp.R
import com.info.shoppingapp.core.databases.InstructionFakeData.sliderList
import com.info.shoppingapp.domain.entities.Instruction
import com.info.shoppingapp.presentation.components.TextButtonX
import com.info.shoppingapp.presentation.screens.navigation.view.NavigationActivity
import com.info.shoppingapp.presentation.ui.theme.ShoppingAppTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingAppTheme {
                val systemUiController = rememberSystemUiController()
                val isDark = isSystemInDarkTheme()
                val background = colors.background
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = background,
                        darkIcons = !isDark
                    )
                    systemUiController.setNavigationBarColor(
                        color = background,
                        darkIcons = !isDark
                    )
                }
                Surface(modifier = Modifier.fillMaxSize()) {
                    val pagerState = rememberPagerState(
                        pageCount = sliderList.size,
                        initialOffscreenLimit = 2,
                        infiniteLoop = false,
                        initialPage = 0,
                    )
                    SliderContent(
                        item = sliderList,
                        pagerState = pagerState,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@DelicateCoroutinesApi
@ExperimentalPagerApi
@Composable
fun SliderContent(
    item: List<Instruction>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    val configuration = LocalConfiguration.current
    val context = LocalContext.current

    Box(
        modifier = modifier
            .background(colors.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPagerIndicator(
                modifier = Modifier.padding(top = 10.dp),
                pagerState = pagerState,
                activeColor = Color.Black,
                inactiveColor = Color.LightGray,
                indicatorHeight = 3.dp,
                indicatorWidth = (configuration.screenWidthDp.dp - 60.dp) / 3,
                spacing = 1.dp,
            )
            HorizontalPager(state = pagerState) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Image(
                        painter = painterResource(item[page].image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = colors.primary,
                                fontSize = 45.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        ) {
                            append(
                                item[pagerState.currentPage].firstDescription
                            )
                        }

                        withStyle(
                            style = SpanStyle(
                                color = colors.background,
                                fontSize = 6.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        ) {
                            append(
                                item[pagerState.currentPage].firstDescription
                            )
                        }

                        withStyle(
                            style = SpanStyle(
                                color = colors.primaryVariant.copy(alpha = .6f),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        ) {
                            append(
                                item[pagerState.currentPage].secondDescription
                            )
                        }
                    })
                }
            }
        }
        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
            .padding(horizontal = 30.dp)
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (pagerState.currentPage != 2) {
                    Text(
                        text = stringResource(id = R.string.skip),
                        color = colors.primaryVariant,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable {
                            context.startActivity(Intent(context, NavigationActivity::class.java))
                        }
                    )

                    TextButtonX(
                        text = stringResource(id = R.string.next)
                    ) {
                        GlobalScope.launch {
                            pagerState.scrollToPage(
                                pagerState.currentPage + 1,
                                pageOffset = 0f
                            )
                        }
                    }
                } else {
                    Text(
                        text = stringResource(id = R.string.skip),
                        color = colors.primaryVariant,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable {
                            context.startActivity(Intent(context, NavigationActivity::class.java))
                        }
                    )
                    TextButtonX(
                        text = stringResource(id = R.string.next)
                    ) {
                        context.startActivity(Intent(context, NavigationActivity::class.java))
                    }
                }
            }
        }
    }
}



