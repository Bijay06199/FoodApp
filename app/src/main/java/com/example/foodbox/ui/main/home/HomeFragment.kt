package com.example.foodbox.ui.main.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.foodbox.R
import com.example.foodbox.base.BaseFragment
import com.example.foodbox.databinding.FragmentHomeBinding
import com.example.foodbox.ui.main.MainActivity
import com.example.foodbox.ui.main.home.adapter.*
import com.example.foodbox.ui.main.home.cart.CartItemModel
import com.example.foodbox.ui.main.home.cart.ShoppingCart
import com.example.foodbox.ui.main.home.model.BannerModel
import com.example.foodbox.ui.main.home.model.DietTypeModel
import com.example.foodbox.ui.main.home.model.SpotLightModel
import com.example.foodbox.ui.main.home.model.TopPicksModel
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),TopPicksAdapter.OnItemClickListener,SpotLightAdapter.OnItemClickListener {
    override fun getLayoutId(): Int = R.layout.fragment_home
    override fun getViewModel(): HomeViewModel = homeViewModel
    private val homeViewModel: HomeViewModel by viewModel()


    lateinit var bannerAdapter: BannerAdapter
    lateinit var dietTypeAdapter: DietTypeAdapter
    lateinit var topPicksAdapter: TopPicksAdapter
    lateinit var popularAdapter: PopularAdapter
    lateinit var spotLightAdapter: SpotLightAdapter
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<MaterialCardView>
    lateinit var badge: BadgeDrawable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        bottomSheetBehavior = BottomSheetBehavior.from<MaterialCardView>(persistent_bottom_sheet)
        if (savedInstanceState == null) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }

        bottomSheetBehavior.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {


                    BottomSheetBehavior.STATE_EXPANDED -> {
                        viewDataBinding.cvWithBottomSheet.visibility = View.VISIBLE
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        viewDataBinding.cvWithBottomSheet.visibility = View.GONE
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED.and(20) -> {
                        viewDataBinding.cvWithBottomSheet.visibility = View.VISIBLE
                    }

                }
            }

        })
        setUpBannerLayout()
        setUpRecyclerViewType()
        setUpRecyclerViewTop()
        setUpRecyclerViewPopular()
        setUpRecyclerViewSpotLight()
    }

    private fun initView() {
        with(viewDataBinding){


        }
    }

    private fun setUpRecyclerViewSpotLight() {
        with(viewDataBinding){

            spotLightAdapter=SpotLightAdapter(this@HomeFragment)
            recyclerviewSpotlight.adapter=spotLightAdapter
            var itemList=ArrayList<SpotLightModel>()


                itemList.add(SpotLightModel(1,R.drawable.grilled_chicken,"Egg Chowmein","Chicken World","Rs 550","Rs 500","10% off"))
                itemList.add(SpotLightModel(2,R.drawable.burger_king,"Chicken Berger","Berger","Rs 300","Rs 250","5% off"))
                itemList.add(SpotLightModel(3,R.drawable.coffee,"Black Coffee","Coffee","Rs 100","Rs 50","5% off"))
                itemList.add(SpotLightModel(4,R.drawable.grill_durbar,"Grilled Chicken","Chicken","Rs 400","Rs 300","15% off"))
                itemList.add(SpotLightModel(5,R.drawable.food1,"Mushroom Fry Rice","Rice","Rs 400","Rs 300","15% off"))
                itemList.add(SpotLightModel(6,R.drawable.food2,"Mutton Curry","Mutton","Rs 400","Rs 300","15% off"))
                itemList.add(SpotLightModel(7,R.drawable.food3,"Vegetable Dish","Vegetable Curry","Rs 400","Rs 300","15% off"))
                itemList.add(SpotLightModel(8,R.drawable.food4,"Chicken Biryani","Biryani","Rs 400","Rs 300","15% off"))
                itemList.add(SpotLightModel(9,R.drawable.food5,"Roti Tarkali","Roti","Rs 400","Rs 300","15% off"))

            spotLightAdapter.addAll(itemList)
        }
    }

    private fun setUpRecyclerViewPopular() {
        with(viewDataBinding){

           popularAdapter= PopularAdapter()
            recyclerviewPopular.adapter = popularAdapter
            var itemList = ArrayList<DietTypeModel>()

                itemList.add(DietTypeModel(R.drawable.kfc, "KFC"))
                itemList.add(DietTypeModel(R.drawable.kkfc, "KKFC"))
                itemList.add(DietTypeModel(R.drawable.burger_king, "Burger Kings"))
                itemList.add(DietTypeModel(R.drawable.bajeko, "Bajeko Sekuwa"))
                itemList.add(DietTypeModel(R.drawable.grill_durbar, "Grill Chicken"))
                



            popularAdapter.addAll(itemList)
        }
    }

    private fun setUpRecyclerViewTop() {
        with(viewDataBinding){
            topPicksAdapter= TopPicksAdapter(this@HomeFragment)
            viewpagerTopPicks.adapter=topPicksAdapter
            var itemListTopPicks=ArrayList<TopPicksModel>()
            for (i in 0..3){
                itemListTopPicks.add(TopPicksModel(R.drawable.grilled_chicken,"Grilled Chicken","By KFC","Rs 600","Rs 500"))

            }
            topPicksAdapter.addAll(itemListTopPicks)

            viewpagerTopPicks.clipToPadding = false
            viewpagerTopPicks.setPadding(15, 0, 50, 0)

        }
    }

    private fun setUpRecyclerViewType() {
        with(viewDataBinding) {

            dietTypeAdapter = DietTypeAdapter()
            recyclerviewType.adapter = dietTypeAdapter
            var itemList = ArrayList<DietTypeModel>()
            itemList.add(DietTypeModel(R.drawable.pizza, "Breakfast"))
            itemList.add(DietTypeModel(R.drawable.coffee, "Lunch & Snacks"))
            itemList.add(DietTypeModel(R.drawable.pizza, "Coffee"))
            itemList.add(DietTypeModel(R.drawable.coffee, "Tea"))
            itemList.add(DietTypeModel(R.drawable.pizza, "Breakfast"))

            dietTypeAdapter.addAll(itemList)

        }
    }

    private fun setUpBannerLayout() {
        with(viewDataBinding) {
            bannerAdapter = BannerAdapter(context!!)
            bannerViewPager.adapter = bannerAdapter
            var itemList = ArrayList<BannerModel>()
            itemList.add(BannerModel(R.drawable.banner_image))
            itemList.add(BannerModel(R.drawable.banner_image))
            itemList.add(BannerModel(R.drawable.banner_image))
            itemList.add(BannerModel(R.drawable.banner_image))

            bannerAdapter.addAll(itemList)

            var rv = bannerViewPager.getChildAt(0) as RecyclerView

            rv.clipToPadding = false
            rv.setPadding(50, 0, 50, 0)
            bannerViewPager.postDelayed({
                bannerViewPager.currentItem = 1
            }, 5)

            setUpIndicator()
            setCurrentIndicator(0)

            bannerViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    setCurrentIndicator(position)
                }
            })
        }
    }

    private fun setCurrentIndicator(index: Int) {
        with(viewDataBinding) {
            val childCount = layoutIndicators.childCount
            for (i in 0 until childCount) {
                val imageView = layoutIndicators.get(i) as ImageView
                if (i == index) {
                    imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.indicator_active
                        )
                    )
                } else {
                    imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.indicator_inactive
                        )
                    )
                }
            }
        }
    }

    private fun setUpIndicator() {
        with(viewDataBinding) {
            var indicators = arrayOfNulls<ImageView>(bannerAdapter.itemCount)

            val layoutParams: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            layoutParams.setMargins(8, 0, 8, 0)
            for (i in indicators.indices) {

                indicators[i] = ImageView(requireContext())
                indicators[i].apply {
                    this?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.indicator_inactive
                        )
                    )
                    this?.layoutParams = layoutParams
                }
                layoutIndicators.addView(indicators[i])
            }
        }
    }

    companion object {
        fun start(activity: FragmentActivity, containerId: Int) {
            val fragment = HomeFragment()
            activity.supportFragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack("TAG")
                .commit()
        }
    }

    override fun onClicked(position: Int, itemList: TopPicksModel) {

    }

    override fun onClicked(position: Int, itemList: SpotLightModel) {

        with(viewDataBinding){
            btnAddToCart.visibility = View.VISIBLE
            addSubtractButton.visibility = View.GONE



            for (i in 0..position) {

                var cartItemModel = CartItemModel(itemList, 0)

                var _counter = cartItemModel.quantity



                btnAddToCart.setOnClickListener {

                    _counter++

                    val item = CartItemModel(itemList,_counter)
                    ShoppingCart.addItem(item, requireContext())
                    btnAddToCart.visibility = View.GONE
                    addSubtractButton.visibility = View.VISIBLE
                    (context as MainActivity).badge.number=ShoppingCart.getCart().size
                    tvCartQuantity.setText(Integer.toString(_counter))
                }


                clAdd.setOnClickListener { view ->

                    _counter++
                    val item = CartItemModel(itemList,_counter)
                    ShoppingCart.addItem(item, requireContext())

                    ShoppingCart.getCart()
                    (context as MainActivity).badge.number=ShoppingCart.getCart().size
                    tvCartQuantity.setText(Integer.toString(_counter))


                }


                clSubtract.setOnClickListener {

                    _counter--
                    val item = CartItemModel(itemList,_counter)
                    ShoppingCart.removeItem(item, requireContext())
                    ShoppingCart.getCart()
                    (context as MainActivity).badge.number=ShoppingCart.getCart().size
                    tvCartQuantity.setText(Integer.toString(_counter))

                    if (_counter == 0) {
                        ShoppingCart.removeItem(item, requireContext())
                        btnAddToCart.visibility = View.VISIBLE
                        (context as MainActivity).badge.number=ShoppingCart.getCart().size
                        addSubtractButton.visibility = View.GONE

                    }

                }
            }

            var productName = itemList.type
            var description = itemList.by

            var price = itemList.newPrice

            viewDataBinding.textView20.setText("Product Description")
            viewDataBinding.tvDescriptions.setText(description)
            Glide.with(itemImage)
                .load(itemList.image)
                .into(itemImage)
            viewDataBinding.tvProductName1.setText(productName)
            viewDataBinding.tvProductName.setText(productName)
            viewDataBinding.tvPrice.setText(price)
            viewDataBinding.tvTotalAmount.setText(price)






            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED.and(20)
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }




        }

    }
}