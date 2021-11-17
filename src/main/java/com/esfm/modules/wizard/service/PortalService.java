package com.esfm.modules.wizard.service;

import com.esfm.extension.api.R;

public interface PortalService {

    R<?> loginDoBySysUserByTel(String tel, String password);
}
