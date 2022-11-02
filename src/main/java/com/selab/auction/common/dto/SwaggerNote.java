package com.selab.auction.common.dto;

public class SwaggerNote {
    public final static String ITEM_READ_ALL_NOTE = """
                모든 상품 목록 조회
                GET /api/v1/items
            
                ResponseBody
                {
                    "items": 
                    [
                        {
                                "id": 1,
                                "name": "아이템1",
                                "price": 10000,
                                "category": "FOOD",
                                "memberId": 1,
                                "description": "음식 상품입니다",
                                "auctionPeriod": 10,
                                "immediatelyPrice": 30000,
                                "state": "ACTIVE"
                        },
                        {
                                "id": 2,
                                "name": "아이템2",
                                "price": 10000,
                                "category": "LIFE",
                                "memberId": 1,
                                "description": "생활 상품입니다",
                                "auctionPeriod": 7,
                                "immediatelyPrice": 50000,
                                "state": "ACTIVE"
                        }
                    ]
                }
            """;

    public final static String ITEM_READ_ONE_NOTE = """
                상품 단건 조회
                GET /api/v1/items/{id}
            
                ResponseBody
                {
                     "id": 1,
                     "name": "아이템1",
                     "price": 10000,
                     "category": "FOOD",
                     "memberId": 1,
                     "description": "음식 상품입니다",
                     "auctionPeriod": 10,
                     "immediatelyPrice": 30000,
                     "state": "ACTIVE"
                 }
            """;

    public final static String ITEM_CREATE_NOTE = """
                    상품 등록하기
                    POST /api/v1/items/           
                        
                    Request Body
                    {
                        "name" : "아이템2",
                        "price": 10000,
                        "description" : "생활 상품입니다",
                        "category" : "LIFE",
                        "memberId" : 1,
                        "auctionPeriod" : 7,
                        "immediatelyPrice" : 50000
                    }
                        
                    Response Body
                    {
                              "id": 3,
                              "name": "아이템3",
                              "price": 30000,
                              "category": "FOOD",
                              "memberId": 1,
                              "description": "음식 상품입니다2",
                              "auctionPeriod": 4,
                              "immediatelyPrice": 35000,
                              "state": "ACTIVE"
                    }     
            """;
}
