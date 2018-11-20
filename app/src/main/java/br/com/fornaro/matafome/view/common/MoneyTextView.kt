package br.com.fornaro.matafome.view.common

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import br.com.fornaro.matafome.R
import br.com.fornaro.matafome.utils.MoneyUtils

class MoneyTextView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : AppCompatTextView(context, attrs, defStyleAttr) {

    private var price = 0.0f

    init {
        val arr = context.obtainStyledAttributes(attrs, R.styleable.MoneyTextView)
        val price = arr.getString(R.styleable.MoneyTextView_price)
        if (price != null) {
            setPrice(price.toFloat())
        }
        arr.recycle()
    }

    fun setPrice(value: Float) {
        price = value
        text = MoneyUtils.formatMoney(value)
    }
}
