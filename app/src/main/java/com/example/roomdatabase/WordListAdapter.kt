package com.example.roomdatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class WordListAdapter internal constructor(context: Context) :
RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    var words = emptyList<Word>() //cached copy of words
    //var kata = emptyList<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        holder.wordItemView.text = current.word

        //val anjir = kata[position]
        //holder.ItemView2.text = current.word
    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        //this.kata = words
        notifyDataSetChanged()
    }
    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
        //val ItemView2: TextView = itemView.findViewById(R.id.textView2)
    }

    override fun getItemCount() = words.size

}






