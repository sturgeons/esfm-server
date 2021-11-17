package com.esfm.modules.wizard.service.impl;

import com.esfm.modules.wizard.entity.bo.HuaweiAuthProperties;
import com.esfm.modules.wizard.service.HuaweiOcrService;
import com.huaweicloud.sdk.core.auth.BasicCredentials;
import com.huaweicloud.sdk.core.auth.ICredential;
import com.huaweicloud.sdk.core.exception.ConnectionException;
import com.huaweicloud.sdk.core.exception.RequestTimeoutException;
import com.huaweicloud.sdk.core.exception.ServiceResponseException;
import com.huaweicloud.sdk.ocr.v1.OcrClient;
import com.huaweicloud.sdk.ocr.v1.model.GeneralTextRequestBody;
import com.huaweicloud.sdk.ocr.v1.model.RecognizeGeneralTextRequest;
import com.huaweicloud.sdk.ocr.v1.model.RecognizeGeneralTextResponse;
import com.huaweicloud.sdk.ocr.v1.region.OcrRegion;
import com.esfm.extension.api.R;
import com.esfm.extension.constant.ResponseInfoConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("HuaweiOcrService")
public class HuaweiOcrServiceImpl implements HuaweiOcrService {
    @Autowired
    private HuaweiAuthProperties huaweiAuthProperties;

    @Override
    public R<?> getOcr(String url) {
        RecognizeGeneralTextRequest request = new RecognizeGeneralTextRequest();
        GeneralTextRequestBody body = new GeneralTextRequestBody();
        body.withUrl(url);
        body.setDetectDirection(true);
        return getR(request, body);
    }

    @Override
    public R<?> getOcrBase64(String imgStr) {
        RecognizeGeneralTextRequest request = new RecognizeGeneralTextRequest();
        GeneralTextRequestBody body = new GeneralTextRequestBody();
        body.withImage(imgStr);
        return getR(request, body);
    }

    private OcrClient getClient() {
        String ak = huaweiAuthProperties.getAk();
        String sk = huaweiAuthProperties.getSk();
        ICredential auth = new BasicCredentials()
                .withAk(ak)
                .withSk(sk);
        return OcrClient.newBuilder()
                .withCredential(auth)
                .withRegion(OcrRegion.valueOf("cn-north-4"))
                .build();
    }


    private R<?> getR(RecognizeGeneralTextRequest request, GeneralTextRequestBody body) {
        request.withBody(body);
        try {
            List<String> maps = new ArrayList<>();
            RecognizeGeneralTextResponse response = getClient().recognizeGeneralText(request);
            response.getResult().getWordsBlockList().forEach(t -> {
                System.out.println(t.getWords());
                maps.add(t.getWords());
            });
            return R.ok(maps);
        } catch (ConnectionException | RequestTimeoutException e) {
            e.printStackTrace();
        } catch (ServiceResponseException e) {
            e.printStackTrace();
            System.out.println(e.getHttpStatusCode());
            System.out.println(e.getErrorCode());
            System.out.println(e.getErrorMsg());
        }
        return R.failed(ResponseInfoConstant.OPERATE_FAIL);
    }
}
