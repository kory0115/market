package com.example.applemarket

import java.text.DecimalFormat

open class ItemList {
    fun bind() : List<ItemEntity> {
        val data = ArrayList<ItemEntity>()
        data.add(ItemEntity(name = "산진 한달된 선풍기 팝니다", description = R.string.number1, seller = "대현동", price = String.format("%,d", 1000), address = "서울 서대문구 창천동", like = count1, chat = chat1, image = R.drawable.sample1, key = 1, status = false))
        data.add(ItemEntity(name = "김치 냉장고", description = R.string.number2, seller = "안마담", price = String.format("%,d", 20000), address = "인천 계양구 귤현동", like = count2, chat = chat2, image = R.drawable.sample2, key = 2, status = false))
        data.add(ItemEntity(name = "샤넬 카드 지갑", description = R.string.number3, seller = "코코유", price = String.format("%,d", 10000), address = "수성구 범어동", like = count3, chat = chat3, image = R.drawable.sample3, key = 3, status = false))
        data.add(ItemEntity(name = "금고", description = R.string.number4, seller = "Nicole", price = String.format("%,d", 10000), address = "해운대구 우제2동", like = count4, chat = chat4, image = R.drawable.sample4, key = 4, status = false))
        data.add(ItemEntity(name = "갤럭시 Z플립3 팝니다", description = R.string.number5, seller = "절명", price = String.format("%,d", 30000), address = "연제구 연산제8동", like = count5, chat = chat5, image = R.drawable.sample5, key = 5, status = false))
        data.add(ItemEntity(name = "프라다 복 조리백", description = R.string.number6, seller = "미니멀하게", price = String.format("%,d", 50000), address = "수원시 영통구 원천동", like = count6, chat = chat6, image = R.drawable.sample6, key = 6, status = false))
        data.add(ItemEntity(name = "울산 동해 오션뷰 60평 복층 펜트 하우스 1일 숙박권 펜션 힐링 숙소 별장", description = R.string.number7, seller = "굿리치", price = String.format("%,d", 150000), address = "남구 옥동", like = count7, chat = chat7, image = R.drawable.sample7, key = 7, status = false))
        data.add(ItemEntity(name = "샤넬 탑핸들 가방", description = R.string.number8, seller = "난쉽", price = String.format("%,d", 180000), address = "동래구 온천제2동", like = count8, chat = chat8, image = R.drawable.sample8, key = 8, status = false))
        data.add(ItemEntity(name = "4행정 엔진 분무기 판매 합니다.", description = R.string.number9, seller = "알뜰한", price = String.format("%,d", 30000), address = "원주시 명륜2동", like = count9, chat = chat9, image = R.drawable.sample9, key = 9, status = false))
        data.add(ItemEntity(name = "셀린느 버킷 가방", description = R.string.number10, seller = "똑태현", price = String.format("%,d", 190000), address = "중구 동화동", like = count10, chat = chat10, image = R.drawable.sample10, key = 10, status = false))

        return data
    }


    companion object {
        var count1: Int = 30
        var count2: Int = 8
        var count3: Int = 23
        var count4: Int = 14
        var count5: Int = 22
        var count6: Int = 25
        var count7: Int = 142
        var count8: Int = 31
        var count9: Int = 7
        var count10: Int = 40
        var chat1: Int = 25
        var chat2: Int = 5
        var chat3: Int = 17
        var chat4: Int = 9
        var chat5: Int = 16
        var chat6: Int = 54
        var chat7: Int = 7
        var chat8: Int = 28
        var chat9: Int = 6
        var chat10: Int = 40
    }
}