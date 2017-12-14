package com.bwie.kotlin_recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class MainActivity : AppCompatActivity() {
    lateinit var adapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        adapter = RecyclerViewAdapter(this)
        recyclerview.adapter = adapter
        getData();
    }

     fun getData() {
        var retrofit=Retrofit.Builder()
                .baseUrl("http://japi.juhe.cn")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
         var service : ApiService = retrofit.create(ApiService::class.java)
        service.getData()
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe({
                     //onNext();
                    next ->
                   //切记，切记是next.string,不是next.tostring
                     var result =  next.string()
                     var gson = Gson()
                     var bean = gson.fromJson(result, Bean::class.java)
                     adapter.addData(bean)

                 },{
                     //onerror()
                     t->
                 })
     }
}
