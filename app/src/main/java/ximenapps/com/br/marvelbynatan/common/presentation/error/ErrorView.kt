package ximenapps.com.br.marvelbynatan.common.presentation.error

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import ximenapps.com.br.marvelbynatan.databinding.ViewErrorBinding

class ErrorView @JvmOverloads constructor(
    context: Context,
    private val attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {
    private val binding by lazy {
        ViewErrorBinding.inflate(LayoutInflater.from(context), this, false)
    }

    init {
        addView(binding.errorViewContainer)
    }

    fun setIcon(@DrawableRes iconRes: Int) {
        binding.errorViewImage.setImageResource(iconRes)
    }

    fun setTitle(@StringRes title: Int) {
        binding.errorViewTitle.text = context.getString(title)
    }

    fun setMessage(@StringRes message: Int) {
        binding.errorViewMessage.text = context.getString(message)
    }

    fun setupButtonAction(text: Int, action: () -> Unit) {
        binding.errorViewButton.text = context.getString(text)
        binding.errorViewButton.setOnClickListener { action.invoke() }
    }
}