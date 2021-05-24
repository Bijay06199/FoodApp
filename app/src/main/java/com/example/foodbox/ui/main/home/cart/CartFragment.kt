package com.example.foodbox.ui.main.home.cart

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodbox.BR
import com.example.foodbox.R
import com.example.foodbox.base.BaseFragment
import com.example.foodbox.databinding.FragmentCartBinding
import com.example.foodbox.ui.main.MainActivity
import com.example.foodbox.ui.main.home.HomeFragment
import com.example.foodbox.ui.main.home.cart.checkout.CheckOutActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.card.MaterialCardView
import io.paperdb.Paper
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.recyclerview_cartitems.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class CartFragment : BaseFragment<FragmentCartBinding, CartViewModel>(),
    ShoppingCartAdapter.OnItemClickListener {
    override fun getLayoutId(): Int = R.layout.fragment_cart
    override fun getViewModel(): CartViewModel = cartViewModel
    private val cartViewModel: CartViewModel by viewModel()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<MaterialCardView>
    lateinit var adapter: ShoppingCartAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Paper.init(requireContext())
        bottomSheetBehavior = BottomSheetBehavior.from<MaterialCardView>(persistent_bottom_sheet)



        if (savedInstanceState == null) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
        initView()
        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        with(viewDataBinding) {

            adapter = ShoppingCartAdapter(
                requireContext(),
                ShoppingCart.getCart(),
                this@CartFragment,
                this@CartFragment
            )

            rvCartItem.adapter = adapter
            rvCartItem.layoutManager = LinearLayoutManager(this@CartFragment.activity)
            adapter.notifyDataSetChanged()

            if (ShoppingCart.getShoppingCartSize() == 0) {
                clEmpty.visibility = View.VISIBLE
            }

            noOfItems.text = ShoppingCart.getCart().size.toString()


        }
    }

    private fun initView() {
        with(viewDataBinding) {
            topBar.setOnClickListener {
                startActivity(Intent(this@CartFragment.activity, MainActivity::class.java))
            }

            with(cartViewModel) {


                btnCheckout.setOnClickListener {
                    if (noOfItems.text == "0") {
                        Toast.makeText(requireContext(), "No Items To Proceed", Toast.LENGTH_LONG)
                            .show()
                    } else {

                        var intent =
                            Intent(this@CartFragment.activity, CheckOutActivity::class.java)
                        startActivity(intent)

                    }
                }

            }



            bottomSheetBehavior.setBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_HIDDEN -> {
                            amountDescription.visibility = View.VISIBLE
                            cvWithBottomSheet.visibility = View.GONE
                        }
                        BottomSheetBehavior.STATE_EXPANDED -> {
                            amountDescription.visibility = View.GONE
                            cvWithBottomSheet.visibility = View.VISIBLE
                        }
                        BottomSheetBehavior.STATE_HALF_EXPANDED.and(20) -> {
                            amountDescription.visibility = View.GONE
                            cvWithBottomSheet.visibility = View.VISIBLE
                        }
                    }
                }

            })


            topBar.setOnClickListener {
                startActivity(Intent(this@CartFragment.activity, MainActivity::class.java))
            }
        }
    }

    override fun onSelectListener(position: Int, itemList: CartItemModel) {

        var images = itemList.product.image

        var cartItemModel = CartItemModel(itemList.product, 0)

        var _counter = cartItemModel.quantity

        for (i in 0..position) {

            with(viewDataBinding) {

                btnAddToCart.setOnClickListener {

                    _counter++

                    val item = CartItemModel(itemList.product, 0)
                    ShoppingCart.addItem(item, requireContext())
                    btnAddToCart.visibility = View.GONE
                    addSubtractButton.visibility = View.VISIBLE
                    (context as MainActivity).badge.number = ShoppingCart.getCart().size
                    tvCartQuantity.setText(Integer.toString(_counter))
                }


                clAdd.setOnClickListener { view ->

                    _counter++
                    val item = CartItemModel(itemList.product, 0)
                    ShoppingCart.addItem(item, requireContext())

                    ShoppingCart.getCart()
                    (context as MainActivity).badge.number = ShoppingCart.getCart().size
                    tvCartQuantity.setText(Integer.toString(_counter))


                }


                clSubtract.setOnClickListener {

                    _counter--
                    val item = CartItemModel(itemList.product, 0)
                    ShoppingCart.removeItem(item, requireContext())
                    ShoppingCart.getCart()
                    (context as MainActivity).badge.number = ShoppingCart.getCart().size
                    tvCartQuantity.setText(Integer.toString(_counter))

                    if (_counter == 0) {
                        ShoppingCart.removeItem(item, requireContext())
                        btnAddToCart.visibility = View.VISIBLE
                        (context as MainActivity).badge.number = ShoppingCart.getCart().size
                        addSubtractButton.visibility = View.GONE

                    }

                }
                btnAddToCart.visibility = View.VISIBLE
                addSubtractButton.visibility = View.GONE
            }


        }




        Glide.with(viewDataBinding.imageView5)
            .load(images)
            .into(viewDataBinding.imageView5)
        var productName = itemList.product.type


        var price = itemList.product.newPrice

        viewDataBinding.textView15.setText(productName)
        viewDataBinding.tvProductName.setText(productName)

        viewDataBinding.textView17.setText(price)
        viewDataBinding.tvTotalPrice.setText(price)
        viewDataBinding.tvCartAmount1.setText(price)


        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED.and(20)


        } else {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }


    companion object {

        fun start(activity: FragmentActivity, containerId: Int) {

            val fragment = CartFragment()
            activity.supportFragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack("")
                .commit()
        }

    }
}