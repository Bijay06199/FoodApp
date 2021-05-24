package com.example.foodbox.base

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.transition.TransitionManager
import com.example.foodbox.BR
import com.example.foodbox.data.prefs.PreferenceManager
import com.example.foodbox.utils.extentions.setupUI
import org.koin.android.ext.android.inject

abstract class BaseActivity<DATA_BINDING : ViewDataBinding, VIEW_MODEL : ViewModel> : AppCompatActivity() {

    lateinit var viewDataBinding: DATA_BINDING
    private var baseViewModel: VIEW_MODEL? = null
    var layoutView: View? = null
    var popupWindow: PopupWindow? = null
    val preferenceManager: PreferenceManager by inject()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        setupUI(viewDataBinding.root)

    }

    private fun performDataBinding() {
        viewDataBinding= DataBindingUtil.setContentView(this,getLayoutId())
        this.baseViewModel=baseViewModel?:getViewModel()
        viewDataBinding.apply {
            setVariable(getBindingVariable(),baseViewModel)
            setLifecycleOwner(this@BaseActivity)
            executePendingBindings()
        }

    }


    fun popUpWindow(viewlayout: View, viewGroup: ViewGroup, layoutRes: Int){
        val inflater: LayoutInflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate( layoutRes,null)

        layoutView=view




        popupWindow = PopupWindow(
            view, // Custom view to show in popup window
            LinearLayout.LayoutParams.MATCH_PARENT, // Width of popup window
            LinearLayout.LayoutParams.WRAP_CONTENT // Window height

        )





        popupWindow?.elevation = 10.0F

        popupWindow?.isTouchable=true

        popupWindow?.dismiss()
        popupWindow?.isOutsideTouchable = true



        TransitionManager.beginDelayedTransition(viewGroup)
        popupWindow?.showAtLocation(
            viewlayout, // Location to display popup window
            Gravity.CENTER, // Exact position of layout to display popup
            0, // X offset
            0 // Y offset
        )

        var container:View=popupWindow?.contentView!!.rootView
        var windowManager: WindowManager =getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var p: WindowManager.LayoutParams=container.layoutParams as WindowManager.LayoutParams
        p.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND
        p.dimAmount=0.8f
        windowManager.updateViewLayout(container,p)

    }


    fun editTextWatcher1(editText1: EditText, editText2: EditText){
        editText1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (editText1.length()==1)
                    editText2.requestFocus()

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    /** @return layout resource id*/

    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): VIEW_MODEL

    /**
     * Override for set binding variable
     *
     * @return variable id
     */

    open fun getBindingVariable(): Int= BR.viewModel

}