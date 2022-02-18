package cn.mb.wx.mp.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.mb.wx.mp.dto.GetPhoneNoInfoDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/8
 */
@RestController
@AllArgsConstructor
public class 解析手机号 {

    private final WxMaConfig wxMaConfig;
    private final WxMaService wxMaService;

    @PostMapping("/getPhoneNoInfo")
    public Object getPhoneNoInfo(@RequestBody GetPhoneNoInfoDTO getPhoneNoInfoDTO) throws Exception {
//        System.out.println(wxMaConfig);
        String code = getPhoneNoInfoDTO.getCode();
        System.out.println(code);
        String encryptedData = getPhoneNoInfoDTO.getEncryptedData();
        String iv = getPhoneNoInfoDTO.getIv();
        //  获取openid
        WxMaJscode2SessionResult wxMaJscode2SessionResult = wxMaService.jsCode2SessionInfo(code);
        System.out.println(wxMaJscode2SessionResult);
        //  获取手机号
        WxMaUserService wxMaUserService = wxMaService.getUserService();
        WxMaPhoneNumberInfo phoneNoInfo = wxMaUserService.getPhoneNoInfo(wxMaJscode2SessionResult.getSessionKey(), encryptedData, iv);
        System.out.println(phoneNoInfo);
        return null;
    }

}
