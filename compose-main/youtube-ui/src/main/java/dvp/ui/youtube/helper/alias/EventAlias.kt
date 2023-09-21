package dvp.ui.youtube.helper.alias

import dvp.data.youtube.viewmodel.MainEvent
import dvp.ui.youtube.viewmodel.models.MediaState
import dvp.ui.youtube.viewmodel.models.PlayerEvent

typealias OnMainUiEvent = (MainEvent) -> Unit
typealias OnPlayerEvent = (PlayerEvent) -> Unit
typealias OnControllerState = (MediaState, OnPlayerEvent) -> Unit