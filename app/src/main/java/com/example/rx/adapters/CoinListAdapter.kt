package com.example.rx.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rx.R
import com.example.rx.db.entity.ConvertCryptoToCurrency
import java.util.*


class CoinAdapter(private val coinListener: CoinListener) :
    RecyclerView.Adapter<CoinAdapter.CoinHolder>() {
    var list: List<ConvertCryptoToCurrency> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface CoinListener {
        fun onCoinListener(coin: ConvertCryptoToCurrency)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinHolder {
        return CoinHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.rv_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CoinHolder, position: Int) {


        val coin = list[position]
        val symbols = holder.itemView.context.resources.getString(R.string.symbols)
        val lastUpdate = holder.itemView.context.resources.getString(R.string.last_update)
        holder.apply {
            itemView.setOnClickListener {
                coinListener.onCoinListener(coin)}
            tvSymbols.text =
                String.format(Locale.getDefault(), symbols, coin.fromSymbol, coin.toSymbol)
            tvPrice.text = coin.price.toString()
            tvLastUpdate.text =
                String.format(Locale.getDefault(), lastUpdate, coin.getFormattedTime())
            Glide
                .with(itemView)
                .load(coin.getUrl())
                .into(ivLogoCoin)
        }
    }

    override fun getItemCount(): Int = list.size

    inner class CoinHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSymbols: TextView = itemView.findViewById(R.id.tvSymbols)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvLastUpdate: TextView = itemView.findViewById(R.id.tvLastUpdate)
        val ivLogoCoin: ImageView = itemView.findViewById(R.id.ivLogoCoin)
    }

}
