package com.zf.zf_server.modules.wizard.service;

import com.zf.zf_server.extension.api.R;

public interface PortalService {

    R<?> loginDoBySysUserByTel(String tel, String password);
}
