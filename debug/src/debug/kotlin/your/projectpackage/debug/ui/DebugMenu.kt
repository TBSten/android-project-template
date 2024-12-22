package your.projectpackage.debug.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import your.projectPackage.ui.Dispatch
import your.projectPackage.ui.consumeViewModel
import your.projectpackage.debug.ui.component.DebugMenuTabSection

@Composable
internal fun DebugMenu(
    viewModel: DebugMenuViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val (uiState, dispatch) = consumeViewModel(viewModel)

    DebugMenu(
        uiState = uiState,
        dispatch = dispatch,
        modifier = modifier,
    )
}

@Composable
private fun DebugMenu(
    uiState: DebugMenuUiState,
    dispatch: Dispatch<DebugMenuUiAction>,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState { uiState.debugMenuTabs.size }

    LaunchedEffect(pagerState.targetPage) {
        dispatch(DebugMenuUiAction.SelectTab(pagerState.targetPage))
    }

    LaunchedEffect(uiState.selectedTabIndex) {
        pagerState.animateScrollToPage(uiState.selectedTabIndex)
    }

    Surface(Modifier.windowInsetsPadding(WindowInsets.systemBars)) {
        Column(modifier = modifier) {
            DebugMenuTabSection(
                uiState = uiState,
                dispatch = dispatch,
            )

            HorizontalPager(
                state = pagerState,
            ) { page ->
                val tab = uiState.debugMenuTabs[page]

                Box(Modifier.fillMaxSize()) {
                    tab.Content()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DebugMenuPreview() {
    // TODO apply AppTheme
    DebugMenu(
        uiState = DebugMenuUiState(DebugMenuTab.SampleDebugMenuTab),
        dispatch = { },
    )
}
