package com.example.foodbox.ui.main.home.cart.checkout

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.example.foodbox.R
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.custom_layout.*

class CheckOutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
       btnOrder.setOnClickListener {
           showDialog("Do you want to proceed to checkout")
       }
    }

    private fun showDialog(title: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_layout)
        val tVtitle = dialog.findViewById(R.id.tv_title) as TextView
        val firstName = dialog.findViewById(R.id.tv_first_name) as TextView
        val lastName = dialog.findViewById(R.id.tv_last_name) as TextView
        val contactNo = dialog.findViewById(R.id.tv_contact_no) as TextView
        val email = dialog.findViewById(R.id.tv_email) as TextView
        val cardNo = dialog.findViewById(R.id.tv_card_number) as TextView
        val cardExpiry = dialog.findViewById(R.id.tv_card_expiry) as TextView
        val tvAddress = dialog.findViewById(R.id.tv_address) as TextView
        tVtitle.text=title
        firstName.text="FirstName: "+eTFirstName.text.toString()
        lastName.text="LastName: "+eTLastName.text.toString()
        contactNo.text="Contact No: "+eTContactNumber.text.toString()
        email.text="Email Address: "+eTMailAddress.text.toString()
        cardNo.text="Card No:"+eTCardNo.text.toString()
        cardExpiry.text="Card Expiry"+eTCardExpiry.text.toString()
        tvAddress.text="Address"+eTAddress1.text.toString()

        var yesBtn = dialog.findViewById(R.id.btn_yes) as Button
        var noBtn = dialog.findViewById(R.id.btn_no) as Button
        yesBtn.setOnClickListener {
            dialog.dismiss()
        }
        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()

    }
}