package com.example.applemarket

open class ItemList {
    fun bind() : List<ItemEntity> {
        val data = ArrayList<ItemEntity>()
        data.add(ItemEntity(name = "산진 한달된 선풍기 팝니다", description = R.string.number1, seller = "대현동", price = "1000", address = "서울 서대문구 창천동", like = 13, chat = 25, image = R.drawable.sample1))
        data.add(ItemEntity(name = "김치 냉장고", description = R.string.number2, seller = "안마담", price = "20000", address = "인천 계양구 귤현동", like = 8, chat = 28, image = R.drawable.sample2))
        data.add(ItemEntity(name = "샤넬 카드 지갑", description = R.string.number3, seller = "코코유", price = "10000", address = "수성구 범어동", like = 23, chat = 5, image = R.drawable.sample3))
        data.add(ItemEntity(name = "금고", description = R.string.number4, seller = "Nicole", price = "10000", address = "해운대구 우제2동", like = 14, chat = 17, image = R.drawable.sample4))
        data.add(ItemEntity(name = "갤럭시 Z플립3 팝니다", description = R.string.number5, seller = "절명", price = "150000", address = "연제구 연산제8동", like = 22, chat = 9, image = R.drawable.sample5))
        data.add(ItemEntity(name = "프라다 복 조리백", description = R.string.number6, seller = "미니멀하게", price = "50000", address = "수원시 영통구 원천동", like = 25, chat = 16, image = R.drawable.sample6))
        data.add(ItemEntity(name = "울산 동해 오션뷰 60평 복층 펜트 하우스 1일 숙박권 펜션 힐링 숙소 별장", description = R.string.number7, seller = "굿리치", price = "150000", address = "남구 옥동", like = 142, chat = 54, image = R.drawable.sample7))
        data.add(ItemEntity(name = "샤넬 탑핸들 가방", description = R.string.number8, seller = "난쉽", price = "180000", address = "동래구 온천제2동", like = 31, chat = 7, image = R.drawable.sample8))
        data.add(ItemEntity(name = "4행정 엔진 분무기 판매 합니다.", description = R.string.number9, seller = "알뜰한", price = "30000", address = "원주시 명륜2동", like = 7, chat = 28, image = R.drawable.sample9))
        data.add(ItemEntity(name = "셀린느 버킷 가방", description = R.string.number10, seller = "똑태현", price = "190000", address = "중구 동화동", like = 40, chat = 6, image = R.drawable.sample10))

        return data
    }
}