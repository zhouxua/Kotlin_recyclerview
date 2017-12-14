package com.bwie.kotlin_recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

/**
 * Created by dream on 2017/12/14.
 */
class RecyclerViewAdapter(context1 : Context) : RecyclerView.Adapter<RecyclerViewAdapter.MyAdapter>() {

    var context : Context = context1

    var list : ArrayList<Book> = ArrayList()

    fun addData(bean : Bean) {

        list.addAll(bean.result.bookList)

        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyAdapter {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var view = LayoutInflater.from(context).inflate(R.layout.layout,parent,false)
        return MyAdapter(view)
    }
    override fun onBindViewHolder(holder: MyAdapter?, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
         holder!!.tv.setText(list.get(position).name)
        println("tv数据是："+list.get(position).name)
        Glide.with(context).load(list.get(position).coverImg).into(holder!!.img)
    }



    override fun getItemCount(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    return list.size
    }


    class MyAdapter(view: View) : RecyclerView.ViewHolder(view){

            lateinit var img : ImageView
            lateinit var tv : TextView

            init {
                img = view.findViewById(R.id.img)
                tv = view.findViewById(R.id.tv)
            }

//        constructor(view : View) : super(view) {
//            item_imageview = view.findViewById(R.id.item_imageview)
//            item_textview = view.findViewById(R.id.item_textview)
//        }

        }



    }

