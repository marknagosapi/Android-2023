package com.tasty.recipesapp.ui.RecipeFragments

import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.model.NewRecipeModel
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class NewRecipeAdapter(
    var recipes: List<NewRecipeModel>,
    private val onRecipeLongClicked: (NewRecipeModel) -> Unit,
    private val onClickListener: (NewRecipeModel) -> Unit

) : RecyclerView.Adapter<NewRecipeAdapter.NewRecipeViewHolder>() {

    private lateinit var cakeIcon: FloatingActionButton
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewRecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.own_item_recipe, parent, false)
        cakeIcon = view.findViewById(R.id.cakeIcon)
        return NewRecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewRecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.textView.text = recipe.title

        if(recipe.videoUrl != "FAV"){
            cakeIcon.hide()
        }

        if(recipe.videoUrl == "FAV"){
            cakeIcon.show()
        }

        val executor = Executors.newSingleThreadExecutor()

        val handler = Handler(Looper.getMainLooper())

        executor.execute {

            val imageURL = recipe.thumbnailUrl

            try {
                val url = URL(imageURL)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(input)

                handler.post {
                    holder.imageView.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        holder.itemView.setOnLongClickListener{
            onRecipeLongClicked.invoke(recipe)
            true
        }

        holder.itemView.setOnClickListener {
            onClickListener.invoke(recipe)
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun updateData(newData: List<NewRecipeModel>) {
        recipes = newData
        notifyDataSetChanged()
    }

    class NewRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView : TextView = itemView.findViewById(R.id.textView)
        val imageView : ImageView = itemView.findViewById(R.id.imageView)
    }

}
