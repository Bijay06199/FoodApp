package com.example.foodbox.ui.main

import android.os.Bundle
import com.example.foodbox.BR
import com.example.foodbox.R
import com.example.foodbox.base.BaseActivity
import com.example.foodbox.databinding.ActivityMainBinding
import com.example.foodbox.ui.main.home.HomeFragment
import com.example.foodbox.ui.main.home.cart.CartFragment
import com.example.foodbox.ui.main.home.cart.ShoppingCart
import com.google.android.material.badge.BadgeDrawable
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {

    override fun getLayoutId(): Int =R.layout.activity_main
    override fun getViewModel(): MainViewModel =mainViewModel
    private val mainViewModel:MainViewModel by viewModel()

    lateinit var badge: BadgeDrawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeFragment.start(this,R.id.main_screen_container)
        initView()
        badge=viewDataBinding.bottomNavigation.getOrCreateBadge(R.id.page3)
        badge.isVisible=true
        badge.number= ShoppingCart.getCart().size

    }

    private fun initView() {
        with(viewDataBinding){

            bottomNavigation.setOnNavigationItemSelectedListener { item ->

                when(item.itemId){
                    R.id.page1->{
                        HomeFragment.start(this@MainActivity,R.id.main_screen_container)
                        true
                    }
                    R.id.page2->{

                        true
                    }
                    R.id.page3->{
                        CartFragment.start(this@MainActivity,R.id.main_screen_container)

                        true
                    }
                    R.id.page4->{

                        true
                    }
                    else->false
                }
            }
        }
    }

    override fun onBackPressed() {
        val currentFragment=supportFragmentManager.findFragmentById(R.id.main_screen_container)
        if (currentFragment is HomeFragment){
           finish()
        }
        if (supportFragmentManager.backStackEntryCount>0){
            supportFragmentManager.popBackStack()
        }
        else{
            super.onBackPressed()
            finish()
        }
    }
}