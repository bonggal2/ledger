package com.prometheus.ledger.core.util;

import com.prometheus.ledger.core.model.BaseProcessContext;
import com.prometheus.ledger.core.model.Processor;
import com.prometheus.ledger.core.model.result.BaseResult;

import java.util.List;

public class ProcessorUtil {
    public static void runProcessors(List<Processor<BaseProcessContext>> processorList, BaseProcessContext context) {
        if (CollectionUtil.isEmpty(processorList)) {
            return;
        }

        BaseResult processResult = new BaseResult();
        processResult.setSuccess(true);
        processorList
                .stream().filter(processor -> processResult.isSuccess())
                .forEach(processor -> {
                    try {
                        if (processor.isSkipped(context)) {
                            return;
                        }
                        processor.check(context);
                        processor.doProcess(context);
                    } catch (Throwable e) {
                        processResult.setSuccess(false);
                    }
                });
    }
}
