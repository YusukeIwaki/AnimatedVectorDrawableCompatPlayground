package io.github.yusukeiwaki.android.widget

import android.content.Context
import android.graphics.drawable.Animatable
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.widget.Checkable
import io.github.yusukeiwaki.android.widget.favorite_button.R

class FavoriteButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): AppCompatImageView(context, attrs, defStyleAttr), Checkable {
    data class CustomAttribute(val checked: Boolean)

    private var isChecked: Boolean = false
    private val customAttribute: CustomAttribute

    init {
        customAttribute = customAttributeFrom(attrs)

        isChecked = customAttribute.checked
        updateDrawableWithoutAnimation()

        setOnClickListener({ _ ->
            toggle()
        })
    }

    private fun customAttributeFrom(attrs: AttributeSet?): CustomAttribute {
        val defaultAttribute = CustomAttribute(false)
        if (attrs != null) {
            val a = context.theme.obtainStyledAttributes(attrs, R.styleable.FavoriteButton, 0, 0)
            val checked = a.getBoolean(R.styleable.FavoriteButton_android_checked, defaultAttribute.checked)
            a.recycle()

            return CustomAttribute(checked)
        } else {
            return defaultAttribute
        }
    }

    override fun setChecked(b: Boolean) {
        if (isChecked != b) {
            isChecked = b
            updateDrawable()
        }
    }

    override fun isChecked(): Boolean {
        return isChecked
    }

    override fun toggle() {
        isChecked = !isChecked
        updateDrawable()
    }

    private fun updateDrawableWithoutAnimation() {
        if (isChecked) {
            setImageResource(R.drawable.ic_favorite_checked);
        } else {
            setImageResource(R.drawable.ic_favorite_unchecked)
        }
    }

    private fun updateDrawable() {
        if (isChecked) {
            setImageResource(R.drawable.ic_anim_favorite_checking);
        } else {
            setImageResource(R.drawable.ic_anim_favorite_unchecking)
        }
        (drawable as? Animatable)?.start()
    }
}
