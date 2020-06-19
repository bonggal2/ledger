package com.prometheus.ledger.core.util;

import com.prometheus.ledger.core.model.BaseProcessContext;
import com.prometheus.ledger.core.model.Money;
import com.prometheus.ledger.core.model.Processor;

import java.util.List;

public class ProcessorUtil {
    public static void runProcessors(List<Processor<BaseProcessContext>> processorList, BaseProcessContext context){
        if (CollectionUtil.isEmpty(processorList)){
            return;
        }

        processorList.forEach(processor -> {
            if (processor.isSkipped(context)) {
                return;
            }
            processor.check(context);
            processor.doProcess(context);
        });
    }
}
