package com.neraize.realtimedbtest_20220322

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        btnSend.setOnClickListener {
            val inputContent = edtContent.text.toString()

            // 임시 : DB의 하위항목으로 => message 항목생성 => 그밑에 0번항목의 => content 항목에 입력내용을 저장
            realtimeDB.getReference("message").child("0").child("content").setValue(inputContent)

            // 추가 기록 : 현재 시간값을 "2022년 3월5일 오후 5:05" 양식으로 기록
            val now = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyy년 M월 d일 a h:mm")
            val nowStr = sdf.format(now.time)

            realtimeDB.getReference("message").child("0").child("createdAt").setValue(nowStr)

        }
    }

    override fun setValues() {

        // DB 연결 -> 값 기록 연습
        val db = FirebaseDatabase.getInstance("https://realtimedbtest-20220322-99565-default-rtdb.asia-southeast1.firebasedatabase.app/")  // 싱가폴 db주소 대입

        // DB의 하위 정보(Reference) 설정
        val testRef = db.getReference("test")

        // test항목에, "Hello World!" 기록해보기
        testRef.setValue("Hello World")
    }
}