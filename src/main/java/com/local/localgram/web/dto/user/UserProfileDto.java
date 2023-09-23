package com.local.localgram.web.dto.user;

import com.local.localgram.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto {

    private boolean pageOwnerStatus;
    private int imageCount;
    private User user;
}
