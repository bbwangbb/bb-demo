package cn.mb.wx.mp.dto;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/8
 */
@Data
public class GetPhoneNoInfoDTO {

    private String code;
    private String encryptedData;
    private String iv;

}
