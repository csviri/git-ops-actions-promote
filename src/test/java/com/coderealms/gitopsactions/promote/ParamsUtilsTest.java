package com.coderealms.gitopsactions.promote;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParamsUtilsTest {

    @Test
    public void parsesMappingYaml() throws IOException {
        String s = IOUtils.toString(new ClassPathResource("/gitopsactions.yaml").getInputStream());
        PromotionMapping promotionMapping = ParamsUtils.parsePromotionMapping(s);

        assertEquals(2, promotionMapping.getPromotionMapping().size());
    }

}
