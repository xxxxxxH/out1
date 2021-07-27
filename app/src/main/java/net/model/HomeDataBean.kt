package model

class HotDataBean : ArrayList<DataBeanItem>()

data class DataBeanItem(
    val height: String,
    val img_category: String,
    val img_like: String,
    val img_tag: String,
    val img_thumb_url: String,
    val img_title: String,
    val img_url: String,
    val thumb_height: String,
    val thumb_width: String,
    val width: String
)