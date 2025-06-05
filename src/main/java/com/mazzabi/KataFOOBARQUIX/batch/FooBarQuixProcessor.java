package com.mazzabi.KataFOOBARQUIX.batch;

import com.mazzabi.KataFOOBARQUIX.service.IFooBarQuixService;
import org.springframework.batch.item.ItemProcessor;

public class FooBarQuixProcessor implements ItemProcessor<String, String>  {

    private IFooBarQuixService fooBarQuixService;

    public FooBarQuixProcessor(IFooBarQuixService fooBarQuixService) {
        this.fooBarQuixService = fooBarQuixService;
    }

    @Override
    public String process(String item) throws Exception {
        try {
            return item + " \"" + fooBarQuixService.transform(Integer.parseInt(item)) + "\"";
        } catch (NumberFormatException e) {
            return item + " \"[UNKNOWN ENTRY]\"";
        }
    }
}
