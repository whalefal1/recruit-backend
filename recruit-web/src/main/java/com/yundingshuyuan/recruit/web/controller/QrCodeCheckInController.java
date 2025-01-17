package com.yundingshuyuan.recruit.web.controller;

import com.yundingshuyuan.enums.RespStatusEnum;
import com.yundingshuyuan.recruit.api.QrCodeCheckInService;
import com.yundingshuyuan.recruit.web.annotation.RecruitResult;
import com.yundingshuyuan.vo.BasicResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 扫描二维码-接口
 *
 * @author wys
 */
@Slf4j
@RecruitResult
@RestController
@RequestMapping("/checkin")
@Tag(name = "二维码签到接口")
@RequiredArgsConstructor
public class QrCodeCheckInController {

    private final QrCodeCheckInService qrCodeCheckInService;

    /**
     * 获取二维码
     * <br>TODO: 修改请求参数或请求方式
     *
     * @param openId     openId
     * @param eventName  签到事件名称 现有(宣讲会)
     * @param expireTime 二维码有效时长
     * @return
     */
    @GetMapping("/qrcode")
    @Operation(summary = "获取二维码")
    public BasicResultVO<String> getQrCode(@RequestParam String openId, @RequestParam String eventName, @RequestParam int expireTime) {
        return new BasicResultVO<>(RespStatusEnum.SUCCESS, qrCodeCheckInService.createQrCode(openId, eventName, expireTime));
    }

    /**
     * 解析二维码内容
     * <br>TODO: 修改请求参数或请求方式
     *
     * @param scanInfo 扫描二维码后获取的内容
     * @return
     */
    @PostMapping("/parse")
    @Operation(summary = "解析扫描二维码后的内容")
    public boolean signUserIn(@RequestBody String scanInfo) {
        qrCodeCheckInService.parseQrCodeInfo(scanInfo);
        return true;
    }

}
