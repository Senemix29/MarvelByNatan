package ximenapps.com.br.marvelbynatan.common.presentation.error

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class UIError(
    @StringRes val title: Int,
    @StringRes val message: Int,
    @DrawableRes val icon: Int
)