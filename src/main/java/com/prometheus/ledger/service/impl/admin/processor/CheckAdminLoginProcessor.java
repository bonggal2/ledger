package com.prometheus.ledger.service.impl.admin.processor;

import com.prometheus.ledger.core.model.Processor;
import com.prometheus.ledger.core.model.error.ErrorCode;
import com.prometheus.ledger.core.util.AssertUtil;
import com.prometheus.ledger.repository.admin.AdminRepository;
import com.prometheus.ledger.repository.admin.entity.AdminMemberDTO;
import com.prometheus.ledger.service.facade.admin.request.CheckAdminLoginRequest;
import com.prometheus.ledger.service.facade.admin.result.CheckAdminLoginResult;
import com.prometheus.ledger.service.impl.admin.context.AdminContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class CheckAdminLoginProcessor implements Processor<AdminContext> {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public boolean isSkipped(AdminContext context) {
        return false;
    }

    @Override
    public void check(AdminContext context) {
        CheckAdminLoginRequest request = (CheckAdminLoginRequest) context.getRequest();
        AssertUtil.isNotNull(request, ErrorCode.PARAM_ILLEGAL, "request is null");
        AssertUtil.isNotBlank(request.getUsername(), ErrorCode.PARAM_ILLEGAL, "username is blank");
        AssertUtil.isNotBlank(request.getPassword(), ErrorCode.PARAM_ILLEGAL, "password is blank");
    }

    @Override
    public void doProcess(AdminContext context) {

        CheckAdminLoginRequest request = (CheckAdminLoginRequest) context.getRequest();
        List<AdminMemberDTO> adminMemberDTOList = adminRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (CollectionUtils.isEmpty(adminMemberDTOList)){
            return;
        }

        AdminMemberDTO adminMemberDTO = adminMemberDTOList.parallelStream()
                .findFirst().orElse(null);

        if (null == adminMemberDTO){
            return;
        }

        CheckAdminLoginResult result = (CheckAdminLoginResult) context.getResult();
        result.setAdminId(adminMemberDTO.getAdminId());
        result.setSuccess(true);
    }
}
