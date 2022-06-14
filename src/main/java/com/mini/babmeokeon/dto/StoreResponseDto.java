package com.mini.babmeokeon.dto;


import com.mini.babmeokeon.model.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class StoreResponseDto {
    private Long id;
    private String storeName;
    private String address;
    private String nickname;
    private String menu;
    private String img_url;
    private String icon_url;
    private int stars;
    private String comment;
    private int likeCount;
    private LocalDateTime timestamp;

    private boolean isLike = false;

    public StoreResponseDto(Store store){
        this.id = store.getId();
        this.storeName = store.getStoreName();
        this.address = store.getAddress();
        this.nickname = store.getUser().getNickname();
        this.icon_url = store.getUser().getIcon_url();
        this.menu = store.getMenu();
        this.img_url = store.getImg_url();
        this.stars = store.getStars();
        this.comment = store.getComment();
        this.likeCount = store.getLikeCount();
        this.timestamp = store.getTimestamp();
    }

    public StoreResponseDto(Store store, boolean isLike) {
        this.id = store.getId();
        this.storeName = store.getStoreName();
        this.address = store.getAddress();
        this.nickname = store.getUser().getNickname();
        this.icon_url = store.getUser().getIcon_url();
        this.menu = store.getMenu();
        this.img_url = store.getImg_url();
        this.stars = store.getStars();
        this.comment = store.getComment();
        this.likeCount = store.getLikeCount();
        this.timestamp = store.getTimestamp();
        this.isLike = isLike;
    }
}
