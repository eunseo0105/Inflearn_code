package com.es.inflearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val data= arrayOf("aaaa","bbbb","cccc","aabb","bbcc")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,data)
        listView.adapter=adapter

        //검색어 기준으로 필터링 될 수 있도록 설정
        listView.isTextFilterEnabled=true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        val item1=menu?.findItem(R.id.item1)
        val search1= item1?.actionView as SearchView
        search1.queryHint="검색어 입력"

        //메뉴 아이템에 배치된 뷰가 접히거나 펼쳐질 때 반응하는 리스너
        val listener1= object: MenuItem.OnActionExpandListener{
            //접혔을때 호출
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                textView.text="접혀졌습니다"
                return true
            }
            //펼쳐졌을 때 호출
            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                textView.text="펼쳐졌습니다"
                return true
            }

        }
        item1.setOnActionExpandListener(listener1)

        //search 뷰의 리스너
        val listener2=object:SearchView.OnQueryTextListener{
            //SearchView에 문자열을 입력할 때 마다 호출되는 메서드
            override fun onQueryTextChange(newText: String?): Boolean {
                textView.text="문자열 입력중"
                textView2.text="입력중: $newText"

                //searchView에 입력한 내용을 listView의 필터 문자열로 설정한다.
                listView.setFilterText(newText)

                //만약 설정한 문자열의 길이가 0이라면 필터 문자열을 제거한다.
                if (newText?.length==0){
                    listView.clearTextFilter()
                }
                return true
            }
            //키보드의 돋보기 버튼을 눌렀을 때 호출되는 메서드
            override fun onQueryTextSubmit(query: String?): Boolean {
                textView.text="문자열 입력 완료"
                textView2.text="입력 완료 : $query"
                return true
            }
        }
        search1.setOnQueryTextListener(listener2)
        return true
    }

  /*  override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1->{
                textView.text="첫번째 메뉴"
            }
            R.id.item2->{
                textView.text="두번째 메뉴"
            }
            R.id.item3->{
                textView.text="세번째 메뉴"
            }
            R.id.item4->{
                textView.text="네번째 메뉴"
            }
        }
        return super.onOptionsItemSelected(item)
    }*/
}