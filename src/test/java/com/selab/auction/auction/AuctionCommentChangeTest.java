package com.selab.auction.auction;

import com.selab.auction.item.model.entity.Item;
import com.selab.auction.item.model.vo.Category;
import com.selab.auction.item.model.vo.ItemState;
import com.selab.auction.item.repository.ItemRepository;
import com.selab.auction.member.model.entity.Member;
import com.selab.auction.member.repository.MemberRepository;
import com.selab.auction.participation.model.dto.*;
import com.selab.auction.participation.service.AuctionCommentService;
import com.selab.auction.participation.service.AuctionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AuctionCommentChangeTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private AuctionCommentService auctionCommentService;

    @Test
    void 경매_코멘트변경_테스트 () {
        // 멤버, 아이템 생성
        Member buyMemberTest = new Member("test@test.com6", "a1234567!", "testUser6", "경기도 용인시", "010-1234-5678", "F");
        Member saleMemberTest = new Member("test@test.com7", "a1234568!", "testUser7", "경기도 안양시", "010-1234-5678", "M");
        Item itemTest = new Item(2L, Category.FOOD, "testItem1", 35000L, "No Content", 4, 40000L, ItemState.PROGRESS);
        memberRepository.save(buyMemberTest);
        memberRepository.save(saleMemberTest);
        itemRepository.save(itemTest);

        // 즉시구매
        CreateImmediatePurchaseDto immediatePurchaseTest = new CreateImmediatePurchaseDto(1L, 1L);
        auctionService.immediatePurchaseItem(immediatePurchaseTest);
        // 코멘트 작성
        CreateBuyCommentDto createBuyCommentTest = new CreateBuyCommentDto(1L, 1L, "good", 5D);
        CreateSaleCommentDto createSaleCommentTest = new CreateSaleCommentDto(1L, 1L, 2L, "good", 5D);
        auctionCommentService.registerBuyComment(createBuyCommentTest);
        auctionCommentService.registerSaleComment(createSaleCommentTest);
        // 코멘트 변경
        ChangeBuyCommentDto changeBuyCommentTest = new ChangeBuyCommentDto(1L, 1L, "bad", 1D);
        ChangeSaleCommentDto changeSaleCommentTest = new ChangeSaleCommentDto(1L, 2L, "bad", 1D);
        auctionCommentService.changeBuyComment(changeBuyCommentTest);
        auctionCommentService.changeSaleComment(changeSaleCommentTest);

        // 코멘트 별점 변경 확인
        Double buyMemberGrade = memberRepository.findById(1L).get().getGrade();
        Double saleMemberGrade = memberRepository.findById(2L).get().getGrade();
        assertThat(buyMemberGrade).isEqualTo(1D);
        assertThat(saleMemberGrade).isEqualTo(1D);
    }
}
