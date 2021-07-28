package net.fragment

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.layout_fragment_cat.*
import net.adapter.TypeAdapter
import net.basicmodel.R
import net.interFace.OnItemClickListener

class CategoritesFragment : Fragment() , OnItemClickListener{

    val types: ArrayList<String> = ArrayList()
    val typeBg: ArrayList<String> = ArrayList()
    var adapter: TypeAdapter? = null;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_fragment_cat,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addAllType()
        initView()
    }

    fun initView(){
        adapter = TypeAdapter(typeBg,types,activity,activity)
        adapter!!.setOnItemClickListener(this@CategoritesFragment)
        recyclerviewCat.layoutManager = GridLayoutManager(activity,3)
        recyclerviewCat.adapter = adapter
    }

    fun addAllType(){
        types.add("car")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.car_bg))
        types.add("car2")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.car2_bg))
        types.add("bear")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.bear_bg))
        types.add("dragon")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.dragon_bg))
        types.add("eagle")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.eagle_bg))
        types.add("fruit")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.fruit_bg))
        types.add("meteor")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.meteor_bg))
        types.add("snowscene")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.snowscene_bg))
        types.add("summer")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.summer_bg))
        types.add("nba")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.nba_bg))
        types.add("painting")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.painting_bg))
        types.add("game")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.game_bg))
        types.add("gun")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.gun_bg))
        types.add("celestial")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.celestial_bg))
        types.add("fighter")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.fighter_bg))
        types.add("landmark")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.landmark_bg))
        types.add("music")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.music_bg))
        types.add("news")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.news_bg))
        types.add("space")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.space_bg))
        types.add("tank")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.tank_bg))
        types.add("waterfall")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.waterfall_bg))
        types.add("animal")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.animal_bg))
        types.add("castle")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.castle_bg))
        types.add("dog")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.dog_bg))
        types.add("bird")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.bird_bg))
        types.add("fireworks")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.fireworks_bg))
        types.add("letter")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.letter_bg))
        types.add("nature")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.nature_bg))
        types.add("nightview")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.nightview_bg))
        types.add("parrot")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.parrot_bg))
        types.add("spark")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.spark_bg))
        types.add("tree")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.truck_bg))
        types.add("aquarium")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.aquarium_bg))
        types.add("brokeglass")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.brokeglass_bg))
        types.add("cat")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.cat_bg))
        types.add("flower")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.flower_bg))
        types.add("natureh")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.natureh_bg))
        types.add("oceanwave")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.oceanwave_bg))
        types.add("sdolphin")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.dolphin_bg))
        types.add("spring")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.spring_bg))
        types.add("truck")
        typeBg.add(imageTranslateUri(requireActivity(),R.mipmap.truck_bg))

    }

    fun imageTranslateUri(context: Context, resId: Int): String {
        val r = context.resources
        val uri = Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                    + r.getResourcePackageName(resId) + "/"
                    + r.getResourceTypeName(resId) + "/"
                    + r.getResourceEntryName(resId)
        )
        return uri.toString()
    }

    override fun onItemClick(view: View?, position: Int, flag: String?) {
        Toast.makeText(activity, "position = $position", Toast.LENGTH_SHORT).show()
    }
}